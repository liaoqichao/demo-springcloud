package lqc.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine
public class SpringcloudDemo15TurbineApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudDemo15TurbineApplication.class, args);
	}
}