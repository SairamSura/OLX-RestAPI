package com.olx.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.olx.dto.User;
import com.olx.entity.BlacklistedTokensDocument;
import com.olx.entity.UserEntity;
import com.olx.exception.InvalidAuthTokenException;
import com.olx.exception.InvalidCredentialsException;
import com.olx.repository.UserMongoBlackListedRepo;
import com.olx.repository.UserRepo;
import com.olx.security.JwtUtil;



@Service
@Primary
public class LoginServiceImp implements LoginService{
	@Autowired
	UserMongoBlackListedRepo userMongoRepo;
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	JwtUtil jwtUtil;
	@Autowired
	UserRepo userRepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	AuthenticationManager authenticationManager;
	@Override
	public String authentication(User user) {
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
		}
		catch(AuthenticationException ex) {
			throw new InvalidCredentialsException(ex.toString());
		}
		String jwt = jwtUtil.generateToken(user.getUserName());
		return jwt;
	}

	@Override
	public boolean logout(String authToken) {
		String token = authToken.substring(7);
		BlacklistedTokensDocument blackListedToken = userMongoRepo.findByToken(token);
		if (blackListedToken == null) {
			BlacklistedTokensDocument newBlackListedToken = new BlacklistedTokensDocument(token, LocalDate.now());
			userMongoRepo.save(newBlackListedToken);
			return true;
		}

		throw new InvalidAuthTokenException();
	}

	@Override
	public User register(User user) {
		UserEntity userEntity = convertStockDTOIntoEntity(user);
		userEntity = userRepo.save(userEntity);
		return convertEntityIntoDTO(userEntity);
	}

	@Override
	public User userInfo(String authToken) {
		authToken = authToken.substring(7);
		String userName = jwtUtil.extractUsername(authToken);
		List<UserEntity> userEntityList = userRepo.findByUserName(userName);
		return  convertEntityListIntoDTOList(userEntityList);
	}

	@Override
	public boolean validateUser(String authToken) {
		try {
			authToken = authToken.substring(7);
			String userName = jwtUtil.extractUsername(authToken);
			UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
			return jwtUtil.validateToken(authToken, userDetails);
		}
		catch (Exception ex) {
			return false;
		}
		
	}
	
	private UserEntity convertStockDTOIntoEntity(User user) {
		//return 
		//new StockEntity(stock.getId(),stock.getName(),stock.getMarket(),stock.getPrice());
		TypeMap<User,UserEntity> typeMap = modelMapper.typeMap(User.class, UserEntity.class);
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);
		return userEntity;
	}
	private User convertEntityIntoDTO(UserEntity userEntity) {
		TypeMap<UserEntity,User> typeMap = modelMapper.typeMap(UserEntity.class, User.class);
		User user = modelMapper.map(userEntity, User.class);
		return user;
	}
	
	private User convertEntityListIntoDTOList(List<UserEntity> userEntityList) {
		List<User> userList = new ArrayList<>();
		for (UserEntity userEntity : userEntityList ) {
			TypeMap<UserEntity, User> typeMap = modelMapper.typeMap(UserEntity.class, User.class);
			User user = modelMapper.map(userEntity, User.class);
			userList.add(user);
		}
		return userList.get(0);
		
	}
	
	private List<UserEntity> convertDTOListIntoEntityList(List<User> userList) {
		List<UserEntity> userEntityList = new ArrayList<>();
		for (User user : userList ) {
			TypeMap<User, UserEntity> typeMap = modelMapper.typeMap(User.class, UserEntity.class);
			UserEntity userEntity = modelMapper.map(user, UserEntity.class);
			userList.add(user);
		}
		return userEntityList;	
		
	}
	
}
