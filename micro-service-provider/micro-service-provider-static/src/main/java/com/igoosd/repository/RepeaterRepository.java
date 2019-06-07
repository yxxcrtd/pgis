package com.igoosd.repository;

import com.igoosd.domain.Repeater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepeaterRepository extends JpaRepository<Repeater,Long>, JpaSpecificationExecutor<Repeater> {


}
