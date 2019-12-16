package com.cloudream.eshop.product.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cloudream.eshop.product.mapper.UserMapper;
import com.cloudream.eshop.product.model.User;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
	
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void testFindAllUser() throws Exception{
		List<User> users = userMapper.findAllUser();
		System.out.println("Query all user: " + users.toString());
	}
}
