package com.demoklis.service_ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;

@Service
public class HelloService {

	@Autowired
	private RestTemplate restTemplate;
	@HystrixCommand(fallbackMethod="hiSorry")
	public String hiServer(String name) {
		return restTemplate.getForObject("http://service-hi/hi?name="+name, String.class);
	}
	
	public String hiSorry(String name) {
		return "hi,"+name+",sorry,error!";
	}
}
