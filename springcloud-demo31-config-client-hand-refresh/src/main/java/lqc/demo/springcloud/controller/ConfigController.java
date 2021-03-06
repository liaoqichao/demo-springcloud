package lqc.demo.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope	// 添加@RefreshScope注解
public class ConfigController {

	
	@Value("${profile}")
	private String profile;
	
	@GetMapping("/profile")
	public String profile() {
		return this.profile;
	}
}
