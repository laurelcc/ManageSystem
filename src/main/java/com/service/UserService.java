package com.service;

import com.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by soong on 2017/7/22.
 */
@Service
public class UserService {
    @Resource
    protected UserRepository userRepository;

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
