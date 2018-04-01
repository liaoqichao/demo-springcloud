package lqc.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

import lqc.demo.springcloud.annotation.ExcludeComponentScan;
/*
 *  添加这个自定义的注解，启动类添加@ComponentScan(exclude)指定有ExcludeComponentScan注解的包不扫描
 */
//@ExcludeComponentScan 
@Configuration
/*
 * name: 一定要是在eureka server注册的微服务的名称
 * configuration: 这里使用当前类。 configuration指定的类必须是有@Configuration注解，
 * 	且不能有@ComponentScan注解。如果指定的类有@ComponentScan或SpringBootApplication注解，
 * 	那么这个配置范围就配置到所有配置了configuration等于这类的ribbon client。
 *  所以@RibbonClient不能放在SpringBootApplication所在的包和SpringBootApplication的子包
 *  
 *  configuration默认使用的配置是@RibbonClientConfiguration
 */
public class RibbonConfiguration {

	@Autowired
	private IClientConfig config;
	@Bean
	public IRule ribbonRule(IClientConfig config) {
		// RibbonClientConfiguration的ribbonRule是使用忽视zone的rule
		return new RandomRule();
	}
	
//	@Bean
//	public IRule ribbonRule() {
//		// RibbonClientConfiguration的ribbonRule是使用忽视zone的rule
//		return new RandomRule();
//	}
}
