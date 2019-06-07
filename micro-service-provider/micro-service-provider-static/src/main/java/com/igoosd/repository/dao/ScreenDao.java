package com.igoosd.repository.dao;

import com.igoosd.domain.Screen;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class ScreenDao extends AbsPageDao<Screen> {

    private static final String listSql = " select " +
            " t.id as id," +
            " t.name as name," +
            " t.code as code," +
            " t.type as type, " +
            " t.control_card_id as controlCardId," +
            " t.induce_plate_id as inducePlateId," +
            " t.order_num as orderNum," +
            " t.create_time as createTime," +
            " t.width as width, " +
            " t.height as height, "+
            " s.name as inducePlateName," +
            " r.code as controlCardCode " +
            " from t_screen t " +
            " left join t_induce_plate s on s.id = t.induce_plate_id " +
            " left join t_control_card r on r.id = t.control_card_id " +
            " where 1 =1 ";

    @Override
    protected ListParam getListParam(Screen screen) {
        List<Object> params = new ArrayList<>(5);
        StringBuilder sb = new StringBuilder(listSql);
        if (!StringUtils.isEmpty(screen.getName())) {
            sb.append(" and t.name like ? ");
            params.add("%" + screen.getName() + "%");
        }
        if (!StringUtils.isEmpty(screen.getCode())) {
            sb.append(" and t.code like ? ");
            params.add("%" + screen.getCode() + "%");
        }
        if (screen.getType() != null) {
            sb.append(" and t.type = ? ");
            params.add(screen.getType());
        }
        if (screen.getControlCardId() != null) {
            sb.append(" and t.control_card_id = ? ");
            params.add(screen.getControlCardId());
        }
        if (screen.getInducePlateId() != null) {
            sb.append(" and t.induce_plate_id = ? ");
            params.add(screen.getInducePlateId());
        }
        return new ListParam(sb.toString(), params);

    }

    @Override
    protected RowMapper<Screen> getRowMapper() {
        return (resultSet, i) -> {
            Screen screen = new Screen();
            screen.setId(resultSet.getLong("id"));
            screen.setName(resultSet.getString("name"));
            screen.setCode(resultSet.getString("code"));
            screen.setType(resultSet.getInt("type"));
            screen.setControlCardId(resultSet.getLong("controlCardId"));
            screen.setInducePlateId(resultSet.getLong("inducePlateId"));
            screen.setCreateTime(resultSet.getTimestamp("createTime"));
            screen.setControlCardCode(resultSet.getString("controlCardCode"));
            screen.setInducePlateName(resultSet.getString("inducePlateName"));
            screen.setOrderNum(resultSet.getInt("orderNum"));
            screen.setWidth(resultSet.getInt("width"));
            screen.setHeight(resultSet.getInt("height"));
            return screen;
        };
    }


    /**
     * 根据Id集合查找屏幕列表
     *
     * @param ids
     * @return
     */
    public List<Screen> findScreenListByIds(List<Long> ids) {
        String sql = listSql + " and t.id in (:ids)";
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(getJdbcTemplate());
        Map<String, List<Long>> paramMap = new HashMap<>(1);
        paramMap.put("ids", ids);
        List<Screen> list = template.query(sql, paramMap, getRowMapper());
        return list;
    }


    public List<Screen> findL4ScreenListForParking(Long parkingId){
        String  sql  = " select " +
                "t.id as id," +
                "t.name as name," +
                "t.code as code," +
                "t.type as type, " +
                "t.control_card_id as controlCardId," +
                "t.induce_plate_id as inducePlateId," +
                "t.order_num as orderNum," +
                "t.create_time as createTime," +
                "t.width as width, " +
                "t.height as height, " +
                "s.name as inducePlateName," +
                "r.code as controlCardCode " +
                " from t_screen t " +
                "  join t_parking_screen ps on ps.parking_id = ? " +
                "  join t_induce_plate s on s.id = t.induce_plate_id and s.level = 4 " +
                "  left join t_control_card r on r.id = t.control_card_id " +
                " where 1 =1 ";
            Object[] args = new Object[1];
            args[0] = parkingId;
        return getJdbcTemplate().query(sql,args,getRowMapper());
    }

}
