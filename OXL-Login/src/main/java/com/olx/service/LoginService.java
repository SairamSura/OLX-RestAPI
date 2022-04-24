package com.olx.service;


import com.olx.dto.User;

public interface LoginService {
	public String authentication( User user);
	public boolean logout(String authToken);
	public User register(User user);
	public User userInfo(String authToken);
	public boolean validateUser(String authToken);
}
