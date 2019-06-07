package com.igoosd.repository.dao;

import com.igoosd.domain.ControlCard;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 控制卡
 */
@Repository
public class ControlCardDao extends AbsPageDao<ControlCard> {


    private static final String listSql = " select " +
            "t.id as id," +
            "t.code as code," +
            "t.device_status as deviceStatus," +
            "t.sim_card_id as simCardId," +
            "t.init_status as initStatus," +
            "t.remark as remark," +
            "t.create_time as createTime, " +
            "s.phone_number as phoneNumber " +
            " from t_control_card t " +
            " left join t_sim_card s on s.id = t.sim_card_id " +
            " where 1=1 ";


    @Override
    protected ListParam getListParam(ControlCard controlCard) {
        List<Object> params = new ArrayList<>(2);
        StringBuilder sb = new StringBuilder(listSql);
        if (!StringUtils.isEmpty(controlCard.getCode())) {
            sb.append(" and t.code like ? ");
            params.add("%" + controlCard.getCode() + "%");
        }
        return new ListParam(sb.toString(),params);
    }

    @Override
    protected RowMapper<ControlCard> getRowMapper() {
        return (resultSet, i) -> {
            ControlCard cc = new ControlCard();
            cc.setId(resultSet.getLong("id"));
            cc.setCode(resultSet.getString("code"));
            cc.setDeviceStatus(resultSet.getInt("deviceStatus"));
            cc.setInitStatus(resultSet.getInt("initStatus"));
            cc.setSimCardId(resultSet.getLong("simCardId"));
            cc.setCreateTime(resultSet.getTimestamp("createTime"));
            cc.setPhoneNumber(resultSet.getString("phoneNumber"));
            cc.setRemark(resultSet.getString("remark"));
            return cc;
        };
    }
}
