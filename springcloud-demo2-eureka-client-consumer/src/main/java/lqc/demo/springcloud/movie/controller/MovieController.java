package lqc.demo.springcloud.movie.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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
	
	@Resource
	private DiscoveryClient discoveryClient;
	
	@GetMapping("/user/id/{id}")
	public User findById(@PathVariable("id") long id) {
		String uri = "";
		List<ServiceInstance> list = discoveryClient.getInstances("MICROSERVICES-PROVIDER-USER");
		if(list!=null && !list.isEmpty()) {
			uri = list.get(0).getUri().toString();
			System.out.println(uri); // http://192.168.31.227:7900
		}
		uri = uri + "/user/id/" + id;
		return restTemplate.getForObject(uri, User.class);
//		return restTemplate.getForObject("http://localhost:7900/user/id/" + id, User.class);
	}
}
