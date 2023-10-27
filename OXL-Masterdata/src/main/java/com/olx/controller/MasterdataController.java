package com.olx.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.Advertises;
import com.olx.dto.Categories;
import com.olx.service.MasterdataService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/olx/masterdata")
@CrossOrigin(origins="*")
public class MasterdataController {
	
	@Autowired
	MasterdataService masterdataService;
	@GetMapping(value="/advertise",produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Gets All Categories", notes = "This REST API Gives All Categories")
	public ResponseEntity<Map<String,List<Categories>>> getCategories() {
		System.out.println("Add Line to these File");
		return new ResponseEntity<Map<String,List<Categories>>>(masterdataService.getCategories(),HttpStatus.OK);
	}
	
	@GetMapping(value="/advertise/status", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Gets All Advertise Status", notes = "This REST API Gives All Advertise Status")
	public ResponseEntity<Map<String,List<Advertises>>> getStatus() {
		return new ResponseEntity<Map<String,List<Advertises>>>(masterdataService.getStatus(),HttpStatus.OK);
	}
	@GetMapping(value="/category/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getCategoryName(@PathVariable("id") int categoryId) {
		return new ResponseEntity<String>(masterdataService.getCategoryName(categoryId),HttpStatus.OK);
	}
	@GetMapping(value="/status/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String>  getStatusName(@PathVariable("id") int statusId) {
		return new ResponseEntity<String>(masterdataService.getStatusName(statusId),HttpStatus.OK);
	}
	
}
