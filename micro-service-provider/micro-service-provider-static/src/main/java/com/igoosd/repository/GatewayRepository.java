package com.igoosd.repository;

import com.igoosd.domain.Gateway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GatewayRepository extends JpaRepository<Gateway,Long>, JpaSpecificationExecutor<Gateway> {


}
