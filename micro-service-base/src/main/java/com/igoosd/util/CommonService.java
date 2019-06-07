package com.igoosd.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface CommonService<T, ID extends Serializable> {

    /**
     * 新增
     *
     * @param t
     */
    T insert(T t);

    /**
     * 删除
     *
     * @param t
     */
    T update(T t);

    /**
     * 统计
     *
     * @param t
     * @return
     */
    long getCount(T t);

    /**
     * 根据Id获取实体信息
     *
     * @param id
     * @return
     */
    T getEntityById(ID id);

    /**
     * 根据ＩＤ　删除
     *
     * @param id
     */
    void delete(ID id);

    void delete(T t);

    /**
     * 获取template 下拉框 hash
     *
     * @param needBlank
     * @return
     */
    Map<String, String> getHash(boolean needBlank);

    /**
     * 分页查找
     *
     * @param t
     * @param pageable
     * @return
     */
    Page<T> findPageList(T t, Pageable pageable);

    /**
     * 根据ID列表获取Map<ID,T>
     *
     * @param ids
     * @return
     */
    Map<ID, T> findMapByIds(Collection<ID> ids);

    /**
     * 获取所有记录（慎用）
     *
     * @return
     */
    List<T> findAll();

    List<T> findAll(T param);

    List<T> findAll(List<ID> ids);

}
