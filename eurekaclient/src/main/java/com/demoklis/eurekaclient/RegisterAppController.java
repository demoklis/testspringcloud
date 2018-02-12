package com.demoklis.eurekaclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class RegisterAppController {
	@Value("${server.port}")
	String port;
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/hi")
	@HystrixCommand(fallbackMethod="postHi")
	public String home(@RequestParam String name) {
		return "hi" + name + ",i am from port :"+port;
	}
	
	@RequestMapping(value="/postHi",method=RequestMethod.POST)
	public String postHi(@RequestParam String name) {
		return "hi " + name + " ,i am from port :"+port+",this is postHi";
	}

	@RequestMapping("/miya")
	public String info() {
		return restTemplate.getForObject("http://localhost:8764/miya", String.class);
	}
	
	@RequestMapping("/health")
	public String health() {
		return "hello";
	}
}
