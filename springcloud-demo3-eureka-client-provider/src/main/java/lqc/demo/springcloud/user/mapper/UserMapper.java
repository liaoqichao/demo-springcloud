package lqc.demo.springcloud.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import lqc.demo.springcloud.user.bean.User;

public interface UserMapper {

	@Insert("INSERT INTO User (username,name,age,balance) VALUES (#{username},#{name},#{age},#{balance})")
	/*
	 * useGeneratedKeys：是否开启主键生成策略
	 * keyProperty：实体类属性值
	 * keyColumn：数据库字段
	 */
	@Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
	public Long save(User user);
	
//	@Select("SELECT * FROM User WHERE id=#{id}")
	// 在mapper.xml中配置，不用注解
	public User findById(long id);
	
	@Update("UPDATE User SET name=#{name}, age=#{age}, balance=#{balance} WHERE id=#{id}")
	public void update(User user);
	
	@Delete("DELETE FROM User WHERE id=#{id}")
	public void deleteById(long id);
	
	@Select("SELECT * FROM User")
	public List<User> listAll();
}
