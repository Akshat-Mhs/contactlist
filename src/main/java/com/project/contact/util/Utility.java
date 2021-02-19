package com.project.contact.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.project.contact.dao.ContactDetailDao;
import com.project.contact.dao.UserDao;
import com.project.contact.entity.ContactDetail;
import com.project.contact.entity.User;
import com.project.contact.web.Contacts;
import com.project.contact.web.UserData;

public class Utility {

	public static List<String> validateData(UserData userData, UserDao userDao, ContactDetailDao contactDetailDao) throws Exception {
		List<String> errors = new ArrayList<String>();
		// Email validation
		Pattern pattern = Pattern.compile("^(.+)@(.+)$");
		Matcher matcher = pattern.matcher(userData.getEmail());
		if (!matcher.matches())
			errors.add("Email id entered is not right.");

		phoneNumberValidation(userData, errors, contactDetailDao);
		// user Name Unique check
		if (userNotAllowed(userData.getUserName(), userDao))
			errors.add("User Name is already in use.");

		return errors;
	}

	public static void phoneNumberValidation(UserData userData, List<String> errors, ContactDetailDao contactDetailDao) {
		// Phone Number validation
		Pattern pattern = Pattern.compile("^\\d{10}$");
		Matcher matcher = pattern.matcher(userData.getNumber());
		if (!matcher.matches())
			errors.add("Phone number entered is incorrect.");

		// Phone number is unique
		if (phoneNumberNotAllowed(userData.getNumber(), contactDetailDao))
			errors.add("Phone number is already allocated to someone.");
	}

	public static boolean phoneNumberNotAllowed(String number, ContactDetailDao contactDetailDao) {
		boolean result = false;
		ContactDetail contactDetail = contactDetailDao.findByPhoneNumber(number);
		if (!Objects.isNull(contactDetail))
			result = true;
		return result;
	}

	public static boolean userNotAllowed(String userName, UserDao userDao) {
		boolean result = false;
		User user = userDao.findByUserName(userName);
		if (!Objects.isNull(user))
			result = true;
		return result;
	}

	public static String convertListToString(List<String> errors) {
		StringBuilder v = new StringBuilder();
		for (String string : errors) {
			v.append(string).append("\n");
		}
		
		return v.toString();
	}

	public static List<String> validateData(List<Contacts> contacts, ContactDetailDao contactDetailDao) {
		List<String> errors = new ArrayList<String>();
		for (Contacts contact : contacts) {
			// Phone Number validation
			Pattern pattern = Pattern.compile("^\\d{10}$");
			Matcher matcher = pattern.matcher(contact.getNumber());
			if (!matcher.matches())
				errors.add("Phone number "+contact.getNumber()+" entered is incorrect.");

			// Phone number is unique
			if (phoneNumberNotAllowed(contact.getNumber(), contactDetailDao))
				errors.add("Phone number "+contact.getNumber()+" is already allocated to someone.");
		}
		return errors;
	}

}
