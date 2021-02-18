package com.project.contact.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.project.contact.entity.ContactDetail;
import com.project.contact.entity.User;

public class UserData implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty
	private Long id;
	@JsonProperty
	private String firstName;
	@JsonProperty
	private String lastName;
	@JsonProperty
	private String email;
	@JsonProperty
	private String userName;
	@JsonProperty
	private String password;
	private Date createDate;
	@JsonProperty
	private String number;
	@JsonProperty
	private boolean active;
	private List<Contacts> contacts = new ArrayList<Contacts>();

	public UserData() {
		System.out.println("Default Constructor is called...!!");
	}
	public UserData(String data) {
		System.out.println(data);
		Gson g = new Gson(); 
		UserData d = g.fromJson(data, UserData.class);
		this.id = d.getId();
		this.firstName = d.getFirstName();
		this.lastName = d.getLastName();
		this.email = d.getEmail();
		this.userName = d.getUserName();
		this.password = d.getPassword();
		this.createDate = new Date();
		this.number = d.getNumber();
		this.active = d.isActive();
		this.contacts.add(new Contacts(d.getNumber()));
	}
	
	public UserData(User user) {
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.userName = user.getUserName();
		for (ContactDetail cd : user.getContactDetails()) {
			this.contacts.add(new Contacts(cd));
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public List<Contacts> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contacts> contacts) {
		this.contacts = contacts;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

}
