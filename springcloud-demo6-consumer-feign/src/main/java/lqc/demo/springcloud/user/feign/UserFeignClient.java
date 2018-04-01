package lqc.demo.springcloud.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lqc.demo.springcloud.user.bean.User;

@FeignClient("microservices-provider-user")
@RequestMapping("/user")
public interface UserFeignClient {

//	@RequestMapping(method = RequestMethod.GET, value="/id/{id}") 
	@GetMapping(value="/id/{id}") // 不能用GetMaping
	public User findById(@PathVariable("id") long id); // @PathVariable("id")注解的value一定要写
	
	@RequestMapping(method = RequestMethod.POST, value="/modName") 
	public User modName(@RequestBody User user);
}
