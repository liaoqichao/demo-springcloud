package lqc.demo.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.AsyncRestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@MapperScan("lqc.demo.springcloud.*")
@EnableTransactionManagement
public class SpringcloudMovieMovieApplication {

	@Bean
	@LoadBalanced
	public AsyncRestTemplate getAsyncRestTemplate() {
		return new AsyncRestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringcloudMovieMovieApplication.class, args);
	}
}
