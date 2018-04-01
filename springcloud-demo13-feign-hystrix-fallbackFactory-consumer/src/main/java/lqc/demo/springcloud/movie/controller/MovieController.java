package lqc.demo.springcloud.movie.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lqc.demo.springcloud.user.bean.User;
import lqc.demo.springcloud.user.service.UserFeignClient;

@RestController
@RequestMapping("/movie")
public class MovieController {

	
	@Resource
	private UserFeignClient userFeignClient;
	
	@GetMapping("/user/id/{id}")
	public User findById(@PathVariable("id") long id) {
		return userFeignClient.findById(id);
	}
}
