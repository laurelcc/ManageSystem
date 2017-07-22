package com.ms.repository;

import com.ms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by soong on 2017/7/22.
 */
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findAll();
}
