package lqc.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringcloudDemo8EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudDemo8EurekaServerApplication.class, args);
	}
}
