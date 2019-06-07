package com.igoosd.repository;

import com.igoosd.domain.SimCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SimCardRepository extends JpaRepository<SimCard,Long>, JpaSpecificationExecutor<SimCard> {


}
