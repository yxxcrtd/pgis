package com.igoosd.repository;

import com.igoosd.domain.InducePlate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InducePlateRepository extends JpaRepository<InducePlate,Long>, JpaSpecificationExecutor<InducePlate> {
}
