package com.project.contact.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.contact.entity.ContactDetail;

public interface ContactDetailDao extends JpaRepository<ContactDetail, Long> {

	ContactDetail findByPhoneNumber(String number);

}
