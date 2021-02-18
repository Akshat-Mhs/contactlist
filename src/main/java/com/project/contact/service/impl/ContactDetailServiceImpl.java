package com.project.contact.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.contact.dao.ContactDetailDao;
import com.project.contact.dao.UserDao;
import com.project.contact.entity.ContactDetail;
import com.project.contact.entity.User;
import com.project.contact.service.ContactDetailService;
import com.project.contact.web.Contacts;

@Service
public class ContactDetailServiceImpl implements ContactDetailService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ContactDetailDao contactDetailDao;
	
	@Override
	public boolean updateContactDetails(Long uid, List<Contacts> contacts) {
		User user = null; 
		if (uid != null && uid != 0) {
			user = userDao.getOne(uid);
		}

		if (Objects.isNull(user)) {
			ContactDetail cd = contactDetailDao.getOne(contacts.get(0).getId());
			user = cd.getUser();
		}

		for (Contacts contact : contacts) {
			ContactDetail contactDetail = new ContactDetail();
			Long cId = contact.getId();
			String stringId = String.valueOf(cId);
			if (!stringId.startsWith("99999")) {
				contactDetail.setId(contact.getId());
			}
			
			contactDetail.setActive(contact.isActive());
			contactDetail.setPhoneNumber(contact.getNumber());
			contactDetail.setUser(user);

			contactDetail = contactDetailDao.save(contactDetail);
		}
		
		return true;
	}

	@Override
	public boolean deleteContact(Long id) {
		contactDetailDao.deleteById(id);
		return true;
	}

}
