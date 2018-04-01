package lqc.demo.springcloud.movie.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lqc.demo.springcloud.user.bean.User;

@RestController
@RequestMapping("/movie")
public class MovieController {

	@Resource
	private RestTemplate restTemplate;
	
	/*
	 * ribbon的负载均衡客户端
	 */
	@Resource
	private LoadBalancerClient loadBalancerClient; 
	
	@GetMapping("/user/id/{id}")
	public User findById(@PathVariable("id") long id) {
		/*
		 * http://microservices-provider-user/
		 * http://serviceid/
		 * VIP:vitual ip
		 */
		
		ResponseEntity<User> resp = restTemplate.getForEntity("http://microservices-provider-user/user/id/"+id, User.class);
		return resp.getBody();
//		return restTemplate.getForObject("http://microservices-provider-user/user/id/"+id, User.class);
//		return restTemplate.getForObject("http://localhost:7900/user/id/" + id, User.class);
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/user/listByPager/{pn}/{size}")
	public List<User> listByPager(@PathVariable("pn") String pn, @PathVariable("size") String size) {
		String serviceId = "microservices-provider-user";
		// 通过负载均衡策略命中一个服务实例
		ServiceInstance si = loadBalancerClient.choose(serviceId);
		System.out.println(si.getHost()+":"+si.getPort()+":"+si.getServiceId());
//		return "OK";
		
//		192.168.31.227:7902:microservices-provider-user
//		192.168.31.227:7900:microservices-provider-user
//		192.168.31.227:7900:microservices-provider-user
//		192.168.31.227:7902:microservices-provider-user
//		192.168.31.227:7902:microservices-provider-user

		String vip = "http://"+si.getServiceId()+"/user/listAll/"+pn+"/"+size;
		
		// 页面可以显示，但是在java代码中会强转错误
//		List<User> list = this.restTemplate.getForObject(vip, List.class);
//		for (User user : arr) {
//			// 强制转换错误
//			System.out.println(user.getId());
//		}
		
		// 正确方式
		User[] arr = this.restTemplate.getForObject(vip, User[].class);
		List<User> list = new ArrayList<User>();
		for (User user : arr) {
			System.out.println(user.getId());
			list.add(user);
		}
		

		
		return list;
	}
}
