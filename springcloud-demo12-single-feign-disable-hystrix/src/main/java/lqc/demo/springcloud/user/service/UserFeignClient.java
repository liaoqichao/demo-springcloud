package lqc.demo.springcloud.user.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lqc.demo.springcloud.user.user.User;

@FeignClient(name="microservies-provider-user", fallback=UserFeignClientFallback.class)
public interface UserFeignClient {

	@RequestMapping("/user/id/{id}")
	public User findById(@PathVariable("id") long id);
}

@Component
class UserFeignClientFallback implements UserFeignClient{

	@Override
	public User findById(long id) {
		return new User();
	}
	
}


