package com.olx.controller;


import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.olx.dto.Advertises;
import com.olx.service.AdvertisesService;


@WebMvcTest(AdvertisesController.class)
class AdvertisesControllerTest {

	
	@Autowired
	MockMvc mockMvc;
	
	
	@Autowired
	AdvertisesController advertisesController;
	
	
	@MockBean
	AdvertisesService advertisesService;
	
	
	@Autowired
	ObjectMapper objectMapper;


	@Test
	public void testCreateNewAdvertise() throws Exception {
	Advertises advertise = new Advertises();
	advertise.setTitle("Sofa Sale");
	HttpHeaders httpHeaders = new HttpHeaders();
	httpHeaders.set("Authorization", "D78KL");
	
	
	when(this.advertisesService.postAdvertises("VAFEJD", advertise)).thenReturn(advertise);
	
	
	MvcResult mvcResult = this.mockMvc
	.perform(post("http://localhost:9003/advertises/").contentType("application/json")
	.content(objectMapper.writeValueAsString(advertise)).headers(httpHeaders))
	.andExpect(status().isOk()).andExpect(content().string(containsString("Sofa"))).andReturn();
	
	
	String response = mvcResult.getResponse().getContentAsString();
	assertEquals(response.contains("Sofa"), true);
	
	
	}

	@Test
	public void testupdateAdvertise() throws Exception {
	Advertises advertise = new Advertises();
	advertise.setTitle("Sofa Sale");
	HttpHeaders httpHeaders = new HttpHeaders();
	advertise.setTitle("Cricket");
	httpHeaders.set("Authorization", "SG66203");
	when(this.advertisesService.updateAdvertises(1,"FSFEHJ",advertise)).thenReturn(advertise);
	MvcResult mvcResult = this.mockMvc
	.perform(put("http://localhost:9003/advertises/"+1).contentType("application/json")
	.content(objectMapper.writeValueAsString(advertise)).headers(httpHeaders))
	.andExpect(status().isOk()).andExpect(content().string(containsString("Cricket"))).andReturn();
	String response = mvcResult.getResponse().getContentAsString();
	assertEquals(response.contains("Cricket"), true);
	}

	@Test
	public void testgetAllAdvertises() throws Exception {
	List advertiseList = new ArrayList<>();
	advertiseList.add(new Advertises());
	advertiseList.add(new Advertises());
	HttpHeaders httpHeaders = new HttpHeaders();
	httpHeaders.set("Authorization", "HSFWNS");
	when(this.advertisesService.getAdvertises("HSFWNS")).thenReturn(advertiseList);
	MvcResult mvcResult = this.mockMvc
	.perform(get("http://localhost:9003/advertises/user/advertise").headers(httpHeaders))
	.andExpect(status().isOk()).andReturn();
	String response = mvcResult.getResponse().getContentAsString();
	assertEquals(response.contains("title"), true);
	
	
	}

	@Test
	public void testGetAdvertiseAllById() throws Exception {
	Advertises advertise = new Advertises();
	advertise.setTitle("Sofa Sale");
	HttpHeaders httpHeaders = new HttpHeaders();
	List advertiseList = new ArrayList<>();
	advertiseList.add(advertise);
	httpHeaders.set("Authorization", "SG66200");
	when(this.advertisesService.getPostedAdvertises(1, "SG66200")).thenReturn((Advertises) advertiseList);
	MvcResult mvcResult = this.mockMvc
	.perform(get("http://localhost:9003/advertises/user/advertises/" + 1).headers(httpHeaders))
	.andExpect(status().isOk()).andExpect(content().string(containsString("Sofa"))).andReturn();
	String response = mvcResult.getResponse().getContentAsString();
	assertEquals(response.contains("Sofa"), true);
	}

	@Test
	public void testDeleteAdvertiseAllById() throws Exception {
	Advertises advertisefirst = new Advertises();
	advertisefirst.setTitle("Sofa Sale");
	HttpHeaders httpHeaders = new HttpHeaders();
	List advertiseList = new ArrayList<>();
	advertiseList.add(advertisefirst);
	httpHeaders.set("Authorization", "SG66200");
	when(this.advertisesService.deleteAdvertisesPostedByUser(1, "SG66200")).thenReturn(true);
	MvcResult mvcResult = this.mockMvc
	.perform(delete("http://localhost:9003/advertises/user/advertise/" + 1).headers(httpHeaders))
	.andExpect(status().isOk()).andReturn();
	String response = mvcResult.getResponse().getContentAsString();
	assertEquals(response.contains("true"), true);
	}


	@Test
	public void testSearchAdvertisesByFilterCriteria() throws Exception {
	List advertiseList = new ArrayList();
	advertiseList.add(new Advertises());
	advertiseList.add(new Advertises());
	when(this.advertisesService.searchAdvertisesByFilterCriteria(null, 0, null, null, null, null, null, null, 0, 10))
	.thenReturn(advertiseList);
	
	
	MvcResult mvcResult = this.mockMvc.perform(get("http://localhost:9003/advertises/search/filtercriteria")
	.param("category", "0").param("startIndex", "0")).andExpect(status().isOk()).andReturn();
	
	
	String response = mvcResult.getResponse().getContentAsString();
	assertEquals(response.contains("title"), true);
	}


@Test
	public void testSearchAdvByText() throws Exception {
Advertises advertise = new Advertises();
advertise.setTitle("Sofa Sale");
HttpHeaders httpHeaders = new HttpHeaders();
List advertiseList = new ArrayList<>();
advertiseList.add(advertise);
httpHeaders.set("Authorization", "SG66200");
when(this.advertisesService.getAdvertisesBySearch("Sofa")).thenReturn((Map<String, List<Advertises>>) advertiseList);
MvcResult mvcResult = this.mockMvc
.perform(get("http://localhost:9087/advertises/search?searchText=Sofa").headers(httpHeaders))
.andExpect(status().isOk()).andExpect(content().string(containsString("Sofa"))).andReturn();
String response = mvcResult.getResponse().getContentAsString();
assertEquals(response.contains("Sofa"), true);
}


	@Test
	public void testGetAdvertiseById() throws Exception {
	Advertises advertise = new Advertises();
	advertise.setTitle("Tv Sale");
	HttpHeaders httpHeaders = new HttpHeaders();
	httpHeaders.set("Authorization", "AG66200");
	when(this.advertisesService.getAdvertiseById(1, "AG66200")).thenReturn(advertise);
	MvcResult mvcResult = this.mockMvc
	.perform(get("http://localhost:9003/advertises/user/advertise/" + 1).headers(httpHeaders))
	.andExpect(status().isOk()).andExpect(content().string(containsString("Tv"))).andReturn();
	String response = mvcResult.getResponse().getContentAsString();
	assertEquals(response.contains("Tv"), true);
	}



}