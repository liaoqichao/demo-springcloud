package lqc.demo.springcloud.user.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lqc.demo.springcloud.user.bean.User;
import lqc.demo.springcloud.user.service.UserService;

@RestController
public class UserController {

	@Resource
	private UserService userService;
	
	@HystrixCommand(fallbackMethod = "findByIdFallback")
	@GetMapping("/id/{id}")
	public User findById(@PathVariable("id") long id) {
		return userService.findById(id);
	}
	
	public User findByIdFallback( long id) {
		User user = new User();
		user.setId(-1L);
		user.setName(new SimpleDateFormat("yyyy:MM:dd hh:mm:ss").format(new Date()));
		return user;
	}
	
	
	@GetMapping("/list/{pn}/{size}")
	public List<User> list(@PathVariable("pn") int pn, @PathVariable("size") int size) {
		PageHelper.startPage(pn, size);
		return userService.listAll();
	}
}
