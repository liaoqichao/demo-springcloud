package lqc.demo.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@MapperScan("lqc.demo.springcloud.*")
@EnableTransactionManagement
public class SpringcloudMovieUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudMovieUserApplication.class, args);
	}
}
