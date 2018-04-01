package lqc.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringcloudMovieEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudMovieEurekaServerApplication.class, args);
	}
}
