package lqc.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableEurekaClient	// 设置为eureka客户端，只有eureka可用，例如zookeeper就不可以
@EnableDiscoveryClient	// 注册服务，通用eureka、zookeeper都可以用
public class SpringcloudDemo2EurekaClientConsumerApplication {

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF-8));
		return restTemplate;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringcloudDemo2EurekaClientConsumerApplication.class, args);
	}
}
