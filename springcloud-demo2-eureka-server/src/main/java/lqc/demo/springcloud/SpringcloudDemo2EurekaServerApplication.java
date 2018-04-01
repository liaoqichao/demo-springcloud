package lqc.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // 配置为eureka server
public class SpringcloudDemo2EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudDemo2EurekaServerApplication.class, args);
	}
}
