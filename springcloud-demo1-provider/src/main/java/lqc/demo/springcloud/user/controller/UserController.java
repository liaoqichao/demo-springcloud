package lqc.demo.springcloud.user.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;

import lqc.demo.springcloud.user.bean.User;
import lqc.demo.springcloud.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;
	
//	@RequestMapping("/id/{id}")
	@GetMapping("/id/{id}")
	public User findById(@PathVariable("id") long id) {
		return userService.findById(id);
	}
	
	@RequestMapping("/save/{username}/{name}/{age}/{balance}")
	public long save(@PathVariable("username") String username,
			@PathVariable("name") String name, @PathVariable("age") short age, 
			@PathVariable("balance") BigDecimal balance) {
		User user = new User();
		user.setUsername(username);
		user.setName(name);
		user.setAge(age);
		user.setBalance(balance);
		return userService.save(user);
	}
	@RequestMapping("/update/{username}/{name}/{age}/{balance}")
	public void update(@PathVariable("username") String username,
			@PathVariable("name") String name, @PathVariable("age") short age, 
			@PathVariable("balance") BigDecimal balance) {
		User user = new User();
		user.setUsername(username);
		user.setName(name);
		user.setAge(age);
		user.setBalance(balance);
		userService.update(user);
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteById(@PathVariable("id") long id) {
		userService.deleteById(id);
		return "OK";
	}
	
	@RequestMapping("/listAll/{pn}/{size}")
	public List<User> listAll(@PathVariable("pn") int pn, @PathVariable("size") int size){
		PageHelper.startPage(pn, size);
		return userService.listAll();
	}
}
