package com.tangcheng.app.dao.common;

public interface DistributedLock {
    /**
     * 加锁
     *
     * @param keyWithBizId 包含 能够让锁最小粒度的，具有区分度的标识
     * @param maxWaitTime  最大的等待时间
     * @return
     */
    boolean acquireLockWithTimeout(String keyWithBizId, int maxWaitTime);

    /**
     * 加锁
     *
     * @param keyWithBizId   包含 能够让锁最小粒度的，具有区分度的标识
     * @param acquireTimeout 最大等待时间
     * @param lockTimeout    锁自动释放时间(秒)
     * @return
     */
    boolean acquireLockWithTimeout(String keyWithBizId, int acquireTimeout, int lockTimeout);

    /**
     * 释放锁
     *
     * @param keyWithBizId 包含 能够让锁最小粒度的，具有区分度的标识
     */
    void releaseLock(String keyWithBizId);
}
