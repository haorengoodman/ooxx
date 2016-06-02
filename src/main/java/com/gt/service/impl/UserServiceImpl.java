package com.gt.service.impl;

import com.gt.entity.User;
import com.gt.repository.UserRepository;
import com.gt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by journal on 2016/5/30.
 */
@Service
public class UserServiceImpl implements UserService{

    @Qualifier("userRepository")
    @Autowired
    private UserRepository repository;

    @Transactional
    public User insert(User user) {
        User user1 = new User();
        user1.setDes("xxxx");
        repository.save(user1);
        //int i = 1/0;
        return repository.save(user);
    }
}
