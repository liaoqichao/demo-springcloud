package lqc.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class SpringcloudDemo14HystrixDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudDemo14HystrixDashboardApplication.class, args);
	}
}
