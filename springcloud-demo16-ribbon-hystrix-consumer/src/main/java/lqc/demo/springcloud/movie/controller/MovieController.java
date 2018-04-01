package lqc.demo.springcloud.movie.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lqc.demo.springcloud.user.bean.User;

@RestController
@RequestMapping("/movie")
public class MovieController {

	@Resource
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod="findByIdFallback")
	@GetMapping("/id/{id}")
	public User findById(@PathVariable("id") long id) {
		String url = "http://microservices-provider-user/user/id/"+id;
		return this.restTemplate.getForObject(url, User.class);
	}
	
	public User findByIdFallback(long id) {
		User user = new User();
		Date date = new Date();
		user.setId(date.getTime());
		user.setUsername(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date));
		user.setName("findByIdFallback");
		return user;
	}
}
