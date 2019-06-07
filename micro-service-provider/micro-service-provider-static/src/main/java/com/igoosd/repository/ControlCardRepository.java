package com.igoosd.repository;

import com.igoosd.domain.ControlCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ControlCardRepository extends JpaRepository<ControlCard,Long>, JpaSpecificationExecutor<ControlCard> {


    @Query(value = "select code from ControlCard where id in (:ids)")
    List<String> findCodesByIds(@Param("ids") List<Long> ids);

}
