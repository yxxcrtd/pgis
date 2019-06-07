package com.igoosd.repository;

import com.igoosd.domain.GeoSensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface GeoSensorRepository extends JpaRepository<GeoSensor,Long>, JpaSpecificationExecutor<GeoSensor> {


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update GeoSensor t set batteryCapacity = :eq where code = :code")
    int updateGeoSensorEQ(@Param("code") String code, @Param("eq") Integer eq);


}
