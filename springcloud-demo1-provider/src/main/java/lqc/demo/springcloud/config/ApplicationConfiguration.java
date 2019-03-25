package lqc.demo.springcloud.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public HttpMessageConverters fastJsonMessageConverters() {
        // 1. 定义转换消息的对象converter
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 2. 添加FastJson的配置信息，比如是否要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        // 3. converter中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
//		fastJsonConfig.setCharset(Charset.forName("GBK")); // 提供者不修改编码格式，否则会报错
        // 4. 返回HttpMessageConverters
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }

}
