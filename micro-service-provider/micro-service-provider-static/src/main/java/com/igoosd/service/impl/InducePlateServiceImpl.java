package com.igoosd.service.impl;

import com.igoosd.domain.InducePlate;
import com.igoosd.repository.InducePlateRepository;
import com.igoosd.service.InducePlateService;
import com.igoosd.util.AbsCommonService;
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
public class InducePlateServiceImpl extends AbsCommonService<InducePlate, Long> implements InducePlateService {

    @Autowired
    private InducePlateRepository inducePlateRepository;


    @Override
    protected List<Predicate> getPredicateList(InducePlate inducePlate, Root<InducePlate> root, CriteriaBuilder cb) {
        List<Predicate> list = new ArrayList<>(3);
        if (!StringUtils.isEmpty(inducePlate.getName())) {
            list.add(cb.like(root.get("name"), "%" + inducePlate.getName() + "%"));
        }
        if (!StringUtils.isEmpty(inducePlate.getCode())) {
            list.add(cb.like(root.get("code"), "%" + inducePlate.getCode() + "%"));
        }
        if (inducePlate.getLevel() != null) {
            list.add(cb.equal(root.get("level"), inducePlate.getLevel()));
        }
        return list;
    }

    @Override
    protected JpaSpecificationExecutor<InducePlate> getJpaSpecificationExecutor() {
        return this.inducePlateRepository;
    }

    @Override
    protected JpaRepository<InducePlate, Long> getRepository() {
        return this.inducePlateRepository;
    }
}
