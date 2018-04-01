package lqc.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class SpringcloudDemo22GatewayZuulFileUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudDemo22GatewayZuulFileUploadApplication.class, args);
	}
}
