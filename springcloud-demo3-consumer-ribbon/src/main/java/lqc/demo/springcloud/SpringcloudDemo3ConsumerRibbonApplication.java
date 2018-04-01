package lqc.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

import lqc.demo.config.RibbonConfiguration;
import lqc.demo.springcloud.annotation.ExcludeComponentScan;

/*
 * 指定有这个注解的包都不扫描，防止ribbonClient和SpringBootApplication重复扫描
 */
//@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value=ExcludeComponentScan.class)})
@SpringBootApplication
// @EnableEurekaClient
@EnableDiscoveryClient	// 注册服务，通用eureka、zookeeper都可以用
/*
 * microservices-provider-user使用RibbonConfiguration类的配置，规则从默认（轮询）改为随机
 * 
 * 这里还有一个微服务B,对于microservices-provider-user使用随机，对于B使用默认。由于这个配置类不在
 * @ComponentScan下，所以2种策略对应2种微服务
 */
// 不添加配置类的话不用加@RibbonClient注解
@RibbonClient(name="microservices-provider-user", configuration=RibbonConfiguration.class)
public class SpringcloudDemo3ConsumerRibbonApplication {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringcloudDemo3ConsumerRibbonApplication.class, args);
	}
}
