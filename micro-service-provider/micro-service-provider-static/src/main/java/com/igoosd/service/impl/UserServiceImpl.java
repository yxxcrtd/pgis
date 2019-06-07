package com.igoosd.service.impl;

import com.igoosd.domain.User;
import com.igoosd.repository.UserRepository;
import com.igoosd.service.UserService;
import com.igoosd.util.AbsCommonService;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl extends AbsCommonService<User, Long> implements UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<User> findPageList(User user, Pageable pageable) {
        return userRepository.findAll((root, query, cb) -> {
            Path<String> username = root.get("username");
            Path<String> phone = root.get("phone");
            Path<String> realName = root.get("realName");
            List<Predicate> list = Lists.newArrayList();
            if (!StringUtils.isEmpty(user.getUsername())) {
                list.add(cb.like(username, "%" + user.getUsername() + "%"));
            }
            if (!StringUtils.isEmpty(user.getPhone())) {
                list.add(cb.like(phone, "%" + user.getPhone() + "%"));
            }
            if (!StringUtils.isEmpty(user.getRealName())) {
                list.add(cb.like(realName, "%" + user.getRealName() + "%"));
            }
            query.where(list.toArray(new Predicate[]{})).orderBy(new OrderImpl(root.get("createTime"), false));
            return null;
        }, pageable);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public Map<String, String> getHash(boolean needBlank) {
        return getHash("id", "username", needBlank);
    }

    @Override
    protected JpaRepository<User, Long> getRepository() {
        return this.userRepository;
    }

}
