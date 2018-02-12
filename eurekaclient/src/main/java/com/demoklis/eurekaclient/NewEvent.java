package com.demoklis.eurekaclient;

import org.springframework.context.ApplicationEvent;
public class NewEvent extends ApplicationEvent{
	public NewEvent(Object source) {
        super(source);
    }
}
