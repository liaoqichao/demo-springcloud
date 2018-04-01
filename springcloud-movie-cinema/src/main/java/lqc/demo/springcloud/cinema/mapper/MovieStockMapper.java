package lqc.demo.springcloud.cinema.mapper;

import org.apache.ibatis.annotations.Update;

import lqc.demo.springcloud.cinema.bean.MovieStock;

public interface MovieStockMapper {

	@Update("UPDATE cinema_movie_stock SET quantity = quantity-#{quantity} WHERE cinemaid=#{cinemaId} AND movieid=#{movieId} AND quantity - #{quantity}>=0")
	public int updateStock(MovieStock movieStock);
}
