package com.demoklis.config_client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
//@EnableEurekaClient
@EnableDiscoveryClient
@RefreshScope
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
    
    @Value("${foo}")
    private String foo;
    
    @RequestMapping(value="/hi")
    public String hi() {
    	return foo;
    }
    
    @RequestMapping(value="/health")
    public String health() {
    	return "client";
    }
}
