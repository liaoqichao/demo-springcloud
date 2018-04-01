package lqc.demo.springcloud.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import lqc.demo.springcloud.user.bean.User;

public interface UserMapper {

	@Options(useGeneratedKeys=true, keyColumn="id", keyProperty="id" )
	@Insert("INSERT INTO User (username,name,age,balance), VALUES(#{username},#{name},#{age},#{balance})")
	public long save(User user);
	
	@Delete("DELETE FROM User WHERE id=#{id}")
	public void deleteById(long id);
	
	public void update(User user);
	
	public User findById(long id);
	
	public List<User> listAll();
	
}
