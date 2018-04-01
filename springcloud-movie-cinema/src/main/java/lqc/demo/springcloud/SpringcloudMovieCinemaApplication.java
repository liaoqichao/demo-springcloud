package lqc.demo.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableTransactionManagement
@MapperScan("lqc.demo.springcloud.*")
public class SpringcloudMovieCinemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudMovieCinemaApplication.class, args);
	}
}
