package com.demoklis.test;

/**
 * 获取解锁后需要处理的逻辑
 * @author demoklis
 *
 * @param <T>
 */
public interface AquiredLockWorker<T> {

	T invokerAfterLockAquire()throws Exception;
}
