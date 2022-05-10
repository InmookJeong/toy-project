package kr.dev_mook.user_manager.dao;

import java.util.HashMap;
import java.util.List;

import kr.dev_mook.user_manager.model.User;

public interface UserMapper {
	
	// Create User
	public void createUser(final User user);
	
	// Update User
	public void updateUser(final User user);
	
	// Update Password
	public void updatePassword(final HashMap<String, Object> updateMap);
	
	// Delete User by ID
	public void deleteUser(final Long id);
	
	// Delete User by userId
	public void deleteUser(final String userId);
	
	// Find all users count
	public int countAll();
	
	// Find all users
	public List<User> findAll();
	
	// ID exist check
	public int countById(final Long id);
	
	public User findById(final Long id);
	
	// By UserId
	public int countByUserId(final String userId);
	
	public User findByUserId(final String userId);
	
	public int countUsersByUserId(final String userId);
	
	public List<User> searchUsersByUserId(final HashMap<String, Object> searchMap);
	
	// By Name
	public int countUsersByName(final String name);
	
	public List<User> searchUsersByName(final HashMap<String, Object> searchMap);
	
	// By PhoneNumber
	public boolean countByPhoneNumber(final String phoneNumber);
	
	public User findByPhoneNumber(final String phoneNumber);
	
	// By Email
	public boolean countByEmail(final String email);
	
	public User findByEmail(final String email);
	
	public int countUsersByEmail(final String email);
	
	public List<User> searchUsersByEmail(final HashMap<String, Object> searchMap);
	
	// By Gender
	public int countUsersByGender(final String gender);
	
	public List<User> searchUsersByGender(final HashMap<String, Object> searchMap);
	
	// By Nation
	public int countUsersByNation(final String nation);
	
	public List<User> searchUsersByNation(final HashMap<String, Object> searchMap);
	
	// By Status
	public int countUsersByStatus(final String status);
	
	public List<User> searchUsersByStatus(final HashMap<String, Object> searchMap);
	
	// By Lockout
	public int countUsersByLockout(final boolean lockout);
	
	public List<User> searchUsersByLockout(final HashMap<String, Object> searchMap);
	
	// By UserId and Password
	public boolean hasByUserIdPassword(final String userId, final String password);
	
	// By Name and Email
	public boolean hasByNameEmail(final String name, final String email);
	
	public User findByNameEmail(final String name, final String email);
	
	// By Name and PhoneNumber
	public boolean hasByNamePhoneNumber(final String name, final String phoneNumber);
	
	public User findByNamePhoneNumber(final String name, final String phoneNumber);
	
	// By userId and Name and Email
	public boolean hasByUserIdNameEmail(final String userId, final String name, final String email);
	
	public User findByUserIdNameEmail(final String userId, final String name, final String email);
	
	// 가장 마지막에 가입된 사용자의 ID 조회
	public Long getLatestId();
	
	// TODO 나중에 지우기
	public String findUserIdByNameEmail(final String name, final String email);

}
