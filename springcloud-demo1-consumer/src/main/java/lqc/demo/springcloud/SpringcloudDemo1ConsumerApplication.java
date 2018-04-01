package lqc.demo.springcloud;

import java.nio.charset.Charset;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@SpringBootApplication
public class SpringcloudDemo1ConsumerApplication {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public HttpMessageConverters fastJsonMessageConverters() {
		// 1. 定义转换消息的对象converter
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		// 2. 添加FastJson的配置信息，比如是否要格式化返回的json数据
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		// 3. converter中添加配置信息
		fastConverter.setFastJsonConfig(fastJsonConfig);
		fastJsonConfig.setCharset(Charset.forName("GBK")); // 不设置默认为UTF-8，会乱码
		// 4. 返回HttpMessageConverters
		HttpMessageConverter<?> converter = fastConverter;
		return new HttpMessageConverters(converter);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringcloudDemo1ConsumerApplication.class, args);
	}
}
