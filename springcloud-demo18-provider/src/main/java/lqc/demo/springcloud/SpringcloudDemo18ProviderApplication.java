package lqc.demo.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableHystrix
@EnableDiscoveryClient
@EnableTransactionManagement 
@EnableCircuitBreaker
@MapperScan("lqc.demo.springcloud.*")
public class SpringcloudDemo18ProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudDemo18ProviderApplication.class, args);
	}
}
