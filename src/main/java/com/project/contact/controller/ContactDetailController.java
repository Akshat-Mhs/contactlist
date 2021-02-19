package com.project.contact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.contact.service.ContactDetailService;
import com.project.contact.web.UserData;

@RestController
@RequestMapping("/api/contact")
public class ContactDetailController {

	@Autowired
	private ContactDetailService contactDetailService;

	@PostMapping("/updateContacts")
	public boolean updateContacts(UserData userData) throws Exception {
		return contactDetailService.updateContactDetails(userData.getId(), userData.getContacts());
	}

	@GetMapping("/deletecontact")
	public boolean getDeletecontact(Long id) {
		return contactDetailService.deleteContact(id);
	}

}
