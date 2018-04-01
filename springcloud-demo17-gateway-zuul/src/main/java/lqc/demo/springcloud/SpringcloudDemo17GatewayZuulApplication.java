package lqc.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class SpringcloudDemo17GatewayZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudDemo17GatewayZuulApplication.class, args);
	}
}
