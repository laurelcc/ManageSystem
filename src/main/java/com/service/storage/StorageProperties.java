package com.service.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by soong on 2017/7/2.
 */
@ConfigurationProperties("storage")
public class StorageProperties {
    /**
     * Folder location for storing files
     */
    private String location = "upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
