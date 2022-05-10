package kr.dev_mook.user_manager.dao.impl;

import java.util.List;

import kr.dev_mook.user_manager.model.User;

public interface UserMapper {
	
	public List<User> findAll();

}
