package com.demoklis.service_feign;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
@RestController
public class HiController {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	Feignservicehi feignservicehi;
	@RequestMapping(value = "/hi",method = RequestMethod.GET)
	public String sayHi(@RequestParam String name) throws JsonProcessingException {
		return feignservicehi.sayHiFromClientOne(name);
	}
	
	@RequestMapping(value = "/postHi1",method = RequestMethod.GET)
	public String postHi1(@RequestParam String name) throws JsonProcessingException {
		String t = String.valueOf(new Date().getTime());
		String v = String.valueOf(1);
		ObjectMapper mapper = new ObjectMapper();
		Map<String,Object> requestMap=new HashMap<>();
		
		String body = mapper.writeValueAsString(requestMap);
	    String encrytString1 = Hashing.md5().hashString(body + t, Charsets.UTF_8).toString();
	    String encrytString2 = Hashing.md5().hashString(encrytString1 + v, Charsets.UTF_8).toString();
		
		HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.add("t", t);	
        headers.add("v", v);
        headers.add("ip", "0.0.0.0");
        headers.add("cv", "windows");
        headers.add("di", "1234567890");
        headers.add("pf", "windows");
        headers.add("sign", encrytString2);
        
        HttpEntity<Map<String, Object>> formEntity = new HttpEntity<Map<String, Object>>(requestMap, headers);
        return restTemplate.postForObject("http://service-zuul/api-b/postHi?name="+name+"&token=111", formEntity, String.class);
	}
	
	@RequestMapping(value = "/postHi",method = RequestMethod.POST)
	public String postHi(@RequestParam String name) throws JsonProcessingException {
		return feignservicehi.sayHiFromClientOne(name);
	}
	
	@RequestMapping(value = "/local",method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod="postHi")
	public String local(@RequestParam String name) throws JsonProcessingException {
		return "#"+name;
	}
}
