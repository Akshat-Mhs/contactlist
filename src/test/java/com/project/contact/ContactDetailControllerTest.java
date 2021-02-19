package com.project.contact;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.project.contact.controller.ContactDetailController;
import com.project.contact.entity.ContactDetail;
import com.project.contact.entity.User;
import com.project.contact.web.UserData;

@RunWith(SpringRunner.class)
@WebMvcTest(ContactDetailController.class)
public class ContactDetailControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ContactDetailController contactDetailController;

	@Test
	public void deleteContact() throws Exception {
		given(contactDetailController.getDeletecontact(100L)).willReturn(true);

		mvc.perform(get("/api/contact/deletecontact").with(user("admin").password("admin"))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	public void updateContacts() throws Exception {
		given(contactDetailController.updateContacts(getUser())).willReturn(true);

		mvc.perform(post("/api/contact/updateContacts").with(user("admin").password("admin"))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
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
