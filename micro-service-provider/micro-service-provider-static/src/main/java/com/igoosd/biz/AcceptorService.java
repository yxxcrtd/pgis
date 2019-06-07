package com.igoosd.biz;

import com.igoosd.biz.init.AbsScreenInitService;
import com.igoosd.biz.init.MediaScreenInitService;
import com.igoosd.domain.Config;
import com.igoosd.domain.ControlCard;
import com.igoosd.domain.FlowInfoData;
import com.igoosd.domain.Gateway;
import com.igoosd.domain.Parking;
import com.igoosd.domain.ParkingScreen;
import com.igoosd.domain.Screen;
import com.igoosd.enumeration.ControlCardInitStatusEnum;
import com.igoosd.enumeration.ScreenTypeEnum;
import com.igoosd.exception.StaticException;
import com.igoosd.http.vo.RealTimeVo;
import com.igoosd.service.ConfigService;
import com.igoosd.service.ControlCardService;
import com.igoosd.service.FlowInfoDataService;
import com.igoosd.service.GatewayService;
import com.igoosd.service.ParkingScreenService;
import com.igoosd.service.ParkingService;
import com.igoosd.service.ScreenService;
import com.igoosd.util.Assert;
import com.igoosd.util.Constants;
import com.igoosd.util.FlowInfoVo;
import com.igoosd.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
@Slf4j
public class AcceptorService {


    @Autowired
    private GatewayService gatewayService;

    @Autowired
    private ScreenService screenService;

    @Autowired
    private ControlCardService controlCardService;

    @Autowired
    private FlowInfoDataService flowInfoDataService;
    @Autowired
    private ParkingScreenService parkingScreenService;
    @Autowired
    private ScreenSendService screenSendService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private List<AbsScreenInitService> initServiceList;
    @Autowired
    private ParkingService parkingService;

    @Autowired
    private ConfigService configService;

    @Autowired
    private ScreenInitProperties screenInitProperties;

    @Autowired
    private MediaScreenInitService mediaScreenInitService;

    private static final ExecutorService pool = Executors.newFixedThreadPool(10);

    private Map<Long, Boolean> carNumZeroMap = new HashMap<>();

        private final Map<Long,Long> timestampMap = new HashMap<>(10);

    private final Lock lotRemainLock = new ReentrantLock();

    /*** 初始化所有控制卡  程序启动过程中初始化....***/
    @PostConstruct
    public void init() {
        List<ControlCard> controlCardList = controlCardService.findAll();
        if (null != controlCardList && !controlCardList.isEmpty()) {
            for (ControlCard controlCard : controlCardList) {
                initControlCard(controlCard);
            }
        }
    }

    // 控制卡只能控制多媒体或者车位信息 一类屏
    private void initControlCard(ControlCard controlCard) {
        try {
            List<Screen> screenList = screenService.findByControlCardIdOrderByOrderNumAsc(controlCard.getId());
            log.info("当前控制卡（{}）对应的屏幕列表：{}", controlCard, screenList);

            if (!CollectionUtils.isEmpty(screenList)) {

                // 屏幕类型校验
                ScreenTypeEnum ste = checkSameTypeForScreenList(screenList, controlCard.getCode());
                log.info("当前屏幕类型：{}", ste);

                // 初始化屏幕
                for (AbsScreenInitService initService : initServiceList) {
                    log.info("初始化屏幕类型：{}", initService.getScreenType());

                    if (initService.getScreenType() == ste) {
                        log.info("2个屏幕类型相等！");
                        initService.doInit(screenList, controlCard);
                        controlCard.setInitStatus(ControlCardInitStatusEnum.SUCCESS.getValue());
                        controlCard.setRemark("屏幕初始化成功！");
                        return;
                    }
                }
                throw new StaticException("未找到相应的屏幕初始化服务，请检查接口注入是否完整。屏幕类型：" + ste.getName());
            } else {
                controlCard.setInitStatus(ControlCardInitStatusEnum.FAIL.getValue());
                controlCard.setRemark("未配置关联屏");
            }
        } catch (StaticException e) {
            log.info("==============================================================错误信息：", e);
            controlCard.setInitStatus(ControlCardInitStatusEnum.FAIL.getValue());
            controlCard.setRemark(e.getMessage());
        } finally {
            log.info("更新控制卡对象：{}", controlCard);
            log.info("更新控制卡：{} {}", controlCard.getCode(), controlCard.getRemark());

            controlCardService.update(controlCard);
        }
    }


