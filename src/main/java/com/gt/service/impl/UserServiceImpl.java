package com.gt.service.impl;

import com.gt.entity.User;
import com.gt.repository.UserRepository;
import com.gt.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by journal on 2016/5/30.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Resource
    private UserRepository userRepository;

    @Override
    public User insert(User user) {
        User user1 = new User();
        user1.setUsername("c");
        userRepository.save(user1);
//        int i = 1/0;

        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User modify(User user) {
        return userRepository.save(user);
    }

    @Override
    public User find(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findByName(String username) {
        return userRepository.findByName(username);
    }
}
