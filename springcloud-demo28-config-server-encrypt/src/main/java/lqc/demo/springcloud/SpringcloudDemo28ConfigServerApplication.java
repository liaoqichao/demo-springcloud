package lqc.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SpringcloudDemo28ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudDemo28ConfigServerApplication.class, args);
	}
}
