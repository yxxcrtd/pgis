package com.igoosd.repository.dao;

import com.igoosd.domain.Parking;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ParkingDao extends AbsPageDao<Parking> {

    private static final String col_segment = "select t.id as id," +
            "t.name as name," +
            "t.code as code," +
            "t.type as type," +
            "t.access_mode as accessMode," +
            "t.lot_total_count as lotTotalCount," +
            "t.lot_remain_count as lotRemainCount," +
            "t.remark as remark," +
            "t.location as location," +
            "t.create_time as createTime," +
            "t.unit_name as unitName," +
            "t.media_screen_id as mediaScreenId," +
            "r.name as mediaScreenName " +
            " from t_parking t " +
            " left join t_screen r on r.id = t.media_screen_id ";

    private static final String  list_sql = col_segment + " where 1 = 1 ";

    public List<Parking> findParkingListByInducePlateId(Long inducePlateId){
        String sql = col_segment +
                " join t_parking_screen ps on ps.parking_id = t.id " +
                " join t_screen s on s.id = ps.screen_id and s.induce_plate_id = ? ";
        return getJdbcTemplate().query(sql,getRowMapper(),inducePlateId);
    }

    @Override
    protected ListParam getListParam(Parking parking) {
        List<Object> params = new ArrayList<>(2);
        StringBuilder sb = new StringBuilder(list_sql);
        if (!StringUtils.isEmpty(parking.getName())) {
            params.add("%" + parking.getName() + "%");
            sb.append(" and t.name like ? ");
        }
        if (!StringUtils.isEmpty(parking.getCode())) {
            params.add("%" + parking.getCode() + "%");
            sb.append(" and t.code like ? ");
        }
        return new ListParam(sb.toString(),params);
    }

    @Override
    protected RowMapper<Parking> getRowMapper(){
        return (resultSet, i) -> {
            Parking parking = new Parking();
            parking.setId(resultSet.getLong("id"));
            parking.setName(resultSet.getString("name"));
            parking.setCode(resultSet.getString("code"));
            parking.setType(resultSet.getInt("type"));
            parking.setAccessMode(resultSet.getInt("accessMode"));
            parking.setLotRemainCount(resultSet.getInt("lotRemainCount"));
            parking.setLotTotalCount(resultSet.getInt("lotTotalCount"));
            parking.setRemark(resultSet.getString("remark"));
            parking.setLocation(resultSet.getString("location"));
            parking.setCreateTime(resultSet.getTimestamp("createTime"));
            parking.setUnitName(resultSet.getString("unitName"));
            parking.setMediaScreenId(resultSet.getLong("mediaScreenId"));
            parking.setMediaScreenName(resultSet.getString("mediaScreenName"));
            return parking;
        };
    }
}
