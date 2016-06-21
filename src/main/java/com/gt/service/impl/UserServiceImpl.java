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

    @Override
    public User insert(User user) {
        return repository.save(user);
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }

    @Override
    public User modify(User user) {
        return repository.save(user);
    }

    @Override
    public User find(Long id) {
        return repository.findOne(id);
    }

}
