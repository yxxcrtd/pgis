package com.igoosd.repository.dao;

import com.igoosd.domain.Program;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProgramDao extends AbsPageDao<Program> {

    private static final String listSql = " select " +
            "t.id as id," +
            "t.name as name," +
            "t.content as content," +
            "t.user_id as userId," +
            "t.status as status," +
            "t.release_time as releaseTime," +
            "t.remark as remark," +
            "t.create_time as createTime," +
            "t.update_time as updateTime," +
            "t.show_type as showType," +
            "t.interval as interval," +
            "t.step as step," +
            "t.width as width," +
            "t.height as height," +
            "s.username as username " +
            " from t_program t " +
            " left join t_user s on s.id=t.user_id " +
            " where 1=1 ";

    protected ListParam getListParam(Program program) {
        List<Object> params = new ArrayList<>(1);
        StringBuilder sb = new StringBuilder(listSql);
        if(!StringUtils.isEmpty(program.getName())){
            sb.append(" and t.name like ? ");
            params.add(program.getName());
        }
        return new ListParam(sb.toString(),params);
    }

    @Override
    protected RowMapper<Program> getRowMapper() {
        return (resultSet, i) -> {
            Program program = new Program();
            program.setId(resultSet.getLong("id"));
            program.setInterval(resultSet.getInt("interval"));
            program.setRemark(resultSet.getString("remark"));
            program.setContent(resultSet.getString("content"));
            program.setCreateTime(resultSet.getTimestamp("createTime"));
            program.setHeight(resultSet.getInt("height"));
            program.setShowType(resultSet.getInt("showType"));
            program.setName(resultSet.getString("name"));
            program.setReleaseTime(resultSet.getTimestamp("releaseTime"));
            program.setStatus(resultSet.getInt("status"));
            program.setStep(resultSet.getInt( "step"));
            program.setUpdateTime(resultSet.getTimestamp("updateTime"));
            program.setUserId(resultSet.getLong("userId"));
            program.setWidth(resultSet.getInt("width"));
            program.setUsername(resultSet.getString("username"));
            return program;
        };
    }

}
