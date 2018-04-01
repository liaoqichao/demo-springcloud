package lqc.demo.feign.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import feign.Feign;

@Configuration
public class FeignConfiguration {

	@Bean
	@Scope("prototype")
	public Feign.Builder feignBuilder(){
		/*
		 * 如果启用了hystrix默认是HystrixFeign.builder();
		 * 如果直接返回Feign.builder()就没有对hystrix支持，配置了这个类的FeignClient就不支持对hystrix的支持
		 */
//		HystrixFeign.builder();
		return Feign.builder();
	}
}
