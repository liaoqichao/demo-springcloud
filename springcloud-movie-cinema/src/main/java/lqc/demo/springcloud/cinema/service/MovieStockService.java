package lqc.demo.springcloud.cinema.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lqc.demo.springcloud.cinema.bean.MovieStock;
import lqc.demo.springcloud.cinema.mapper.MovieStockMapper;

@Service
public class MovieStockService {

	@Resource
	private MovieStockMapper movieStockMapper;
	
	public int updateStock(long cinemaId, long movieId, int quantity) {
		MovieStock movieStock = new MovieStock();
		movieStock.setCinemaId(cinemaId);
		movieStock.setMovieId(movieId);
		movieStock.setQuantity(quantity);
		return movieStockMapper.updateStock(movieStock);
	}
}
