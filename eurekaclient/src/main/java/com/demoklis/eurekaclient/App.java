package com.demoklis.eurekaclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
//@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class);
    }
    
    @Autowired
    private RestTemplate restTemplate; 
    
    @Bean
    public RestTemplate getRestTemplate() {
    	RestTemplate rest = new RestTemplate();
    	return rest;
    }
}
