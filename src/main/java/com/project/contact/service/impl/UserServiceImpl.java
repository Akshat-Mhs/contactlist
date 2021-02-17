package com.project.contact.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.contact.dao.ContactDetailDao;
import com.project.contact.dao.UserDao;
import com.project.contact.entity.ContactDetail;
import com.project.contact.entity.User;
import com.project.contact.service.UserService;
import com.project.contact.web.UserData;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ContactDetailDao contactDetailDao;
	
	@Override
	public List<User> getContactList() {
		return userDao.findAll();
	}

	@Override
	public User saveUser(UserData userData) {
		User user = new User();
		user.setCreateDate(new Date());
		user.setEmail(userData.getEmail());
		user.setFirstName(userData.getFirstName());
		user.setLastName(userData.getLastName());
		user.setPassword(userData.getPassword().trim());
		user.setUserName(userData.getUserName());
		user = userDao.save(user);
		
		ContactDetail contactDetail = new ContactDetail();
		contactDetail.setActive(true);
		contactDetail.setPhoneNumber(userData.getNumber());
		contactDetail.setUser(user);
		contactDetail = contactDetailDao.save(contactDetail);
		
		user.setContactDetails(Arrays.asList(contactDetail));
		return user;
	}

}
