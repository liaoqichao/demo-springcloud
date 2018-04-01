package lqc.demo.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableEurekaClient	// 设置为eureka客户端，只有eureka可用，例如zookeeper就不可以
@EnableDiscoveryClient	// 注册服务，通用eureka、zookeeper都可以用
@EnableTransactionManagement //如果mybatis中service实现类中加入事务注解，需要此处添加该注解
@MapperScan("lqc.demo.springcloud.*")
public class SpringcloudDemo2EurekaClientProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudDemo2EurekaClientProviderApplication.class, args);
	}
}
