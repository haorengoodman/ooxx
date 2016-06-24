package com.gt.service;

import com.gt.entity.User;


/**
 * Created by journal on 2016/5/30.
 */
public interface UserService extends BasicService<User>{
    User findByName(String username);
}
