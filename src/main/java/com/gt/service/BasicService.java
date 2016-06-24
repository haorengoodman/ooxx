package com.gt.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Created by journal on 2016/6/21.
 */
@Transactional
public interface BasicService<T> {
    T insert(T t);
    void delete(T t);
    T modify(T t);
    T find(Long id);
}
