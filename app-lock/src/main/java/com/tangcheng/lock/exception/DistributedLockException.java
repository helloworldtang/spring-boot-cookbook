package com.tangcheng.lock.exception;

/**
 * @author: cheng.tang
 * @date: 2019/11/4
 * @see
 * @since
 */
public class DistributedLockException extends RuntimeException {

    public DistributedLockException() {
    }

    public DistributedLockException(String message) {
        super(message);
    }

    public DistributedLockException(String message, Throwable cause) {
        super(message, cause);
    }

    public DistributedLockException(Throwable cause) {
        super(cause);
    }

    public DistributedLockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
