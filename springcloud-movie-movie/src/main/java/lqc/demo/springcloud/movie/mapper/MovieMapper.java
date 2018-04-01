package lqc.demo.springcloud.movie.mapper;

import org.apache.ibatis.annotations.Select;

import lqc.demo.springcloud.movie.bean.Movie;

public interface MovieMapper {

	@Select("SELECT * FROM Movie WHERE id=#{id}")
	public Movie findById(long id);
}
