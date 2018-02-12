package com.demoklis.test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class RedisLockerController {
	@Autowired
	RedisLocker distributedLocker;
	
	@RequestMapping("/redLock")
	public String testRedLock() throws Exception{
		CountDownLatch startSignal=new CountDownLatch(1);
		CountDownLatch doneSignal=new CountDownLatch(5);
		for(int i=0;i<5000;i++) {
			new Thread(new Worker(startSignal,doneSignal)).start();;
		}
		startSignal.countDown();
		doneSignal.await();
		System.out.println("All processors done. Shutdown connection");
		return "redLock";
	}
	
	class Worker implements Runnable{
		private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;
        
        Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }  
        
		@Override
		public void run() {
			try {
                startSignal.await();
                distributedLocker.lock("test",new AquiredLockWorker<Object>() {
					@Override
					public Object invokerAfterLockAquire() throws Exception {
						doTask();
                        return null;
					}

                    

                });
            }catch (Exception e){

            }
		}
		
		void doTask() {
            System.out.println(Thread.currentThread().getName() + " start");
            Random random = new Random();
            int _int = random.nextInt(200);
            System.out.println(Thread.currentThread().getName() + " sleep " + _int + "millis");
            try {
                Thread.sleep(_int);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end");
            doneSignal.countDown();
        }
	}
}
