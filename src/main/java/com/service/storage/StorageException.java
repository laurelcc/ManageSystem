package com.service.storage;

/**
 * Created by soong on 2017/7/2.
 */
public class StorageException extends RuntimeException {
    public StorageException(String message){
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
