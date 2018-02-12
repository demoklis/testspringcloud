package com.demoklis.test;
/**
 * 获取锁管理类
 * @author demoklis
 *
 */
public interface DistributedLocker {

	<T> T lock(String resourceName,AquiredLockWorker<T> worker)throws UnableToAquireLockException,Exception;
	
	<T> T lock(String resourceName,AquiredLockWorker<T> worker,int lockTime)throws UnableToAquireLockException,Exception;
}
