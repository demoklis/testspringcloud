package com.demoklis.service_feign;

import org.springframework.stereotype.Component;

@Component
public class FeignservicehiHystrix implements Feignservicehi {

	@Override
	public String sayHiFromClientOne(String name) {
		return "sorry "+name;
	}

}
