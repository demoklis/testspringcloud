package com.demoklis.eurekaclient;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

//@Component
public class EventListenter implements ApplicationListener<NewEvent> {
    @Override
    @Async
    public void onApplicationEvent(final NewEvent newEvent) {
       System.out.println("start ---todo");
                long startTime=System.nanoTime();
                try {
                    Thread.sleep(2000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Object source=   newEvent.getSource();
                System.out.println("---message---"+source.toString());
                System.out.println("all used time---"+(System.nanoTime()-startTime));


    }
}
