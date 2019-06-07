package com.igoosd.service.impl;

import com.igoosd.domain.SimCard;
import com.igoosd.repository.SimCardRepository;
import com.igoosd.service.SimCardService;
import com.igoosd.util.AbsCommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SimCardServiceImpl extends AbsCommonService<SimCard, Long> implements SimCardService {

    @Autowired
    private SimCardRepository simCardRepository;


    @Override
    protected List<Predicate> getPredicateList(SimCard simCard, Root<SimCard> root, CriteriaBuilder cb) {
        List<Predicate> list = new ArrayList<>(1);
        if (!StringUtils.isEmpty(simCard.getPhoneNumber())) {
            list.add(cb.like(root.get("phoneNumber"), "%" + simCard.getPhoneNumber() + "%"));
        }
        return list;
    }

    @Override
    protected JpaSpecificationExecutor<SimCard> getJpaSpecificationExecutor() {
        return simCardRepository;
    }

    @Override
    protected JpaRepository<SimCard, Long> getRepository() {
        return simCardRepository;
    }
}
