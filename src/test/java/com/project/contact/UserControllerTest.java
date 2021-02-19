package com.project.contact;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.project.contact.controller.UserController;
import com.project.contact.entity.ContactDetail;
import com.project.contact.entity.User;
import com.project.contact.web.UserData;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserController userController;

	@Test
	public void getContactList() throws Exception {
		given(userController.getContactList()).willReturn(getUsers());

		mvc.perform(get("/api/user/contactList").with(user("admin").password("admin"))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(10))).andExpect(jsonPath("$[0].firstName", is("FirstName - 0")));
	}

	@Test
	public void getContacts() throws Exception {
		given(userController.getContacts(1L)).willReturn(getUser());

		mvc.perform(
				get("/api/user/contacts").with(user("admin").password("admin")).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void addUser() throws Exception {
		given(userController.saveUser(getUser())).willReturn(getUser());

		mvc.perform(
				post("/api/user/add").with(user("admin").password("admin")).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void update() throws Exception {
		given(userController.updateContact(getUser())).willReturn(true);

		mvc.perform(
				post("/api/user/update").with(user("admin").password("admin")).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	
	private List<UserData> getUsers() {
		List<User> users = new ArrayList<User>();
		for (int i = 0; i < 10; i++) {
			ContactDetail contactDetail = new ContactDetail();
			contactDetail.setId(Long.parseLong(i + ""));
			contactDetail.setPhoneNumber("989898989" + i);
			contactDetail.setActive(i % 2 == 0 ? true : false);

			User user = new User();
			user.setFirstName("FirstName - " + i);
			user.setLastName("LastName - " + i);
			user.setEmail("Test" + i + "@evolent.com");
			user.setContactDetails(Lists.list(contactDetail));

			users.add(user);
		}
		List<UserData> userDatas = new ArrayList<UserData>();
		for (User user : users) {
			UserData ud = new UserData(user);
			userDatas.add(ud);
		}
		return userDatas;
	}

	private UserData getUser() {
		ContactDetail contactDetail = new ContactDetail();
		contactDetail.setId(100L);
		contactDetail.setPhoneNumber("9898989890");
		contactDetail.setActive(Math.random() % 2 == 0 ? true : false);

		User user = new User();
		user.setFirstName("FirstName - 7");
		user.setLastName("LastName - 8");
		user.setEmail("Test007@evolent.com");
		user.setContactDetails(Lists.list(contactDetail));

		UserData ud = new UserData(user);
		return ud;
	}
}
