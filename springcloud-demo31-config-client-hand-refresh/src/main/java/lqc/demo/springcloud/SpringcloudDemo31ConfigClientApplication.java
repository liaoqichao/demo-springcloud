package lqc.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringcloudDemo31ConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudDemo31ConfigClientApplication.class, args);
	}
}
