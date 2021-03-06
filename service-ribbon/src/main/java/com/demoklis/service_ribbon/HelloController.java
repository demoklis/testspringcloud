package com.demoklis.service_ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.ribbon.proxy.annotation.Hystrix;

@RestController
public class HelloController {

	@Autowired
	private HelloService helloService;
	
	@RequestMapping("/hi")
	public String hi(@RequestParam String name) {
		return helloService.hiServer(name);
	}
	
	@RequestMapping("/miya")
	public String miya() {
		return "i'm service-ribbon";
	}
}
