package com.purpletalk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.purpletalk.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	/*@SuppressWarnings("rawtypes")
	@RequestMapping(value="/user", method = RequestMethod.GET)
	public List listUser(){
		return userService.findAll();
	}
*/
	@GetMapping(value="/")
	public String homePage(){
		return "testUser";
	}

	@GetMapping(value="/private")
	public List listUser(){
		return userService.findAll();
	}
}
