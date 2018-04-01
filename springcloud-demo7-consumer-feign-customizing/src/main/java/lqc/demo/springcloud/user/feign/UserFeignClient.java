package lqc.demo.springcloud.user.feign;

import org.springframework.cloud.openfeign.FeignClient;

import feign.Param;
import feign.RequestLine;
import lqc.demo.config.FeignClientConfiguration;
import lqc.demo.springcloud.user.bean.User;

@FeignClient(name="microservices-provider-user", configuration=FeignClientConfiguration.class)
public interface UserFeignClient {

	/*
	 * contract改为feign的默认契约，不能再使用SpringMVC注解
	 * 使用@RequestLine("GET/PUT/DELETE/POST url")
	 */
	
	@RequestLine("GET /user/id/{id}")
	public User findById(@Param("id") long id); 
	
	@RequestLine("POST /user/modName")
	public User modName(User user);
}
