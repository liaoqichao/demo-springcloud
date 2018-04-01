package lqc.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import lqc.demo.springcloud.filter.PreZuulFilter;

@SpringBootApplication
@EnableZuulProxy
public class SpringcloudDemo24GatewayZuulFilterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudDemo24GatewayZuulFilterApplication.class, args);
	}
	
	@Bean
	public PreZuulFilter getPreZuulFilter() {
		return new PreZuulFilter();
	}
}
