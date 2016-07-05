package com.gt.service.impl;

import com.gt.entity.Permission;
import com.gt.repository.PermissionRepository;
import com.gt.service.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by journal on 2016/6/30.
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService{

    @Resource
    private PermissionRepository permissionRepository;

    @Override
    public Permission insert(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public void delete(Permission permission) {
        permissionRepository.delete(permission);
    }

    @Override
    public Permission modify(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Permission find(Long id) {
        return permissionRepository.findOne(id);
    }
}
