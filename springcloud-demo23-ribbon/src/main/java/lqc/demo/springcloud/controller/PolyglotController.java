package lqc.demo.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PolyglotController {

	@Resource
	private RestTemplate restTemplate;
	
	@GetMapping("/sidecar")
	public String sidecar() {
		String vip = "http://microservice-sidecar/";
		return restTemplate.getForObject(vip, String.class);
	}
}
