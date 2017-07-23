package com.service;

import com.model.User;
import com.repository.UserRepository;
import org.springframework.data.domain.Example;
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

    /**
     * 用户名是否存在
     * @param username
     * @return
     */
    public boolean existsByUsername(String username){
        return existsByUsername(username, null);
    }

    /**
     * 用户名是否存在
     * @param username
     * @param excludeIds
     * @return
     */
    public boolean existsByUsername(String username, String... excludeIds){
        User user = userRepository.findByUsername(username);
        if (user != null){
            if (excludeIds != null){
                int length = excludeIds.length;
                for (int i = 0; i < length; i++) {
                    String id = excludeIds[i];
                    if (user.getId().equals(id)){
                        return false;
                    }
                }
                return true;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

}
