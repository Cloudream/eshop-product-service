package com.cloudream.eshop.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudream.eshop.product.mapper.UserMapper;
import com.cloudream.eshop.product.model.User;


@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public List<User> findAllUser() {
		return userMapper.findAllUser();
	}
}
