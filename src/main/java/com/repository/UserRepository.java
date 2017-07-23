package com.repository;

import com.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by soong on 2017/7/22.
 */
public interface UserRepository extends BaseRepository<User, String> {
    Page<User> findAll(Pageable pageable);

    User findById(String id);
    User findByUsername(String name);

    Page<User> findByUsername(String name, Pageable pageable);
}
