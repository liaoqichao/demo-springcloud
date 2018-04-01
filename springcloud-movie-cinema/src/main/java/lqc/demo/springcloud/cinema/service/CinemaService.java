package lqc.demo.springcloud.cinema.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lqc.demo.springcloud.cinema.bean.Cinema;
import lqc.demo.springcloud.cinema.mapper.CinemaMapper;

@Service
public class CinemaService {

	@Resource 
	private CinemaMapper cinemaMapper;
	
	public Cinema findById(long id){
		return cinemaMapper.findById(id);
	}
}
