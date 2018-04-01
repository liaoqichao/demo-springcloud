package lqc.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringcloudDemo32ConfigClientAutoRefreshApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudDemo32ConfigClientAutoRefreshApplication.class, args);
	}
}
