package lqc.demo.springcloud.movie.controller;

import java.util.concurrent.ExecutionException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lqc.demo.springcloud.movie.bean.MovieOrder;
import lqc.demo.springcloud.movie.service.MovieOrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Resource
	private MovieOrderService movieOrderService;
	
	@HystrixCommand(fallbackMethod = "buyFallback")
	@RequestMapping("/buy")
	public @ResponseBody MovieOrder buy(MovieOrder movieOrder) throws InterruptedException, ExecutionException {
		return movieOrderService.buy(movieOrder);
	}
	
	public MovieOrder buyFallback(MovieOrder movieOrder) {
		return new MovieOrder();
	}
}
