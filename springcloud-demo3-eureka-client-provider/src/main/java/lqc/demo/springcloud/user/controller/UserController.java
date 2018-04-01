package lqc.demo.springcloud.user.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import lqc.demo.springcloud.user.bean.User;
import lqc.demo.springcloud.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;
	
	@Resource
	private EurekaClient eurekaClient;
	
	@Resource
	private DiscoveryClient discoveryClient;
	
	
	@GetMapping("/eureka-instance")
	public String serviceUrl() {
		/*
		 * eureka客户端访问eureka服务器，查询应用名称为“microservices-provider-user”的ip和端口号
		 * 返回值：http://192.168.31.227:7900/
		 * 参数1：主机名，默认为微服务应用的名称
		 */
		InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("microservices-provider-user", false);
		return instanceInfo.getHomePageUrl();
	}
	
	@GetMapping("/instance-info")
	public String showInfo() {
		StringBuilder sb = new StringBuilder(256);
		// 获取指定微服务
//		List<ServiceInstance> serviceInstance = discoveryClient.getInstances("microservices-provider-user");
		List<String> serviceList = discoveryClient.getServices();
		for (String string : serviceList) {
			List<ServiceInstance> serviceInstance = discoveryClient.getInstances(string);
			for (ServiceInstance serviceInstance2 : serviceInstance) {
//				serviceInstance2.getHost();
//				serviceInstance2.getPort();
//				serviceInstance2.getMetadata();
//				serviceInstance2.getScheme();
//				serviceInstance2.getServiceId();
//				serviceInstance2.getUri();
				System.out.println(serviceInstance2.getServiceId() + " [ " +serviceInstance2.getHost()+":"+serviceInstance2.getPort()+" ]");
				sb.append(serviceInstance2.getServiceId() + " [ " +serviceInstance2.getHost()+":"+serviceInstance2.getPort()+" ]");
				sb.append("\r\n");
			}
		}
		return sb.toString();
		/*
		 * MICROSERVICES-PROVIDER-USER [ 192.168.31.227:7900 ] 
		 */
	}
	
//	@RequestMapping("/id/{id}")
	@GetMapping("/id/{id}")
	public User findById(@PathVariable("id") long id) {
		System.out.println("7902"); // ribbon实现客户端负载均衡
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
	
	
	@PostMapping("/modName") // demo6
	public User modName(@RequestBody User user) {
		User u = userService.findById(user.getId());
		if(null != u) {
			u.setName(user.getName());
			userService.update(u);
		}
		return u;
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
