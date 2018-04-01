package lqc.demo.springcloud.user.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lqc.demo.feign.config.FeignConfiguration;

@FeignClient(name="xx", fallback=XXFeignClientFallback.class, configuration=FeignConfiguration.class)
public interface XXFeignClient {

	@RequestMapping("/xx/id/{id}")
	public String xx(@PathVariable("id") long id);
}

@Component
class XXFeignClientFallback implements XXFeignClient{

	@Override
	public String xx(long id) {
		return "hystrix - " + new SimpleDateFormat("yyyy-HH-MM hh:mm:ss").format(new Date());
	}
	
}
