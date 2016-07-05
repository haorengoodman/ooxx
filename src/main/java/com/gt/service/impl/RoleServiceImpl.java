package com.gt.service.impl;

import com.gt.entity.Role;
import com.gt.repository.RoleRepository;
import com.gt.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by journal on 2016/6/30.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleRepository roleRepository;

    @Override
    public Role insert(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void delete(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public Role modify(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role find(Long id) {
        return roleRepository.findOne(id);
    }
}
