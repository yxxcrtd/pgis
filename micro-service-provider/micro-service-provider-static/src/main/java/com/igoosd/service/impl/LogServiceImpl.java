package com.igoosd.service.impl;

import com.igoosd.domain.Log;
import com.igoosd.repository.LogRepository;
import com.igoosd.service.LogService;
import com.igoosd.util.AbsCommonService;
import org.assertj.core.util.Lists;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.List;

@Service
public class LogServiceImpl extends AbsCommonService<Log, Long> implements LogService {

    @Autowired
    private LogRepository logRepository;

    protected JpaRepository<Log, Long> getRepository() {
        return this.logRepository;
    }

    public Page<Log> findPageList(Log log, Pageable pageable) {
        return logRepository.findAll((root, query, cb) -> {
            Path<String> username = root.get("username");
            Path<String> ip = root.get("ip");
            List<Predicate> list = Lists.newArrayList();
            if (!StringUtils.isEmpty(log.getUsername())) {
                list.add(cb.like(username, "%" + log.getUsername() + "%"));
            }
            if (!StringUtils.isEmpty(log.getIp())) {
                list.add(cb.like(ip, "%" + log.getIp() + "%"));
            }
            query.where(list.toArray(new Predicate[]{})).orderBy(new OrderImpl(root.get("createTime"), false));
            return null;
        }, pageable);
    }

}
