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
	public Long save(User user) {
		return userMapper.save(user);
	}
	
	public User findById(long id) {
		return userMapper.findById(id);
	}
	
	@Transactional
	public void update(User user) {
		userMapper.update(user);
	}
	
	@Transactional
	public void deleteById(long id) {
		userMapper.deleteById(id);
	}
	
	public List<User> listAll(){
		return userMapper.listAll();
	}
}
