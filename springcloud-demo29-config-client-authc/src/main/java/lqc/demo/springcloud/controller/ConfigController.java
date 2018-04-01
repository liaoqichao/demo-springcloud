package lqc.demo.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

	@Value("${profile}")
	private String profile;
	
	@GetMapping("/profile")
	public String getProfile() {
		return profile;
	}
}