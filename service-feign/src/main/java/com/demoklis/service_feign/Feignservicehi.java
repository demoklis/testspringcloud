package com.demoklis.service_feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-hi" ,fallback = FeignservicehiHystrix.class)
public interface Feignservicehi {

	@RequestMapping(value = "/hi",method = RequestMethod.GET)
	public String sayHiFromClientOne(@RequestParam(value="name") String name);
}
