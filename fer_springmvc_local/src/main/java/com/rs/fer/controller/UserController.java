package com.rs.fer.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rs.fer.service.UserService;
import com.rs.fer.vo.RegistrationVO;

@Controller
public class UserController {
	 
    	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView welcome() throws IOException {
		ModelAndView mv = new ModelAndView();
		System.out.println("for teju my out");
		mv.setViewName("Login");
		return mv;
	}
	
	@RequestMapping(value = { "/registration" }, method = RequestMethod.GET)
	public ModelAndView displayRegistration() throws IOException {
		return new ModelAndView("Registration");
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute RegistrationVO registrationVO) {
		ModelAndView mv = new ModelAndView();

		boolean isAdded = userService.saveUser(registrationVO);
		String nextPath = "";
		if (isAdded) {
			mv.addObject("status", "User registered successfully");
			nextPath = "Login";
		} else {
			mv.addObject("status", "User registration is failed");
			nextPath = "Registration";
		}
		
		mv.setViewName(nextPath);
		
		return mv;
	}
}