    /**
     * 初始化单个控制卡控制卡
     *
     * @param controlCardId
     * @desc 对外提供 单个控制卡初始化操作接口
     */
    public void initControlCard(Long controlCardId) {
        ControlCard controlCard = controlCardService.getEntityById(controlCardId);
        if (null == controlCard) {
            throw new StaticException("找不到指定的控制卡信息");
        }
        initControlCard(controlCard);
    }

    /**
     * 校验控制卡关联的屏幕是否为同一类型
     *
     * @param screenList
     * @param controlCardCode
     * @return
     */
    private ScreenTypeEnum checkSameTypeForScreenList(List<Screen> screenList, String controlCardCode) {
        ScreenTypeEnum ste = null;
        for (Screen screen : screenList) {
            if (null == ste) {
                ste = ScreenTypeEnum.getEnumByValue(screen.getType());
            } else {
                Assert.isTrue(screen.getType().equals(ste.getValue()), "屏幕数据配置不对，存在同一控制卡配置不同类型的屏幕。控制卡编码:" + controlCardCode);
            }
        }
        return ste;
    }

    /**
     * 接入数据 FlowInfo 处理。。。。
     * 网关关联 停车场中的 叶子 停车场
     *
     * @param vo
     * @param latestData 如果是定时task 操作  设置为 false
     */
    @Transactional(rollbackFor = Exception.class)
    public void doFlowInfo(FlowInfoVo vo, boolean latestData) {

        Gateway gateway = gatewayService.getGatewayByCode(vo.getGatewayNum() + "");
        if (gateway == null) {
            throw new StaticException("找不到指定的网关");
        }
        Long parkingSpaceId = gateway.getParkingId();
        //停车场数据更新
        parkingService.changeLotRemain(vo.getExitCarFlow(), vo.getEnterCarFlow(), parkingSpaceId);
        //记录数据
        FlowInfoData flowInfoData = new FlowInfoData();
        flowInfoData.setEnterNum(vo.getEnterCarFlow());
        flowInfoData.setExitNum(vo.getExitCarFlow());
        flowInfoData.setUniqueKey(vo.getKey());
        flowInfoData.setParkingSpaceId(parkingSpaceId);
        flowInfoData.setGatewayCode(vo.getGatewayNum() + "");
        flowInfoData.setCreateTime(new Date());
        flowInfoDataService.insert(flowInfoData);
        //异步执行车位数数据发送操作...
        pool.submit(() -> {
            //发送车位数
            sendLotRemainCount(parkingSpaceId);
            //redis 数据更新 --用于更新地磁电量信息
            if (latestData) {
                redisTemplate.opsForHash().put(Constants.REDIS_HASH_KEY_FLOW_INFO, "" + vo.getGatewayNum(), GsonUtil.toJson(vo));
            }
        });
    }

    public void changeParkingLotRemainCount(int lotRemainCount, Long parkingId, Long timestamp) {
        lotRemainLock.lock();
        Long latest = timestamp;
        try{
            Long old = getTimestampByKey(parkingId);
            if(null != old && timestamp.compareTo(old) < 0){
                latest = old;
                log.warn("过期的剩余停车位数更新...,parkingId:{},timestamp:{},lotremainCount:{}",parkingId,timestamp,lotRemainCount);
            }else {
                //更新剩余车位数
                Parking temp = new Parking();
                temp.setId(parkingId);
                temp.setLotRemainCount(lotRemainCount);
                parkingService.update(temp);
                //发送屏幕
                pool.submit(() -> {
                    //发送车位数--屏幕显示异步 没关系，必须保证db存储的数据一致性 同步 即可
                    sendLotRemainCount(parkingId);
                });
            }
        }finally {
            putTimestamp(parkingId,latest);
            lotRemainLock.unlock();
        }
    }

    /**
     * 网关注册
     *
     * @return
     */
    public boolean registerGateway(String gatewayCode) {
        Gateway gateway = new Gateway();
        gateway.setCode(gatewayCode);
        return gatewayService.getCount(gateway) > 0;
    }


