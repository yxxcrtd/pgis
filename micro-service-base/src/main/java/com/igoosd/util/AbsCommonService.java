package com.igoosd.util;

import com.igoosd.exception.StaticException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
public abstract class AbsCommonService<T, ID extends Serializable> implements CommonService<T, ID> {

    protected abstract JpaRepository<T, ID> getRepository();

    /**
     * 新增
     *
     * @return
     */
    @Override
    @Transactional
    public T insert(T t) {
        if (null == getId(t)) {
           return getRepository().save(t);
        }
        return null;
    }

    /**
     * 更新
     *
     * @param newParam
     * @return
     */
    @Override
    @Transactional
    public T update(T newParam) {
        ID id = getId(newParam);
        if (null != id) {
            T target = getRepository().findOne(id);
            try {
                copyCustomProperties(newParam, target);
            } catch (Exception e) {
                log.error("更新操作属性copy 异常", e);
                throw new StaticException("更新操作属性copy 异常", e);
            }
           return getRepository().saveAndFlush(target);
        }
        return null;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    @Transactional
    public void delete(ID id) {
        getRepository().delete(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public void delete(T t) {
        List<T> list = getRepository().findAll(Example.of(t));
        if (!CollectionUtils.isEmpty(list)) {
            getRepository().deleteInBatch(list);
        }
    }

    /**
     * 根据ID获取实体信息
     *
     * @param id
     * @return
     */
    @Override
    public T getEntityById(ID id) {
        return getRepository().findOne(id);
    }


    @Override
    public long getCount(T t) {
        return getRepository().count(Example.of(t));
    }

    /**
     * 获取freemarker 需要的 remote 业务数据 hash 结构
     *
     * @param needBlank 下拉框数据第一行是否为 “”
     * @return
     */
    @Override
    public Map<String, String> getHash(boolean needBlank) {
        return getHash("id", "name", needBlank);
    }

    protected Map<String, String> getHash(String keyName, String valueName, boolean needBlank) {
        List<T> totals = getRepository().findAll();
        Map<String, String> map = new TreeMap<>();
        if (needBlank) {
            map.put("", "");
        }
        try {
            if (!CollectionUtils.isEmpty(totals)) {
                for (T t : totals) {
                    Object keyObj = ReflectionUtils.getField(FieldUtils.getDeclaredField(t.getClass(), keyName, true), t);
                    Object valObj = ReflectionUtils.getField(FieldUtils.getDeclaredField(t.getClass(), valueName, true), t);
                    if (null != keyObj && null != valObj) {
                        map.put(keyObj.toString(), valObj.toString());
                    }
                }
            }
        } catch (Exception e) {
            log.error("反射回去值异常....", e);
        }
        return map;
    }


    /**
     * 分页查找 通用分页查找 equals 查询
     *
     * @param t
     * @param pageable
     * @return
     */
    @Override
    public Page<T> findPageList(T t, Pageable pageable) {
        JpaSpecificationExecutor<T> jpa = getJpaSpecificationExecutor();
        if (null == jpa) {
            return getRepository().findAll(Example.of(t), pageable);
        } else {
            return jpa.findAll((root, criteriaQuery, criteriaBuilder) -> {
                List<Predicate> list = getPredicateList(t, root, criteriaBuilder);
                List<Order> orderList = new ArrayList<>(5);
                Sort sort = pageable.getSort();
                if (null != sort) {
                    Iterator<Sort.Order> iterator = pageable.getSort().iterator();
                    while (iterator.hasNext()) {
                        Sort.Order order = iterator.next();
                        orderList.add(new OrderImpl(root.get(order.getProperty()), order.isAscending()));
                    }
                }
                criteriaQuery.where(list.toArray(new Predicate[]{})).orderBy(orderList.toArray(new Order[orderList.size()]));
                return null;
            }, pageable);
        }
    }

    @Override
    public Map<ID, T> findMapByIds(Collection<ID> ids) {
        Map<ID, T> rstMap = new HashMap<>(ids.size());
        List<T> list = getRepository().findAll(ids);
        if (!CollectionUtils.isEmpty(list)) {
            for (T t : list) {
                rstMap.put(getId(t), t);
            }
        }
        return rstMap;
    }


    /**
     * 将source 中字段值不为空的字段替换到目标对象中去
     *
     * @param source
     * @param target
     * @param <T>
     */
    private final <T> void copyCustomProperties(T source, T target) throws IllegalAccessException {
        Class<T> clazz = (Class<T>) target.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            Object value = FieldUtils.readDeclaredField(source, fieldName, true);
            if (null != value) {
                FieldUtils.writeDeclaredField(target, fieldName, value, true);
            }
        }
    }

    /**
     * 判断是否存在id主键
     *
     * @param t
     * @return
     */
    private ID getId(T t) {
        ID id = null;
        try {
            Field field = t.getClass().getDeclaredField("id");
            field.setAccessible(true);
            Object val = field.get(t);
            if (null != val) {
                id = (ID) val;
            }
            field.setAccessible(false);
        } catch (Exception e) {
            log.error("{},没有指定的主键ID设计或其他异常。", t.getClass().getName());
        }
        return id;
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public List<T> findAll(T param) {
        return getRepository().findAll(Example.of(param));
    }

    @Override
    public List<T> findAll(List<ID> ids) {
        return getRepository().findAll(ids);
    }

    /**
     * 单表 like >=操作   子类覆盖
     *
     * @param root
     * @return
     */
    protected List<Predicate> getPredicateList(T t, Root<T> root, CriteriaBuilder cb) {
        return Collections.EMPTY_LIST;
    }

    /**
     * 单表 like >=操作   子类覆盖
     *
     * @return
     */
    protected JpaSpecificationExecutor<T> getJpaSpecificationExecutor() {
        return null;
    }

}
