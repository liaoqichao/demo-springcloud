package lqc.demo.springcloud.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer webMvcConfigurer(final List<HttpMessageConverter<?>> converters) {
        return new WebMvcConfigurer() {
            public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
                FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
                FastJsonConfig config = new FastJsonConfig();
                config.setSerializerFeatures(SerializerFeature.PrettyFormat);
                List<MediaType> fastMediaTypes = new ArrayList<MediaType>();
                fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
                converter.setSupportedMediaTypes(fastMediaTypes);
                converter.setFastJsonConfig(config);
                converters.add(converter);
            }
        };
    }

}
