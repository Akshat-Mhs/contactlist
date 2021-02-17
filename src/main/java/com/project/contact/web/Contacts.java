package com.project.contact.web;

import java.io.Serializable;

import com.project.contact.entity.ContactDetail;

public class Contacts implements Serializable {

	private static final long serialVersionUID = 1L;

	private String number;
	private Long id;
	private boolean active;

	public Contacts(ContactDetail cd) {
		this.id = cd.getId();
		this.number = cd.getPhoneNumber();
		this.active = cd.isActive();
	}

	public Contacts(String number) {
		this.number = number;
		this.active = true;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
