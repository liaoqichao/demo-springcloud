package lqc.demo.springcloud.movie.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lqc.demo.springcloud.user.service.UserFeignClient;
import lqc.demo.springcloud.user.service.XXFeignClient;
import lqc.demo.springcloud.user.user.User;

@RestController
@RequestMapping("/movie")
public class MovieController {

	@Resource
	private UserFeignClient userFeignClient;
	@Resource
	private XXFeignClient xXFeignClient;
	
	@GetMapping("/user/id/{id}")
	public User findById(@PathVariable("id") long id) {
		return this.userFeignClient.findById(id);
	}
	
	@GetMapping("/xx/id/{id}")
	public String xx(@PathVariable("id") long id) {
		return this.xXFeignClient.xx(id);
	}
	
	
}
