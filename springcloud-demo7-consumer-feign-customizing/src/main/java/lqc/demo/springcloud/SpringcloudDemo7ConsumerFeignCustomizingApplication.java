package lqc.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SpringcloudDemo7ConsumerFeignCustomizingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudDemo7ConsumerFeignCustomizingApplication.class, args);
	}
}
