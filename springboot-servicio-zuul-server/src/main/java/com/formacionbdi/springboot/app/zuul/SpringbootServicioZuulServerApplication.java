package com.formacionbdi.springboot.app.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class SpringbootServicioZuulServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioZuulServerApplication.class, args);
	}

}

/*
Endpoint ID 'service-registry' contains invalid characters, please migrate to a valid format.
[2m2023-09-20 16:03:11.367[0;39m [33m WARN[0;39m [35m4936[0;39m [2m---[0;39m [2m[  restartedMain][0;39m [36mo.s.boot.actuate.endpoint.EndpointId    [0;39m [2m:[0;39m Endpoint ID 'hystrix.stream' contains invalid characters, please migrate to a valid format.
[2m2023-09-20 16:03:11.628[0;39m [32m INFO[0;39m [35m4936[0;39m [2m---[0;39m [2m[  restartedMain][0;39m [36mo.s.cloud.context.scope.GenericScope    [0;39m [2m:[0;39m BeanFactory id=b78ba745-3267-3633-9f27-8be0809b02da*/