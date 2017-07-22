package com.repository;

import com.model.User;

import java.util.List;

/**
 * Created by soong on 2017/7/22.
 */
public interface UserRepository extends BaseRepository<User, String> {
    List<User> findAll();
    User findById(String id);
}
