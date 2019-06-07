package com.igoosd.api;

import com.igoosd.domain.User;
import com.igoosd.domain.vo.ResultMsg;
import com.igoosd.exception.StaticException;
import com.igoosd.util.RoadPricingException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.igoosd.util.Constants.SESSION_USER_KEY;

public abstract class BaseController<T> {

    protected User getUserFromSession(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(SESSION_USER_KEY);
    }

    protected List<Long> getIdList(String ids) {
        Set<String> strSet = StringUtils.commaDelimitedListToSet(ids);
        List<Long> idList = new ArrayList<>(strSet.size());
        try {
            for (String idStr : strSet) {
                idList.add(Long.valueOf(idStr));
            }
        } catch (Exception e) {
            throw new RoadPricingException("无效的参数");
        }
        return idList;
    }

    /**
     * 0 补齐 如  3位  1-> 001
     *
     * @param value
     * @param length
     * @return
     */
    protected String preFixZero(int value, int length) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumIntegerDigits(length);
        numberFormat.setMinimumIntegerDigits(length);
        return numberFormat.format(value);
    }

    protected ResultMsg<?> verifyCodeForList(List<T> list, T t) {
        return verifyForList(list,t,"编码");
    }

    protected ResultMsg<?> verifyForList(List<T> list, T t,String fieldDescName) {

        if (!CollectionUtils.isEmpty(list)) {
            Long id = getId(t);
            if (id != null) {
                if (!id.equals(getId(list.get(0)))) {
                    return ResultMsg.resultFail(fieldDescName + "不唯一");
                }
            } else {
                return ResultMsg.resultFail(fieldDescName +"不唯一");
            }
        }
        return ResultMsg.resultSuccess(fieldDescName + "校验成功");
    }

    private Long getId(T t) {
        Long id;
        try {
            Class clazz  = t.getClass();
            Method method =  clazz.getMethod("getId");
            id = (Long) method.invoke(t);
        } catch (Exception e) {
            throw new StaticException("获取ID异常id");
        }
        return id;
    }

}
