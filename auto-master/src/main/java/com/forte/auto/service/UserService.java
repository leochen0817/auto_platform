package com.forte.auto.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forte.auto.dao.UserMapper;
import com.forte.auto.entity.User;

@Service
public class UserService {

	@Resource
	UserMapper userMapper;

	public List<User> getAll() {
		return this.userMapper.getAll();
	}

	public User getOne(int id) {
		return this.userMapper.getOne(id);
	}

	public void insert(User user) {
		this.userMapper.insert(user);
	}

	public void update(User user) {
		this.userMapper.update(user);
	}

	public void delete(int id) {
		this.userMapper.delete(id);
	}
}
