package lqc.demo.springcloud.movie.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lqc.demo.springcloud.movie.bean.Movie;
import lqc.demo.springcloud.movie.mapper.MovieMapper;

@Service
public class MovieService {

	@Resource
	private MovieMapper movieMapper;
	
	public Movie findById(long id) {
		return movieMapper.findById(id);
	}
	

}
