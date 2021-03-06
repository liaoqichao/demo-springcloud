package lqc.demo.springcloud.movie.controller;

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
		String serviceId = "microservices-provider-user";
		ServiceInstance si = loadBalancerClient.choose(serviceId);
		String vip = "http://"+serviceId+"/user/id/"+id;
		System.out.println("[ "+si.getHost() + ":" + si.getPort()+" ] : "+si.getServiceId() +" - " + (Math.random() * 100));
		User user = this.restTemplate.getForObject(vip, User.class);
		return user;
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
		
		List<User> list = this.restTemplate.getForObject(vip, List.class);
		return list;
	}
}
