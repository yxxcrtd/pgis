package com.igoosd.repository;

import com.igoosd.domain.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program,Long>, JpaSpecificationExecutor<Program> {


    /**
     * 查询最新发布的节目
     * @param programIds
     * @return
     */
    @Query( nativeQuery = true,value = " select * from  t_program t where t.id in (:ids) and t.release_time is not null and t.status = 2  order by t.release_time desc limit 1 ")
    Program getLatestReleaseProgram(@Param("ids") List<Long> programIds);
}
