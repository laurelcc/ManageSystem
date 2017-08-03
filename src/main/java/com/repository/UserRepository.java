package com.repository;

import com.model.XUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by soong on 2017/7/22.
 */
public interface UserRepository extends BaseRepository<XUser, String> {
    Page<XUser> findAll(Pageable pageable);

    XUser findById(String id);
    XUser findByUsername(String name);

    Boolean existsByUsername(String name);

    Boolean existsByUsernameAndAlias(String name, String alias);

    Page<XUser> findByUsernameContains(String name, Pageable pageable);
}
