package lqc.demo.springcloud.movie.controller;

import java.util.Arrays;
import java.util.List;

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
	@GetMapping("/user/id/{id}")
	public User findById(@PathVariable("id") long id) {
		String url = "http://microservices-provider-user/user/id/"+id;
		return this.restTemplate.getForObject(url, User.class);
	}
	
	/*
	 * 返回值和参数要和正常方法(MovieController.findById)一样
	 */
	public User findByIdFallback(long id) {
		return new User();
	}
	
	@GetMapping("/user/listByPager/{pn}/{size}")
	public List<User> listByPager(@PathVariable("pn")int pn, @PathVariable("size") int size){
		String url = "http://microservices-provider-user/user/listByPager/"+pn+"/"+size;
		User[] arr = this.restTemplate.getForObject(url, User[].class);
		return Arrays.asList(arr);
	}
}
