package lqc.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
public class SpringcloudDemo11FeignHystrixConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudDemo11FeignHystrixConsumerApplication.class, args);
	}
}
