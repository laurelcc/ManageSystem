package com.config;

import org.springframework.data.domain.AuditorAware;

/**
 * Created by soong on 2017/7/22.
 */
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public String getCurrentAuditor() {
        return "Someone";
    }
}
