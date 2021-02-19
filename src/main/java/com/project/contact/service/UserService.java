package com.project.contact.service;

import java.util.List;

import com.project.contact.entity.User;
import com.project.contact.web.UserData;

public interface UserService {

	List<User> getContactList();

	User saveUser(UserData userData) throws Exception;

	boolean updateContact(UserData userData) throws Exception;

	User getUser(Long uId);

}
