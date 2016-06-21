package com.gt.repository;

import com.gt.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by journal on 2016/6/21.
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission,Long> {
}
