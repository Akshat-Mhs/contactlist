package com.project.contact.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.contact.entity.User;
import com.project.contact.service.UserService;
import com.project.contact.web.UserData;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/contactList")
	public List<UserData> getContactList() {
		List<User> users = userService.getContactList();
		List<UserData> userDatas = new ArrayList<UserData>();
		for (User user : users) {
			UserData ud = new UserData(user);
			userDatas.add(ud);
		} 
		return userDatas;
	}

	@PostMapping("/add")
	public UserData saveUser(UserData userData) {
		User user = userService.saveUser(userData);
		return new UserData(user);
	}

	@PostMapping("/update")
	public boolean updateContact(UserData userData) {
		return userService.updateContact(userData);
	}
}
