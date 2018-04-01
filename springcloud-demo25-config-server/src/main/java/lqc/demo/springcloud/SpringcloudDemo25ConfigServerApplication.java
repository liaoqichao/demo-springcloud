package lqc.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SpringcloudDemo25ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudDemo25ConfigServerApplication.class, args);
	}
}
