package kr.dev_mook.user_manager.dao;

import java.time.LocalDate;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.dev_mook.user_manager.model.User;

public class UserDaoTest {
	
	@Autowired
	private SqlSession _sqlSession;
	
	@Test
	public void createUser() {
		User user = new User(1L);
		user.setUserId("test1");
		user.setPassword("1234");
		user.setIsTempPw(false);
		user.setName("홍길동");
		user.setPhoneNumber("010-1234-1234");
		user.setEmail("test@injeong.co.kr");
		user.setBirth("1993-01-18");
		user.setGender("male");
		user.setAddress("Sejong");
		user.setNation("ROK");
		user.setStatus("active");
		user.setCreateDate(LocalDate.now());
		user.setEmailVerified(true);
		user.setSiteTermsOfUse(true);
		user.setUserInfoTermsOfUse(true);
		user.setEventTermsOfUse(true);
		
//		try {
//			System.out.println("dao is null? " + (dao==null));
//			boolean success = dao.createUser(user);
//			assertTrue(success);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
}
