package com.igoosd.repository;

import com.igoosd.domain.FlowInfoData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FlowInfoDataRepository extends JpaRepository<FlowInfoData,Long>, JpaSpecificationExecutor<FlowInfoData> {

}
