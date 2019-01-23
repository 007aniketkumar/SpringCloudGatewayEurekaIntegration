package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudGatewayEurekaIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudGatewayEurekaIntegrationApplication.class, args);
	}
	
	
	 /*@Bean
	    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
	        return builder.routes()
	                .route(r ->
	                        r.path("/java/**")
	                                .filters(
	                                        f -> f.stripPrefix(1)
	                                )
	                                .uri("http://httpbin.org:80")
	                )
	                .build();
	    }*/
	 
	 
	 @Bean
	    
	 public RouteLocator routeLocator(RouteLocatorBuilder builder) {
	        return builder.routes().
	        		route(r->
	        		 r.path("/student/**")
	        		 .and().query("anyFiltername", "abc")// this can be any filter say a student id ,which helps in routinf of service request
				
	        		 .filters(f-> f.rewritePath("/student/(?<segment>.*)", "/getStudentDetails/$\\{segment}"))
	        		 
	        		.uri("lb://student")
	        		 )
			.build();
	        		 
	                
	    }
	 
	 
}
