package lqc.demo.springcloud.cinema.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lqc.demo.springcloud.cinema.bean.Cinema;
import lqc.demo.springcloud.cinema.service.CinemaService;
import lqc.demo.springcloud.cinema.service.MovieStockService;

@RestController
public class CinemaController {

	@Resource
	private CinemaService cinemaService;
	
	@Resource
	private MovieStockService movieStockService;
	
	@HystrixCommand(fallbackMethod="findByIdFallback")
	@GetMapping("/cinema/id/{id}")
	public Cinema findById(@PathVariable("id") long id) {
		return this.cinemaService.findById(id);
	}
	
	public Cinema findByIdFallback(long id) {
		Cinema cinema = new Cinema();
		cinema.setId(-1);
		cinema.setName("findByIdFallback");
		return cinema;
	}
	
	@GetMapping("/updateStock/{cinemaId}/{movieId}/{num}")
	public int updateStock(@PathVariable("cinemaId") int cinemaId, @PathVariable("movieId") int movieId,
			@PathVariable("num") int num) {
		return movieStockService.updateStock(cinemaId, movieId, num);
	}
}
