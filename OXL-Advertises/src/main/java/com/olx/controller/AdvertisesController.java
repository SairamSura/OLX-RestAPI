package com.olx.controller;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.Advertises;
import com.olx.service.AdvertisesService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/olx/advertises")
@CrossOrigin(origins ="*")
public class AdvertisesController {
	
	@Autowired
	AdvertisesService advertisesService;
	@PostMapping(value="/advertise",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Posts new advertise",notes="This REST API Post new Advertise")
	public ResponseEntity<Advertises> postAdvertises(@RequestHeader("Authorization") String authToken,@RequestBody Advertises advertises) {
		return new ResponseEntity<Advertises>(advertisesService.postAdvertises(authToken, advertises),HttpStatus.OK);
	}
	
	@PutMapping(value="/advertise/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Updates existing advertise",notes="This REST API Updates existing advertise")
	public ResponseEntity<Advertises> updateAdvertises(@PathVariable("id") int advertisesId,@RequestHeader("Authorization") String authToken,@RequestBody Advertises advertises) {
		return new ResponseEntity<Advertises>(advertisesService.updateAdvertises(advertisesId, authToken, advertises),HttpStatus.OK);
	}
	@GetMapping(value="/user/advertise",produces ={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Gets advertise posted by user",notes="This REST API returns List of All Stocks")
	public ResponseEntity<List<Advertises>> getAdvertises(@RequestHeader("Authorization") String authToken) {
		return new ResponseEntity<List<Advertises>>(advertisesService.getAdvertises(authToken),HttpStatus.OK);
	}
	@GetMapping(value="/user/advertise/{advertiseId}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Gets Advertises postedy by logged in user",notes="This REST API Reads specific advertisement posed by logged in user")
	public ResponseEntity<Advertises> getPostedAdvertisesByUser(@PathVariable("advertiseId") int advertiseId ,@RequestHeader("Authorization") String authToken) {
		return new ResponseEntity<Advertises>(advertisesService.getPostedAdvertises(advertiseId, authToken),HttpStatus.OK);
	}
	@DeleteMapping(value="/user/advertise/{advertiseId}")
	@ApiOperation(value="Deletes the advertises by user",notes="This REST API Deletes specific advertisement posted by logged in user")
	public ResponseEntity<Boolean> deleteAdvertisesPostedByUser(@PathVariable("advertiseId") int advertiseId, @RequestHeader("Authorization") String authtoken) {
		return new ResponseEntity<Boolean>(advertisesService.deleteAdvertisesPostedByUser(advertiseId, authtoken),HttpStatus.OK);
	}
	
	@GetMapping(value="/search/filtercriteria", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Advertises>> searchAdvertisesByFilterCriteria(@RequestParam(name="searchText", required = false)String searchText,
	@RequestParam(name = "category", required = false, defaultValue = "0")Integer categoryId, @RequestParam(name="postedBy", required=false)String postedBy,
	@RequestParam(name="dateCondition", required=false)String dateCondition,
	@RequestParam(name="onDate", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate onDate,
	@RequestParam(name="fromDate", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
	@RequestParam(name="toDate", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
	@RequestParam(name="sortedBy", required=false)String sortedBy, @RequestParam(name = "startIndex", defaultValue="0")int startIndex, @RequestParam(name="records", defaultValue = "10")int records
	) {
	List<Advertises> advertises = advertisesService.searchAdvertisesByFilterCriteria(searchText, categoryId, postedBy, dateCondition,
	onDate, fromDate, toDate, sortedBy, startIndex, records);
	return new ResponseEntity<List<Advertises>>(advertises,HttpStatus.OK);
	}
	
	@GetMapping(value="/advertise/search")
	@ApiOperation(value="Gets All the advertises of match to serach criteria",notes="This REST API Matches advertisements using the provided 'searchText' within all fields of an advertise.")
	public ResponseEntity<Map<String,List<Advertises>>> getAdvertisesBySearch(@RequestParam(name = "searchText") String searchText) {
		return new ResponseEntity<Map<String,List<Advertises>>>(advertisesService.getAdvertisesBySearch(searchText),HttpStatus.OK);
	}
	@GetMapping(value="/advertise/{advertiseId}",produces ={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Return advertise details",notes="This REST API returns Return advertise details")
	public ResponseEntity<Advertises> getAdvertiseById(@PathVariable("advertiseId") int advertiseId, @RequestHeader("Authorization") String authToken) {
		return new ResponseEntity<Advertises>(advertisesService.getAdvertiseById(advertiseId, authToken),HttpStatus.OK);
	}
	}
