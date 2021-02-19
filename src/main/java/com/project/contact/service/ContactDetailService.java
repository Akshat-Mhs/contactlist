package com.project.contact.service;

import java.util.List;

import com.project.contact.web.Contacts;

public interface ContactDetailService {

	boolean updateContactDetails(Long uid, List<Contacts> contacts) throws Exception;

	boolean deleteContact(Long id);
}
