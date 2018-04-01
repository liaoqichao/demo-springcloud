package lqc.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;
import feign.Feign;
import feign.Logger;
import feign.slf4j.Slf4jLogger;
import lqc.demo.springcloud.user.feign.UserFeignClient;

@Configuration
public class FeignClientConfiguration {
	@Bean
	public Contract feignContract() {
		/*
		 * Error creating bean with name 'lqc.demo.springcloud.user.feign.UserFeignClient':
		 *  FactoryBean threw exception on object creation; nested exception is 
		 *  java.lang.IllegalStateException: Method findById not annotated with HTTP method 
		 *  type (ex. GET, POST)
		 * 
		 * 不能在使用SpringMVC的注解，要使用Feign的注解，@RequestLine
		 */
		return new feign.Contract.Default();
	}
	
	@Bean
	public Logger.Level feignLoggerLevel() {
//		# 配置Feign日志
//		# NONE 默认
//		# BASIC 记录URL、状态码、执行时间
//		# HEADERS 记录请求和响应头
//		# FULL 记录每个请求和相应的headers、body、metadata

		return Logger.Level.FULL;
	}
	
//	/*
//	 * 访问eureka server的验证
//	 */
//	@Bean
//	public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
//		return new BasicAuthRequestInterceptor("user", "psw123");
//	}
}
