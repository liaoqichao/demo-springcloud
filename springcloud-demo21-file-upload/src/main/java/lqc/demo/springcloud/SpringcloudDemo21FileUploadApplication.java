package lqc.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // yml配置了，但是没加这个注解也会注册到eureka服务器
public class SpringcloudDemo21FileUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudDemo21FileUploadApplication.class, args);
	}
}
