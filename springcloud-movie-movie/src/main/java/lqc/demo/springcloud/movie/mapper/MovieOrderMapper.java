package lqc.demo.springcloud.movie.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import lqc.demo.springcloud.movie.bean.MovieOrder;

public interface MovieOrderMapper {

	@Options(useGeneratedKeys=true, keyColumn="id", keyProperty="id")
	@Insert("INSERT INTO movie_order (userid,movieid,cinemaid,num) VALUES (#{userId}, #{movieId}, #{cinemaId}, #{num})")
	public long save(MovieOrder movieOrder);
	
	@Select("SELECT * FROM movie_order WHERE id=#{id}")
	public MovieOrder findById(long id);
}
