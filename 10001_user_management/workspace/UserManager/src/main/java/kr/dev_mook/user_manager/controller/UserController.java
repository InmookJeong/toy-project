package kr.dev_mook.user_manager.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.dev_mook.user_manager.dao.UserDao;
import kr.dev_mook.user_manager.model.User;

@Controller
public class UserController {
	
	private final Logger _logger = LoggerFactory.getLogger(UserController.class);
	private final String ACCOUNT_PATH = "account/";
	
	private UserDao dao;
	
	@Autowired
	public void setUserDao(UserDao dao) {
		this.dao = dao;
	}
	
	@RequestMapping(value = "/sign-in", method = RequestMethod.POST)
	public String signIn(HttpServletRequest request, Model model, Locale locale) {
		_logger.info("로그인 완료");
		
		String userId = (String)request.getParameter("userId");
		String password = (String)request.getParameter("password");
		
		if(this.dao.hasByUserIdPassword(userId, password)) {
			model.addAttribute("isSignIn", true);
			model.addAttribute("userId", userId);
		} else {
			model.addAttribute("failedSignIn", true);
		}
		
		return "home";
	}
	
	@RequestMapping(value = "/sign-up", method = RequestMethod.GET)
	public String goToSignUpPage(HttpServletRequest request, Model model, Locale locale) {
		_logger.info("회원가입 페이지로 이동");
		return ACCOUNT_PATH + "sign_up";
	}
	
	@RequestMapping(value = "/sign-up", method = RequestMethod.POST)
	public String signUp(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("user") User user, Model model) {
		
		// Model Count 테이블 설계할 것인지 고민하기
		Long newId = dao.getLatestId() + 1;
		user.setId(newId);
		user.setIsTempPw(false);
		user.setCreateDate(LocalDate.now());
		
		boolean success = this.dao.createUser(user);
		System.out.println("회원가입 완료");
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/duplicate-check", method = RequestMethod.POST)
	public void existUserId(HttpServletRequest request, HttpServletResponse response) {
		
		String column = (String)request.getParameter("column");
		String value = (String)request.getParameter("value");
		
		boolean isExist = false;
		if(column.equals("userId")) {
			isExist = dao.hasByUserId(value);
		} else if(column.equals("email")) {
			isExist = dao.hasByEmail(value);
		} else if(column.equals("phoneNumber")) {
			isExist = dao.hasByPhoneNumber(value);
		} else {
		}
		
		JSONObject json = new JSONObject();
		json.put("isDuplicate", isExist);
		response.setContentType("application/json");
		try {
			response.getWriter().print(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/forgot-id", method = RequestMethod.GET)
	public String goToForgotIdPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		_logger.info("아이디 찾기 화면으로 이동");
		model.addAttribute("status", true);
		return ACCOUNT_PATH + "forgot_id";
	}
	
	@RequestMapping(value = "/forgot-id", method = RequestMethod.POST)
	public String forgotId(HttpServletRequest request, HttpServletResponse response, Model model) {
		String name = request.getParameter("name").toString();
		String email = request.getParameter("email").toString();
		
		if(dao.hasByNameEmail(name, email)) {
			String userId = dao.findUserIdByNameEmail(name, email);
			model.addAttribute("status", true);
			model.addAttribute("userId", userId);
			return ACCOUNT_PATH + "find_id";
		} else {
			model.addAttribute("status", false);
			return ACCOUNT_PATH + "forgot_id";
		}
	}
	
	@RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
	public String goToForgotPasswordPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		_logger.info("비밀번호 찾기 화면으로 이동");
		model.addAttribute("status", true);
		return ACCOUNT_PATH + "forgot_password";
	}
	
	@RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
	public String forgotPassword(HttpServletRequest request, HttpServletResponse response, Model model) {
		String userId = request.getParameter("userId").toString();
		String name = request.getParameter("name").toString();
		String email = request.getParameter("email").toString();
		
		if(dao.hasByUserIdNameEmail(userId, name, email)) {
			model.addAttribute("userId", userId);
			model.addAttribute("status", true);
			return ACCOUNT_PATH + "change_password";
		} else {
			model.addAttribute("status", false);
			return ACCOUNT_PATH + "forgot_password";
		}
	}
	
	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	public String changePassword(HttpServletRequest request, HttpServletResponse response, Model model) {
		String userId = request.getParameter("userId").toString();
		String password = request.getParameter("password").toString();
		
		boolean result = dao.updatePassword(userId, password);
		
		return "redirect:/";
	}
}
