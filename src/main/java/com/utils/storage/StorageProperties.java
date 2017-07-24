package com.utils.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by soong on 2017/7/2.
 */
@ConfigurationProperties("utils.storage")
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
