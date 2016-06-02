package com.gt.repository;

import com.gt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by journal on 2016/5/30.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
