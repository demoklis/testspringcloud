package com.demoklis.test;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class RedisLocker implements DistributedLocker {
	
	private final static String LOCKER_PREFIX="lock:";
	@Autowired
	RedissonConnector redissonConnector;

	@Override
	public <T> T lock(String resourceName, AquiredLockWorker<T> worker) throws UnableToAquireLockException, Exception {
		
		return lock(resourceName, worker, 100);
	}

	@Override
	public <T> T lock(String resourceName, AquiredLockWorker<T> worker, int lockTime)
			throws UnableToAquireLockException, Exception {
		RedissonClient rc=redissonConnector.getClient();
		RLock lock=rc.getLock(LOCKER_PREFIX+resourceName);
		boolean success=lock.tryLock(100,lockTime,TimeUnit.SECONDS);
		if(success) {
			try {
				return worker.invokerAfterLockAquire();
			}finally {
				lock.unlock();
			}
		}
		throw new UnableToAquireLockException();
	}

}
