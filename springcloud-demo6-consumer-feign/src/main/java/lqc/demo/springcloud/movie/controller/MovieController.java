package lqc.demo.springcloud.movie.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lqc.demo.springcloud.user.bean.User;
import lqc.demo.springcloud.user.feign.UserFeignClient;

@RestController
@RequestMapping("/movie")
public class MovieController {

	@Resource
	private UserFeignClient userFeignClient;
	
	@GetMapping("/user/id/{id}")
	public User findById(@PathVariable("id") long id) {
		return userFeignClient.findById(id);
	}
	
	@GetMapping("/user/modName/id/{id}/name/{name}")
	public User postUser(@PathVariable("id") long id, @PathVariable("name") String name) {
		User example = new User();
		example.setId(id);
		example.setName(name);
		userFeignClient.modName(example);
		User user = userFeignClient.findById(id);
		return user;
	}
}
