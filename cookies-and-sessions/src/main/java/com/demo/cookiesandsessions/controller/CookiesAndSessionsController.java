package com.demo.cookiesandsessions.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.demo.cookiesandsessions.model.UserInfoDTO;

@Controller
//@SessionAttributes("userInfo") //is used to store the model(pojo) objects 
public class CookiesAndSessionsController {

	@RequestMapping("/homePage")
	public String printHelloWorld(Model model) {
		//read the default properties by fetching it from DTO class
		UserInfoDTO userInfo=new UserInfoDTO();
		model.addAttribute("userInfo",userInfo);
		return "index";
	}
	
	//Use of @ModelAttribute annotation: we can get the input values through form tag modelAttribute and bind to UserInfoDTO properties
	@RequestMapping("/")
	public String homePage(@ModelAttribute("userInfo") UserInfoDTO userInfoDTO,HttpServletRequest req) {
		//Method -1: GetCookie and set in yourName input
		Cookie[] cookies=req.getCookies();
		System.out.println(cookies);
		for(Cookie theCookie:cookies) {
			System.out.println(theCookie.getValue());
			if("lcapp-userName".equals(theCookie.getName())) {
				userInfoDTO.setYourName(theCookie.getValue());
			}
		}
		
		return "index";
	}

	@RequestMapping("/process-page")
	public String getResult(@RequestParam("yourName") String yourName,
			@RequestParam("crushName") String crushName,Model model) {
		//@RequestParam is basically used to bind the url query string parameters to the method parameter 
		model.addAttribute("yourName",yourName);
		model.addAttribute("crushName",crushName);
		return "result-home";
	}
	
	
	//Another Way to binding variables through POJO class 
	@RequestMapping("/process-page-dto")
	public String getResults(UserInfoDTO userInfoDTO,Model model) {
		//writing the value to the DTO class properties by fetching from url
		model.addAttribute("yourName",userInfoDTO.getYourName());
		model.addAttribute("crushName",userInfoDTO.getCrushName());
		return "result-home";
	}
	
	@RequestMapping("/process-page-dto-modelAttribute")
	public String getResults(@Valid @ModelAttribute("userInfo") UserInfoDTO userInfoDTO,BindingResult result,HttpServletResponse response,HttpServletRequest req) {
		//writing the value to the DTO class properties by fetching from url
		if(result.hasErrors()) {
			return "index";
		}
		
		/*
		 * Add cookies in browser-->track cookie by going in Developer section -->then
		 * in Application-Storage(if you are using chrome)Cookies are stored in txt
		 * files in browser location
		 */
		
		System.out.println(userInfoDTO.getYourName());
		Cookie cookie=new Cookie("lcapp-userName",userInfoDTO.getYourName());
		response.addCookie(cookie);
		cookie.setMaxAge(60*60);//setting expiry of cookie 
		
		/*
		 * Sessions- Cookies have some limitations- If we want to store large details
		 * more than 4kb the we should go for sessions.
		 */
		HttpSession session=req.getSession();
		session.setAttribute("sessionUserName", userInfoDTO.getYourName());
		/*
		 * In sessions we don't need to add details ,it autometically done by server.
		 */		
		return "result-home";
	}
	
	
	
	@RequestMapping("/email-landing")
	public String emailPageLanding(@CookieValue("lcapp-userName") String userName,Model model) {
		//Spring is providing @CookieValue annotation to get the cookie value directly
		model.addAttribute("userName",userName);
		return "email-home";
	}
	
	

}
