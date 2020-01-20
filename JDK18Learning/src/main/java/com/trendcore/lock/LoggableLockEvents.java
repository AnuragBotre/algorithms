package com.trendcore.lock;

public interface LoggableLockEvents {


    void lockAcquired(Thread acquiredByThread,String resourceIdentifier);

    void releasedLock(Thread acquiredByThread,String resourceIdentifier);

    void threadParked(Thread parkedThread,String resourceIdentifier);

    void releasedSharedLock(Thread current, String resourceIdentifier);

    void lockAcquiredShared(Thread current, String resourceIdentifier);
}
