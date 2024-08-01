package com.havesweets.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator bankOfBabjiRouting(RouteLocatorBuilder routeLocatorBuilder)
	{
		return routeLocatorBuilder.routes()
				.route(p->p.path("/bankofbabji/accounts/**")
						.filters(f->f.rewritePath("/bankofbabji/accounts/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								.circuitBreaker(breaker->breaker.setName("accountsCircuitBreaker"))
								.metadata("connection_timeout",1000)
								.metadata("response_timeout", 5))
						.uri("lb://ACCOUNTS"))
				.route(p->p.path("/bankofbabji/cards/**")
						.filters(f->f.rewritePath("/bankofbabji/cards/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								.retry(retryConfig -> retryConfig.setRetries(3).setMethods(HttpMethod.GET).setExceptions(ClassCastException.class)
								.setBackoff(Duration.ofMillis(100),Duration.ofMillis(1000),2,true)))
						.uri("lb://CARDS"))
				.route(p->p.path("/bankofbabji/loans/**")
						.filters(f->f.rewritePath("/bankofbabji/loans/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://LOANS"))
				.build();
	}

}
