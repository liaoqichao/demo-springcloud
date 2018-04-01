package lqc.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SpringcloudDemo29ConfigServerAuthcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudDemo29ConfigServerAuthcApplication.class, args);
	}
}
