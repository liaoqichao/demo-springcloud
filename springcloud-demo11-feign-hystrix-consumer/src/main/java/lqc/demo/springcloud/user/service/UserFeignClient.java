package lqc.demo.springcloud.user.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lqc.demo.springcloud.user.bean.User;

@FeignClient(name="microservices-provider-user", fallback = UserHystrixFallback.class)
//@RequestMapping("/user")  // 要使用hystrix，接口上不能使用@RequestMapping，否则UserHystrixFallback或者public的UserHystrixFallback也会带路径导致报错
public interface UserFeignClient {

	@RequestMapping("/user/id/{id}")
	public User findById(@PathVariable("id") long id);
}

// 要加注解，当然加@controller、@service、@repository也可以, 
// 非public类也要加注解，单独一个.java文件也要要加注解
@Component
class UserHystrixFallback implements UserFeignClient{

	@Override
	public User findById(long id) {
		return new User();
	}
}