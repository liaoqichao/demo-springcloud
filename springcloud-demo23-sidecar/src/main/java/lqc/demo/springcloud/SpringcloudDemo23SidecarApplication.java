package lqc.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

@SpringBootApplication
@EnableSidecar
public class SpringcloudDemo23SidecarApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudDemo23SidecarApplication.class, args);
	}
}
