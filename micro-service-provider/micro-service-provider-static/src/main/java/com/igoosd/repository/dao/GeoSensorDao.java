package com.igoosd.repository.dao;

import com.igoosd.domain.GeoSensor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GeoSensorDao extends AbsPageDao<GeoSensor> {

    private static final String listSql = " select " +
            "t.id as id," +
            "t.create_time as createTime," +
            "t.device_status as deviceStatus," +
            "t.battery_capacity as batteryCapacity," +
            "t.code as code," +
            "t.gateway_id as gatewayId," +
            "t.type as type," +
            "s.code as gatewayCode" +
            " from t_geo_sensor t " +
            " left join t_gateway s on s.id = t.gateway_id " +
            " where 1 = 1 ";

    @Override
    protected ListParam getListParam(GeoSensor sensor) {
        StringBuilder sb = new StringBuilder(listSql);
        List<Object> list = new ArrayList<>(2);
        if (!StringUtils.isEmpty(sensor.getCode())) {
            sb.append(" and t.code like ? ");
            list.add("%" + sensor.getCode() + "%");
        }
        if(sensor.getGatewayId()!= null){
            sb.append(" and t.gateway_id = ? ");
            list.add(sensor.getGatewayId());
        }
        return new ListParam(sb.toString(),list);
    }

    @Override
    protected RowMapper<GeoSensor> getRowMapper() {
        return (resultSet, i) -> {
            GeoSensor sensor = new GeoSensor();
            sensor.setId(resultSet.getLong("id"));
            sensor.setCreateTime(resultSet.getTimestamp("createTime"));
            sensor.setDeviceStatus(resultSet.getInt("deviceStatus"));
            sensor.setBatteryCapacity(resultSet.getInt("batteryCapacity"));
            sensor.setCode(resultSet.getString("code"));
            sensor.setGatewayId(resultSet.getLong("gatewayId"));
            sensor.setGatewayCode(resultSet.getString("gatewayCode"));
            sensor.setType(resultSet.getInt("type"));
            return sensor;
        };
    }
}
