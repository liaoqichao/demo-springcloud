package lqc.demo.springcloud.movie.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lqc.demo.springcloud.user.bean.User;

@RestController
@RequestMapping("/movie")
public class MovieController {

	// 通过restTemplate调用微服务
	@Resource
	private RestTemplate restTemplate;
	
	@GetMapping("/user/id/{id}")
	public User findById(@PathVariable("id") long id) {
//		ResponseEntity<String> resp = restTemplate.getForEntity("", String.class);
		// getForEntity
		// http
//		resp.getBody(); // getForObject
//		resp.getHeaders();
//		resp.getStatusCode();
//		resp.getStatusCodeValue();
		
		// getForObject = getForEntity.getBody();
		return restTemplate.getForObject("http://localhost:7900/user/id/" + id, User.class);
	}
}
