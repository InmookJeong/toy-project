package kr.dev_mook.file_manager.controller;

import java.util.Locale;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.dev_mook.file_manager.constants.ViewConstants;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "file")
public class FileController {
	
	private static final Logger _logger = LoggerFactory.getLogger(FileController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = {"", "/", "/file-manager"}, method = RequestMethod.GET)
	public String fileManager(Locale locale, Model model) {
		_logger.info("파일 관리 화면 이동");
		
		return ViewConstants.FILE_MANAGER_VIEW;
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String fileManger(HttpServletRequest request, ServletResponse response, @RequestParam("file") MultipartFile file){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		System.out.println("here..");
		MultipartFile file1 = multipartRequest.getFile("file");
		System.out.println("name : " + file1.getName());
		System.out.println("size : " + file1.getSize());
		System.out.println("path : " + file1.getOriginalFilename());
		
		return "ok";
	}
	
}
