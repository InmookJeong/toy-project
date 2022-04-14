package kr.dev_mook.user_manager.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User Management Project Home Controller.
 * @author Inmook, Jeong
 * @since 2022.03.31
 *
 */
@Controller
public class HomeController {
	
	private final Logger _logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Model model, Locale locale) {
		
		String country = locale.getCountry();
		String ip = request.getRemoteAddr();
		
		_logger.debug("##### Connected user's country is '" + country + "', and IP address is '" + ip + "'.");
		
		return "home";
	}
	

}
