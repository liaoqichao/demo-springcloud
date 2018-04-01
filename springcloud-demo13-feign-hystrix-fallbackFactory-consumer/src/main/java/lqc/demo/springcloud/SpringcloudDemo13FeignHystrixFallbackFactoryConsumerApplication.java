package lqc.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableCircuitBreaker
public class SpringcloudDemo13FeignHystrixFallbackFactoryConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudDemo13FeignHystrixFallbackFactoryConsumerApplication.class, args);
	}
}
