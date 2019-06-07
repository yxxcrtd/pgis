package com.igoosd.repository.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Iterator;
import java.util.List;

/**
 * 使用 jdbc 关联查询  继承 该抽象类 要求
 *
 * 2.实现getListParam(t) 返回 查询list的 sql 和参数集合（避免sql注入、采用preparedStatement 参数化 查询）
 * 3.sql 建议最好参数化
 * 4.实现 getRowMapper  返回 bean映射器 rowMapper
 * 5.调用分页接口、Pageable 必须有排序
 */
@Slf4j
public abstract class AbsPageDao<T> {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    protected abstract ListParam getListParam(T t);

    protected abstract RowMapper<T> getRowMapper();


    protected  JdbcTemplate getJdbcTemplate(){
        return jdbcTemplate;
    }


    /**
     * 分页 接口
     *
     * @param t
     * @param pageable
     * @return
     */
    public Page<T> findPage(T t, Pageable pageable) {
        ListParam listParam = getListParam(t);
        long total = getTotal(listParam);
        List<T> contentList = getContentList(listParam, pageable);

        return new PageImpl<T>(contentList, pageable, total);
    }

    /**
     * 获取分页 list
     *
     * @param listParam
     * @param pageable
     * @return
     */
    private List<T> getContentList(ListParam listParam, Pageable pageable) {
        //分页 list sql
        int offset = pageable.getOffset();
        int pageSize = pageable.getPageSize();
        List<Object> params = listParam.getParams();
        Iterator<Sort.Order> iterator = pageable.getSort().iterator();
        StringBuilder sb = new StringBuilder(" order by ");
        while (iterator.hasNext()) {
            Sort.Order order = iterator.next();
            sb.append(order.getProperty()).append(" ").append(order.getDirection()).append(",");
        }
        sb.setLength(sb.length() - 1);
        //limit
        sb.append(" limit ? ").append(" offset ? ");

        String listSql = listParam.listSql + sb.toString();
        params.add(pageSize);
        params.add(offset);
        log.info("分页列表查询sql:{}",listSql);
        List<T> list = getJdbcTemplate().query(listSql, params.toArray(), getRowMapper());

        return list;
    }


    /**
     * total 获取
     *
     * @param listParam
     * @return
     */
    private long getTotal(ListParam listParam) {
        StringBuilder sb = new StringBuilder(" select count(1) ");
        String listSql = listParam.listSql;
        sb.append(listSql, listSql.toLowerCase().indexOf("from"), listSql.length());
        String totalSql = sb.toString();
        log.info("分页total查询sql:{}",totalSql);
        return getJdbcTemplate().queryForObject(totalSql, listParam.getParams().toArray(), Long.class);
    }




    @Data
    @AllArgsConstructor
    protected static class ListParam {
        private String listSql;
        private List<Object> params;

    }

    protected NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(){
        return new NamedParameterJdbcTemplate(getJdbcTemplate());
    }


}