    /**
     * 超时数据进一步确认或 同步
     *
     * @param vo
     * @return
     */
    public void confirm(FlowInfoVo vo) {
        FlowInfoData data = new FlowInfoData();
        data.setUniqueKey(vo.getKey());
        long count = flowInfoDataService.getCount(data);
        if (count == 0) {
            doFlowInfo(vo, false);
        }
    }

    /**
     * 发送停车场数据
     */
    public void sendLotRemainCount(Long parkingSpaceId) {

        log.info("当前停车场ID：{}", parkingSpaceId);

        // 发送数据
        try {
            ParkingScreen temp = new ParkingScreen();
            temp.setParkingId(parkingSpaceId);
            List<ParkingScreen> pssList = parkingScreenService.findAll(temp);

            log.info("当前停车场关联的屏幕list：{}", pssList);

            if (!CollectionUtils.isEmpty(pssList)) {
                // 获取剩余停车数
                Parking parking = parkingService.getEntityById(parkingSpaceId);

                log.info("当前停车场对象：{}", parking);

                int remainLotCount = parking.getLotRemainCount();

                log.info("当前停车场剩余停车数：{}", remainLotCount);

                // 屏幕数据发送
                for (ParkingScreen pss : pssList) {
                    Screen screen = screenService.getEntityById(pss.getScreenId());
                    log.info("当前屏幕对象：{}", screen);

                    ControlCard controlCard = controlCardService.getEntityById(screen.getControlCardId());
                    log.info("当前控制卡对象：{}", controlCard);

//                    Assert.isTrue(controlCard.getInitStatus().equals(ControlCardInitStatusEnum.SUCCESS.getValue()), "控制卡初始化异常,请先进行初始化操作");
//                    Assert.notNull(screen, "找不到指定的屏幕，请确保数据准确性,screenId:" + pss.getId());
                    RealTimeVo vo = screenSendService.sendLotRemainCount(remainLotCount, controlCard.getCode(), screen.getId());
                    log.info("发送剩余车位数：{} 到控制卡：{} {}", remainLotCount, controlCard.getCode(), "success".equals(vo.get_type()) ? "成功！" : "失败了......................................................");
                }

                if (parking.getMediaScreenId() != null) {
                    //为0 的时候显示
                    boolean status = false;
                    if (0 == remainLotCount) {
                        synchronized (carNumZeroMap) {
                            Boolean flag = carNumZeroMap.get(parkingSpaceId);
                            if (flag == null || !flag) {
                                carNumZeroMap.put(parkingSpaceId, true);
                                status = true;
                            }
                        }
                        if (status) {
                            pool.submit(() -> {
                                Config config = configService.getByName(Constants.CONFIG_ZERO_CAR_NUM_MEDIA_INFO);
                                if (config == null) {
                                    log.warn("未添加车位数为0，全息屏展示配置项：" + Constants.CONFIG_ZERO_CAR_NUM_MEDIA_INFO);
                                    return;
                                }
                                Screen mediaScreen = screenService.getEntityById(parking.getMediaScreenId());
                                ControlCard cc = controlCardService.getEntityById(mediaScreen.getControlCardId());
                                screenSendService.loadUrlForScreen(cc.getCode(), screenInitProperties.getDomain() + "content/zero?width=" + mediaScreen.getWidth() + "&height=" + mediaScreen.getHeight() + "&unitName="
                                        + (parking.getUnitName() == null ? "" : parking.getUnitName()));
                            });
                        }
                    } else {
                        synchronized (carNumZeroMap) {
                            Boolean flag = carNumZeroMap.get(parkingSpaceId);
                            if (flag != null && flag) {
                                carNumZeroMap.remove(parkingSpaceId);
                                status = true;
                            }
                        }
                        if (status) {
                            //查找最新的programId 如果 没有显示默认
                            pool.submit(() -> {
                                Screen mediaScreen = screenService.getEntityById(parking.getMediaScreenId());
                                List<Screen> list = new ArrayList<>(1);
                                list.add(mediaScreen);
                                ControlCard cc = controlCardService.getEntityById(mediaScreen.getControlCardId());
                                mediaScreenInitService.doInit(list, cc);
                            });

                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("发送屏幕车位数接口异常",e);
        }
    }

    public synchronized Long getTimestampByKey(Long key){
        return timestampMap.get(key);
    }

    public synchronized  void putTimestamp(Long key ,Long timestamp){
        timestampMap.put(key,timestamp);
    }

}
