package kr.dev_mook.user_manager.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import kr.dev_mook.user_manager.dao.UserDao;
import kr.dev_mook.user_manager.model.User;

public class UserDaoImpl implements UserDao {
	
	private final Logger _logger = LoggerFactory.getLogger(UserDaoImpl.class);
	private final String JONE_ID_SOULE = "Asia/Seoul";
	
	public UserDaoImpl() {}
	
	JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	// TODO Refactoring to AOP
	private void _loggingOperatingTime(boolean isStart) {
		String message = (isStart) ? "Started" : "Finished";
		_logger.debug("##### " + message + " User creation. - [" + message + " Date : " + LocalDateTime.now(ZoneId.of(JONE_ID_SOULE)) + "]");
	}
	
	@Override
	public boolean createUser(final User user) {
		boolean success = false;
		_loggingOperatingTime(true);
		
		try {
			template.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//					String query = "INSERT INTO user_(id, userId, password, isTempPw, name, phoneNumber, "
//							+ "email, birth, gender, address, nation, description, status, profileImageId,"
//							+ "createDate, modifiedDate, lastLoginDate, lastLoginIp, failedLoginAttemts,"
//							+ "lockout, lockoutDate, emailVerified, siteTermsOfUse, userInfoTermsOfUse, eventTermsOfUse)"
//							+ "VALUES(?, ?, ?, ?, ?, ?,"
//							+ "?, ?, ?, ?, ?, ?, ?, ?,"
//							+ "?, ?, ?, ?, ?,"
//							+ "?, ?, ?, ?, ?, ?)";
					String query = "";
					query += "INSERT INTO user_(id, userId, password, isTempPw, name, phoneNumber, ";
					query += "email, birth, gender, address, nation, description, status, profileImageId, ";
					query += "createDate, emailVerified, siteTermsOfUse, userInfoTermsOfUse, eventTermsOfUse)";
					query += "VALUES(?, ?, ?, ?, ?, ?,";
					query += "?, ?, ?, ?, ?, ?, ?, ?,";
					query += "?, ?, ?, ?, ?)";
					PreparedStatement pstmt = con.prepareStatement(query);
					pstmt.setLong(1, user.getId());
					pstmt.setString(2, user.getUserId());
					System.out.println("user password : " + user.getPassword());
					pstmt.setString(3, user.getPassword());
					pstmt.setBoolean(4, user.isTempPw());
					pstmt.setString(5, user.getName());
					pstmt.setString(6, user.getPhoneNumber());
					
					pstmt.setString(7, user.getEmail());
					pstmt.setString(8, user.getBirth());
					pstmt.setString(9, user.getGender());
					pstmt.setString(10, user.getAddress());
					pstmt.setString(11, user.getNation());
					pstmt.setString(12, user.getDescription());
					pstmt.setString(13, user.getStatus());
					pstmt.setLong(14, user.getProfileImageId());
					
					pstmt.setDate(15, Date.valueOf(user.getCreateDate()));
					pstmt.setBoolean(16, user.isEmailVerified());
					pstmt.setBoolean(17, user.isSiteTermsOfUse());
					pstmt.setBoolean(18, user.isUserInfoTermsOfUse());
					pstmt.setBoolean(19, user.isEventTermsOfUse());
					
					return pstmt;
				}
			});
			success = true;
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
		}
		
		_loggingOperatingTime(false);
		return success;
	}

	@Override
	public boolean updateUser(final User user) {
		boolean success = false;
		_loggingOperatingTime(true);
		
		try {
			template.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					String query = "";
					query += "UPDATE user_ ";
					query += "SET password = '" + user.getPassword() + "' ";
					query += "WHERE userId = '" + user.getUserId() + "' ";
					PreparedStatement pstmt = con.prepareStatement(query);
					return pstmt;
				}
			});
			success = true;
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
		}
		
		_loggingOperatingTime(false);
		return success;
	}
	
	@Override
	public boolean updatePassword(final String userId, final String password) {
		boolean success = false;
		_loggingOperatingTime(true);
		
		try {
			template.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					String query = "";
					query += "UPDATE user_ ";
					query += "SET password = '" + password + "' ";
					query += "WHERE userId = '" + userId + "' ";
					PreparedStatement pstmt = con.prepareStatement(query);
					return pstmt;
				}
			});
			success = true;
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
		}
		
		_loggingOperatingTime(false);
		return success;
	}

	@Override
	public boolean deleteUser(final User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(final Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(final String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int countAll() {
		_loggingOperatingTime(true);
		
		String query = "SELECT COUNT(*) FROM user_";
		int count = template.queryForObject(query, Integer.class);
		
		_loggingOperatingTime(false);
		return count;
	}

	@Override
	public List<User> findAll() {
		_loggingOperatingTime(true);
		
		String query = "SELECT * FROM user_";
		List<User> users = template.queryForList(query, User.class);
		
		_loggingOperatingTime(false);
		return users;
	}

	@Override
	public boolean hasById(final Long id) {
		_loggingOperatingTime(true);
		
		String query = "SELECT COUNT(*) FROM user_ WHERE id = '" + id + "'";
		int count = template.queryForObject(query, Integer.class);
		
		_loggingOperatingTime(false);
		return (count > 0);
	}

	@Override
	public User findById(final Long id) {
		_loggingOperatingTime(true);
		
		String query = "SELECT * FROM user_ WHERE id = '" + id + "'";
		User user = template.queryForObject(query, User.class);
		
		_loggingOperatingTime(false);
		return user;
	}

	@Override
	public boolean hasByUserId(final String userId) {
		_loggingOperatingTime(true);
		
		String query = "SELECT COUNT(*) FROM user_ WHERE userId = '" + userId + "'";
		int count = template.queryForObject(query, Integer.class);
		
		_loggingOperatingTime(false);
		return (count > 0);
	}

	@Override
	public User findByUserId(final String userId) {
		_loggingOperatingTime(true);
		
		String query = "SELECT * FROM user_ WHERE userId = '" + userId + "'";
		User user = template.queryForObject(query, User.class);
		
		_loggingOperatingTime(false);
		return user;
	}

	@Override
	public int countUsersByUserId(final String userId) {
		_loggingOperatingTime(true);
		
		String query = "SELECT COUNT(*) FROM user_ WHERE userId LIKE '%" + userId + "%'";
		int count = template.queryForObject(query, Integer.class);
		
		_loggingOperatingTime(false);
		return count;
	}

	@Override
	public List<User> findUsersByUserId(final String userId) {
		_loggingOperatingTime(true);
		
		String query = "SELECT * FROM user_ WHERE userId LIKE '%" + userId + "%'";
		List<User> users = template.queryForList(query, User.class);
		
		_loggingOperatingTime(false);
		return users;
	}

	@Override
	public List<User> findUsersByUserId(final String userId, final int start, final int end) {
		_loggingOperatingTime(true);
		
		String query = "SELECT * FROM user_ WHERE userId LIKE '%" + userId + "%' LIMIT " + start + ", " + end;
		List<User> users = template.queryForList(query, User.class);
		
		_loggingOperatingTime(false);
		return users;
	}

	@Override
	public List<User> findUsersByUserId(final String userId, final int start, final int end, final String orderByColumn, final boolean isDesc) {
		_loggingOperatingTime(true);
		
		String orderBy = isDesc ? "DESC" : "ASC";
		String query = "SELECT * FROM user_ WHERE userId LIKE '%" + userId + "%' ORDER BY " + orderByColumn + " " + orderBy + " LIMIT " + start + ", " + end;
		List<User> users = template.queryForList(query, User.class);
		
		_loggingOperatingTime(false);
		return users;
	}
	
	@Override
	public int countUsersByName(final String name) {
		_loggingOperatingTime(true);
		String query = "SELECT COUNT(*) FROM user_ WHERE name LIKE '%" + name + "%'";
		int count = template.queryForObject(query, Integer.class);
		
		_loggingOperatingTime(false);
		return count;
	}

	@Override
	public List<User> findUsersByName(final String name) {
		_loggingOperatingTime(true);
		
		String query = "SELECT * FROM user_ WHERE userId name '%" + name + "%'";
		List<User> users = template.queryForList(query, User.class);
		
		_loggingOperatingTime(false);
		return users;
	}

	@Override
	public List<User> findUsersByName(final String name, final int start, final int end) {
		_loggingOperatingTime(true);
		
		String query = "SELECT * FROM user_ WHERE name LIKE '%" + name + "%' LIMIT " + start + ", " + end;
		List<User> users = template.queryForList(query, User.class);
		
		_loggingOperatingTime(false);
		return users;
	}

	@Override
	public List<User> findUsersByName(final String name, final int start, final int end, final String orderByColumn, final boolean isDesc) {
		_loggingOperatingTime(true);
		
		String orderBy = isDesc ? "DESC" : "ASC";
		String query = "SELECT * FROM user_ WHERE name LIKE '%" + name + "%' ORDER BY " + orderByColumn + " " + orderBy + " LIMIT " + start + ", " + end;
		List<User> users = template.queryForList(query, User.class);
		
		_loggingOperatingTime(false);
		return users;
	}

	@Override
	public boolean hasByPhoneNumber(final String phoneNumber) {
		_loggingOperatingTime(true);
		
		String query = "SELECT COUNT(*) FROM user_ WHERE phoneNumber = '" + phoneNumber + "'";
		int count = template.queryForObject(query, Integer.class);
		
		_loggingOperatingTime(false);
		return (count > 0);
	}

	@Override
	public User findByPhoneNumber(final String phoneNumber) {
		_loggingOperatingTime(true);
		
		String query = "SELECT * FROM user_ WHERE phoneNumber = '" + phoneNumber + "'";
		User user = template.queryForObject(query, User.class);
		
		_loggingOperatingTime(false);
		return user;
	}

	@Override
	public boolean hasByEmail(final String email) {
		_loggingOperatingTime(true);
		
		String query = "SELECT COUNT(*) FROM user_ WHERE email = '" + email + "'";
		int count = template.queryForObject(query, Integer.class);
		
		_loggingOperatingTime(false);
		return (count > 0);
	}

	@Override
	public User findByEmail(final String email) {
		_loggingOperatingTime(true);
		
		String query = "SELECT * FROM user_ WHERE email = '" + email + "'";
		User user = template.queryForObject(query, User.class);
		
		_loggingOperatingTime(false);
		return user;
	}

	@Override
	public int countUsersByEmail(final String email) {
		_loggingOperatingTime(true);
		
		String query = "SELECT COUNT(*) FROM user_ WHERE email LIKE '%" + email + "%'";
		int count = template.queryForObject(query, Integer.class);
		
		_loggingOperatingTime(false);
		return count;
	}

	@Override
	public List<User> findUsersByEmail(final String email) {
		_loggingOperatingTime(true);
		
		String query = "SELECT * FROM user_ WHERE userId LIKE '%" + email + "%'";
		List<User> users = template.queryForList(query, User.class);
		
		_loggingOperatingTime(false);
		return users;
	}

	@Override
	public List<User> findUsersByEmail(final String email, final int start, final int end) {
		_loggingOperatingTime(true);
		
		String query = "SELECT * FROM user_ WHERE userId LIKE '%" + email + "%' LIMIT " + start + ", " + end;
		List<User> users = template.queryForList(query, User.class);
		
		_loggingOperatingTime(false);
		return users;
	}

	@Override
	public List<User> findUsersByEmail(final String email, final int start, final int end, final String orderByColumn, final boolean isDesc) {
		_loggingOperatingTime(true);
		
		String orderBy = isDesc ? "DESC" : "ASC";
		String query = "SELECT * FROM user_ WHERE email LIKE '%" + email + "%' ORDER BY " + orderByColumn + " " + orderBy + " LIMIT " + start + ", " + end;
		List<User> users = template.queryForList(query, User.class);
		
		_loggingOperatingTime(false);
		return users;
	}

	@Override
	public int countUsersByGender(final String gender) {
		_loggingOperatingTime(true);
		
		String query = "SELECT COUNT(*) FROM user_ WHERE gender = '" + gender + "'";
		int count = template.queryForObject(query, Integer.class);
		
		_loggingOperatingTime(false);
		return count;
	}

	@Override
	public List<User> findUsersByGender(final String gender) {
		_loggingOperatingTime(true);
		
		String query = "SELECT * FROM user_ WHERE gender = '" + gender + "'";
		List<User> users = template.queryForList(query, User.class);
		
		_loggingOperatingTime(false);
		return users;
	}

	@Override
	public List<User> findUsersByGender(final String gender, final int start, final int end) {
		_loggingOperatingTime(true);
		
		String query = "SELECT * FROM user_ WHERE gender = '" + gender + "' LIMIT " + start + ", " + end;
		List<User> users = template.queryForList(query, User.class);
		
		_loggingOperatingTime(false);
		return users;
	}

	@Override
	public List<User> findUsersByGender(final String gender, final int start, final int end, final String orderByColumn, final boolean isDesc) {
		_loggingOperatingTime(true);
		
		String orderBy = isDesc ? "DESC" : "ASC";
		String query = "SELECT * FROM user_ WHERE gender = '" + gender + "' ORDER BY " + orderByColumn + " " + orderBy + " LIMIT " + start + ", " + end;
		List<User> users = template.queryForList(query, User.class);
		
		_loggingOperatingTime(false);
		return users;
	}

	@Override
	public int countUsersByNation(final String nation) {
		_loggingOperatingTime(true);
		
		String query = "SELECT COUNT(*) FROM user_ WHERE nation = '" + nation + "'";
		int count = template.queryForObject(query, Integer.class);
		
		_loggingOperatingTime(false);
		return count;
	}

	@Override
	public List<User> findUsersByNation(final String nation) {
		_loggingOperatingTime(true);
		
		String query = "SELECT * FROM user_ WHERE nation = '" + nation + "'";
		List<User> users = template.queryForList(query, User.class);
		
		_loggingOperatingTime(false);
		return users;
	}

	@Override
	public List<User> findUsersByNation(final String nation, final int start, final int end) {
		_loggingOperatingTime(true);
		
		String query = "SELECT * FROM user_ WHERE nation = '" + nation + "' LIMIT " + start + ", " + end;
		List<User> users = template.queryForList(query, User.class);
		
		_loggingOperatingTime(false);
		return users;
	}

	@Override
	public List<User> findUsersByNation(final String nation, final int start, final int end, final String orderByColumn, final boolean isDesc) {
		_loggingOperatingTime(true);
		
		String orderBy = isDesc ? "DESC" : "ASC";
		String query = "SELECT * FROM user_ WHERE nation = '" + nation + "' ORDER BY " + orderByColumn + " " + orderBy + " LIMIT " + start + ", " + end;
		List<User> users = template.queryForList(query, User.class);
		
		_loggingOperatingTime(false);
		return users;
	}

	@Override
	public int countUsersByStatus(final String status) {
		_loggingOperatingTime(true);
		
		String query = "SELECT COUNT(*) FROM user_ WHERE status = '" + status + "'";
		int count = template.queryForObject(query, Integer.class);
		
		_loggingOperatingTime(false);
		return count;
	}

	@Override
	public List<User> findUsersByStatus(final String status) {
		_loggingOperatingTime(true);
		
		String query = "SELECT * FROM user_ WHERE status = '" + status + "'";
		List<User> users = template.queryForList(query, User.class);
		
		_loggingOperatingTime(false);
		return users;
	}

	@Override
	public List<User> findUsersByStatus(final String status, final int start, final int end) {
		_loggingOperatingTime(true);
		
		String query = "SELECT * FROM user_ WHERE status = '" + status + "' LIMIT " + start + ", " + end;
		List<User> users = template.queryForList(query, User.class);
		
		_loggingOperatingTime(false);
		return users;
	}

	@Override
	public List<User> findUsersByStatus(final String status, final int start, final int end, final String orderByColumn, final boolean isDesc) {
		_loggingOperatingTime(true);
		
		String orderBy = isDesc ? "DESC" : "ASC";
		String query = "SELECT * FROM user_ WHERE status = '" + status + "' ORDER BY " + orderByColumn + " " + orderBy + " LIMIT " + start + ", " + end;
		List<User> users = template.queryForList(query, User.class);
		
		_loggingOperatingTime(false);
		return users;
	}

	@Override
	public int countUsersByLockout(final boolean lockout) {
		_loggingOperatingTime(true);
		
		String query = "SELECT COUNT(*) FROM user_ WHERE lockout = '" + lockout + "'";
		int count = template.queryForObject(query, Integer.class);
		
		_loggingOperatingTime(false);
		return count;
	}

	@Override
	public List<User> findUsersByLockout(final boolean lockout) {
		_loggingOperatingTime(true);
		
		String query = "SELECT * FROM user_ WHERE lockout = '" + lockout + "'";
		List<User> users = template.queryForList(query, User.class);
		
		_loggingOperatingTime(false);
		return users;
	}

	@Override
	public List<User> findUsersByLockout(final boolean lockout, final int start, final int end) {
		_loggingOperatingTime(true);
		
		String query = "SELECT * FROM user_ WHERE lockout = '" + lockout + "' LIMIT " + start + ", " + end;
		List<User> users = template.queryForList(query, User.class);
		
		_loggingOperatingTime(false);
		return users;
	}

	@Override
	public List<User> findUsersByLockout(final boolean lockout, final int start, final int end, final String orderByColumn, final boolean isDesc) {
		_loggingOperatingTime(true);
		
		String orderBy = isDesc ? "DESC" : "ASC";
		String query = "SELECT * FROM user_ WHERE lockout = '" + lockout + "' ORDER BY " + orderByColumn + " " + orderBy + " LIMIT " + start + ", " + end;
		List<User> users = template.queryForList(query, User.class);
		
		_loggingOperatingTime(false);
		return users;
	}
	

	@Override
	public boolean hasByUserIdPassword(String userId, String password) {
_loggingOperatingTime(true);
		
		String query = "SELECT COUNT(*) FROM user_ WHERE userId = '" + userId + "' AND password =  '" + password + "'";
		int count = template.queryForObject(query, Integer.class);
		
		_loggingOperatingTime(false);
		return (count == 1);
	}

	@Override
	public boolean hasByNameEmail(String name, String email) {
		_loggingOperatingTime(true);
		
		String query = "SELECT COUNT(*) FROM user_ WHERE name = '" + name + "' AND email =  '" + email + "'";
		int count = template.queryForObject(query, Integer.class);
		
		_loggingOperatingTime(false);
		return (count == 1);
	}

	@Override
	public User findByNameEmail(String name, String email) {
		_loggingOperatingTime(true);
		
		String query = "SELECT userId FROM user_ WHERE name = '" + name + "' AND email = '" + email + "'";
		System.out.println("query : " + query);
		User user = null;
		
		try {
			String userId = template.queryForObject(query, String.class);
			System.out.println("userId : " + userId);
		} catch (Exception e) {
			System.out.println("error...");
			e.printStackTrace();
		}
		
		_loggingOperatingTime(false);
		return user;
	}
	
	// TODO findByNameEmail 완성하기
	public String findUserIdByNameEmail(String name, String email) {
		_loggingOperatingTime(true);
		
		String query = "SELECT userId FROM user_ WHERE name = '" + name + "' AND email = '" + email + "'";
		String userId = template.queryForObject(query, String.class);
		
		_loggingOperatingTime(false);
		return userId;
	}

	@Override
	public boolean hasByNamePhoneNumber(String name, String phoneNumber) {
		_loggingOperatingTime(true);
		
		String query = "SELECT COUNT(*) FROM user_ WHERE name = '" + name + "' AND phoneNumber =  '" + phoneNumber + "'";
		int count = template.queryForObject(query, Integer.class);
		
		_loggingOperatingTime(false);
		return (count == 1);
	}

	@Override
	public User findByNamePhoneNumber(String name, String phoneNumber) {
		_loggingOperatingTime(true);
		
		String query = "SELECT * FROM user_ WHERE name = '" + name + "' AND phoneNumber = " + phoneNumber + "'";
		User user = template.queryForObject(query, User.class);
		
		_loggingOperatingTime(false);
		return user;
	}

	@Override
	public boolean hasByUserIdNameEmail(String userId, String name, String email) {
		_loggingOperatingTime(true);
		
		String query = "SELECT COUNT(*) FROM user_ WHERE userId = '" + userId + "' AND name =  '" + name + "' AND email =  '" + email + "'";
		int count = template.queryForObject(query, Integer.class);
		
		_loggingOperatingTime(false);
		return (count == 1);
	}

	@Override
	public User findByUserIdNameEmail(String userId, String name, String email) {
		_loggingOperatingTime(true);
		String query = "SELECT * FROM user_ WHERE userId = '" + userId + "' AND name =  '" + name + "' AND email =  '" + email + "'";
		User user = template.queryForObject(query, User.class);
		
		_loggingOperatingTime(false);
		return user;
	}
	
	@Override
	public Long getLatestId() {
		Long latestId = 0L;
		String query = "SELECT id FROM user_ ORDER BY id DESC LIMIT 1";
		latestId = template.queryForObject(query, Long.class);
		return latestId;
	}
}
