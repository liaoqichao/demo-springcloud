package lqc.demo.springcloud.cinema.mapper;

import org.apache.ibatis.annotations.Select;

import lqc.demo.springcloud.cinema.bean.Cinema;

public interface CinemaMapper {

	@Select("SELECT * FROM Cinema WHERE id=#{id}")
	public Cinema findById(long id);
}
