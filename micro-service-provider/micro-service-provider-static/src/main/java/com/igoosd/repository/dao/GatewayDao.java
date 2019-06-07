package com.igoosd.repository.dao;

import com.igoosd.domain.Gateway;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GatewayDao extends AbsPageDao<Gateway> {


    private static final String listSql = " select " +
            "t.id as id," +
            "t.code as code," +
            "t.sim_card_id as simCardId," +
            "t.parking_id as parkingId," +
            "t.device_status as deviceStatus," +
            "t.remark as remark," +
            "t.create_time as createTime," +
            "s.name as parkingName," +
            "r.phone_number as phoneNumber " +
            " from t_gateway t " +
            " left join t_parking s on s.id =t.parking_id " +
            " left join t_sim_card r on r.id = t.sim_card_id " +
            " where 1 = 1 ";

    @Override
    protected ListParam getListParam(Gateway gateway) {
        StringBuilder sb = new StringBuilder(listSql);
        List<Object> params = new ArrayList<>(3);
        if (!StringUtils.isEmpty(gateway.getCode())) {
            sb.append(" and t.code like ? ");
            params.add("%" + gateway.getCode() + "%");
        }
        if (gateway.getParkingId() != null) {
            sb.append(" and t.parking_id = ? ");
            params.add(gateway.getParkingId());
        }
        if (gateway.getSimCardId() != null) {
            sb.append(" and t.sim_card_id = ? ");
            params.add(gateway.getSimCardId());
        }
        return new ListParam(sb.toString(), params);
    }

    @Override
    protected RowMapper<Gateway> getRowMapper() {
        return (resultSet, i) -> {
            Gateway gateway = new Gateway();
            gateway.setId(resultSet.getLong("id"));
            gateway.setCode(resultSet.getString("code"));
            gateway.setSimCardId(resultSet.getLong("simCardId"));
            gateway.setParkingId(resultSet.getLong("parkingId"));
            gateway.setDeviceStatus(resultSet.getInt("deviceStatus"));
            gateway.setRemark(resultSet.getString("remark"));
            gateway.setCreateTime(resultSet.getTimestamp("createTime"));
            gateway.setParkingName(resultSet.getString("parkingName"));
            gateway.setPhoneNumber(resultSet.getString("phoneNumber"));
            return gateway;
        };
    }
}
