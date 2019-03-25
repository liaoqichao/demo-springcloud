package lqc.demo.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
 * 服务提供者
 */
@SpringBootApplication
@EnableTransactionManagement //如果mybatis中service实现类中加入事务注解，需要此处添加该注解
@MapperScan("lqc.demo.springcloud.*")
public class SpringcloudDemo1ProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudDemo1ProviderApplication.class, args);
	}

}
