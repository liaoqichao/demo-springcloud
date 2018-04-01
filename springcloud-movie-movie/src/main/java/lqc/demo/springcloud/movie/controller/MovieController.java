package lqc.demo.springcloud.movie.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lqc.demo.springcloud.movie.bean.Movie;
import lqc.demo.springcloud.movie.service.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {

	@Resource
	private MovieService movieService;
	
	@HystrixCommand(fallbackMethod = "findByIdFallback")
	@GetMapping("/id/{id}")
	public Movie findById(@PathVariable("long") long id) {
		return this.movieService.findById(id);
	}
	
	public Movie findByIdFallback(long id) {
		Movie movie =  new Movie();
		movie.setId(-1L);
		movie.setMoviename("findByIdFallback");
		return movie;
	}
}
