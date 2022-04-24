
package com.olx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.User;
import com.olx.service.LoginService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/olx/login")
@CrossOrigin(origins = "*")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	@PostMapping(value="/user/authentication", consumes={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Get userId and password",notes="This Rest Api gives userId AND Password")
	public ResponseEntity<String> authentication(@RequestBody User user) {
		return new ResponseEntity<String>(loginService.authentication(user),HttpStatus.OK);
	}
	@DeleteMapping(value="/user/logout")
	@ApiOperation(value="Logouts User",notes="This Rest Api Used to Logout User")
	public ResponseEntity<Boolean>  logout(@RequestHeader("Authorization") String authToken) {
		return new ResponseEntity<Boolean>(loginService.logout(authToken),HttpStatus.OK);
	}
	@PostMapping(value="/user", consumes={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Updates user",notes="This Rest Api Updates user")
	public ResponseEntity<User> register(@RequestBody User user) {
		return new ResponseEntity<User>(loginService.register(user),HttpStatus.OK);
	}
	
	@GetMapping(value="/user", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Get userInformation",notes="This Rest Api Details of User")
	public ResponseEntity<User> userInfo(@RequestHeader("Authorization") String authToken) {
		return new ResponseEntity<User>(loginService.userInfo(authToken),HttpStatus.OK);
	}
	@GetMapping(value="/token/validate")
	@ApiOperation(value="Validating user",notes="This Rest Api gives True or False by AuthToken")
	public ResponseEntity<Boolean> validateUser(@RequestHeader("Authorization") String authToken) {
		return new ResponseEntity<Boolean>(loginService.validateUser(authToken),HttpStatus.OK);
	}
}
