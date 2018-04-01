package lqc.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
// @EnableEurekaClient
@EnableDiscoveryClient	// 注册服务，通用eureka、zookeeper都可以用
public class SpringcloudDemo4ConsumerRibbonPropertiesCustomizingApplication {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringcloudDemo4ConsumerRibbonPropertiesCustomizingApplication.class, args);
	}
}
