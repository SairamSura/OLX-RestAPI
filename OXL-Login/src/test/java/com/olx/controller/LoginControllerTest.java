package com.olx.controller;


import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olx.dto.User;
import com.olx.service.LoginService;
@WebMvcTest(LoginService.class)
class LoginControllerTest {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	LoginController loginController;
	
	@Autowired
	ObjectMapper objectMapper;
	@MockBean
	LoginService loginService;
	
	/*@Test
	public void testuserInfo() throws Exception {
		User user = new User();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization","ABSFS");
		when(this.loginService.userInfo("ABSFS"));
		

		MvcResult mvcResult = this.mockMvc.perform(get("http://localhost:9001/olx/advertises/user"))
				.andExpect(status().isOk())
				.andReturn();
		
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("user"), true);
	}*/
	
	@Test
	public void testRegister() throws  Exception {
		User user = new User();
		user.setUserName("Ram");
		when(this.loginService.register(user)).thenReturn(user);
		
		
		MvcResult mvcResult = this.mockMvc.perform(post("http://localhost:9004/olx/login/user")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(user))
				)
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Ram")))
				.andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Ram"),true);
	}

}
