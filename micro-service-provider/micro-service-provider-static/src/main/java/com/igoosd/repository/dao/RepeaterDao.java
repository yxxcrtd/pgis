package com.igoosd.repository.dao;

import com.igoosd.domain.Repeater;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RepeaterDao extends AbsPageDao<Repeater> {

    private static final String listSql = " select " +
            "t.id as id," +
            "t.code as code," +
            "t.gateway_id as gatewayId," +
            "t.device_status as deviceStatus," +
            "t.remark as remark," +
            "t.create_time as createTime," +
            "s.code as gatewayCode " +
            " from t_repeater t " +
            " left join t_gateway s on s.id = t.gateway_id " +
            " where 1 = 1 ";

    @Override
    protected ListParam getListParam(Repeater repeater) {

        StringBuilder sb = new StringBuilder(listSql);
        List<Object> params = new ArrayList<>(2);
        if (!StringUtils.isEmpty(repeater.getCode())) {
            sb.append(" and t.code like ? ");
            params.add("%" + repeater.getCode() + "%");
        }
        if (repeater.getGatewayId() != null) {
            sb.append(" and t.gateway_id =  ? ");
            params.add(repeater.getGatewayId());
        }
        return new ListParam(sb.toString(), params);
    }

    @Override
    protected RowMapper<Repeater> getRowMapper() {
        return (resultSet, i) -> {
            Repeater rp = new Repeater();
            rp.setId(resultSet.getLong("id"));
            rp.setCode(resultSet.getString("code"));
            rp.setRemark(resultSet.getString("remark"));
            rp.setDeviceStatus(resultSet.getInt("deviceStatus"));
            rp.setCreateTime(resultSet.getTimestamp("createTime"));
            rp.setGatewayId(resultSet.getLong("gatewayId"));
            rp.setGatewayCode(resultSet.getString("gatewayCode"));
            return rp;
        };
    }
}
