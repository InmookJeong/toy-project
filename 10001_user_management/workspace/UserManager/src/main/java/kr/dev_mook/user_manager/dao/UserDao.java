package kr.dev_mook.user_manager.dao;

import java.util.List;

import kr.dev_mook.user_manager.model.User;

public interface UserDao {
	
	// Create User
	public boolean createUser(final User user);
	
	// Update User
	public boolean updateUser(final User user);
	
	// Update Password
	public boolean updatePassword(final String userId, final String password);
	
	// Delete User
	public boolean deleteUser(final User user);
	
	// Delete User by ID
	public boolean deleteUser(final Long id);
	
	// Delete User by userId
	public boolean deleteUser(final String userId);
	
	// Find all users count
	public int countAll();
	
	// Find all users
	public List<User> findAll();
	
	// ID exist check
	public boolean hasById(final Long id);
	
	public User findById(final Long id);
	
	// By UserId
	public boolean hasByUserId(final String userId);
	
	public User findByUserId(final String userId);
	
	public int countUsersByUserId(final String userId);
	
	public List<User> findUsersByUserId(final String userId);
	
	public List<User> findUsersByUserId(final String userId, final int start, final int end);
	
	public List<User> findUsersByUserId(final String userId, final int start, final int end, String orderByColumn, boolean isDesc);
	
	// By Name
	public int countUsersByName(final String name);
	
	public List<User> findUsersByName(final String name);
	
	public List<User> findUsersByName(final String name, final int start, final int end);
	
	public List<User> findUsersByName(final String name, final int start, final int end, String orderByColumn, boolean isDesc);
	
	// By PhoneNumber
	public boolean hasByPhoneNumber(final String phoneNumber);
	
	public User findByPhoneNumber(final String phoneNumber);
	
	// By Email
	public boolean hasByEmail(final String email);
	
	public User findByEmail(final String email);
	
	public int countUsersByEmail(final String email);
	
	public List<User> findUsersByEmail(final String email);
	
	public List<User> findUsersByEmail(final String email, final int start, final int end);
	
	public List<User> findUsersByEmail(final String email, final int start, final int end, String orderByColumn, boolean isDesc);
	
	// By Gender
	public int countUsersByGender(final String gender);
	
	public List<User> findUsersByGender(final String gender);
	
	public List<User> findUsersByGender(final String gender, final int start, final int end);
	
	public List<User> findUsersByGender(final String gender, final int start, final int end, String orderByColumn, boolean isDesc);
	
	// By Nation
	public int countUsersByNation(final String nation);
	
	public List<User> findUsersByNation(final String nation);
	
	public List<User> findUsersByNation(final String nation, final int start, final int end);
	
	public List<User> findUsersByNation(final String nation, final int start, final int end, String orderByColumn, boolean isDesc);
	
	// By Status
	public int countUsersByStatus(final String status);
	
	public List<User> findUsersByStatus(final String status);
	
	public List<User> findUsersByStatus(final String status, final int start, final int end);
	
	public List<User> findUsersByStatus(final String status, final int start, final int end, String orderByColumn, boolean isDesc);
	
	// By Lockout
	public int countUsersByLockout(final boolean lockout);
	
	public List<User> findUsersByLockout(final boolean lockout);
	
	public List<User> findUsersByLockout(final boolean lockout, final int start, final int end);
	
	public List<User> findUsersByLockout(final boolean lockout, final int start, final int end, String orderByColumn, boolean isDesc);
	
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
	