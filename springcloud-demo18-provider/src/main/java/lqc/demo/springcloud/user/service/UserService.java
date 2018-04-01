package lqc.demo.springcloud.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lqc.demo.springcloud.user.bean.User;
import lqc.demo.springcloud.user.mapper.UserMapper;

@Service
public class UserService {

	@Resource
	private UserMapper userMapper;
	
	@Transactional
	public long save(User user) {
		return this.userMapper.save(user);
	}
	
	public void deleteById(long id) {
		this.userMapper.deleteById(id);
	}
	
	public void update(User user) {
		this.userMapper.update(user);
	}
	
	public User findById(long id) {
		return this.userMapper.findById(id);
	}
	
	public List<User> listAll(){
		return this.userMapper.listAll();
	}
}
