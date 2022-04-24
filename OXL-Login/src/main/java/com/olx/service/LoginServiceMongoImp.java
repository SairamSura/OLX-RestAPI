package com.olx.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.dto.User;
import com.olx.entity.BlacklistedTokensDocument;
import com.olx.entity.UserDocument;
import com.olx.entity.UserEntity;
import com.olx.repository.UserMongoBlackListedRepo;
import com.olx.repository.UserMongoRepo;


@Service(value="MONGO_SERVICE")
public class LoginServiceMongoImp implements LoginService{
	@Autowired
	UserMongoRepo userMongoRepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	UserMongoBlackListedRepo userMongoBlackListedRepo;
	@Override
	public String authentication(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean logout(String authToken) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User register(User user) {
		UserDocument userDoc = convertStockDTOIntoEntity(user);
		userDoc = userMongoRepo.save(userDoc);
		return convertEntityIntoDTO(userDoc);
	}
	
	@Override
	public User userInfo(String authToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validateUser(String authToken) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private UserDocument convertStockDTOIntoEntity(User user) {
		//return 
		//new StockEntity(stock.getId(),stock.getName(),stock.getMarket(),stock.getPrice());
		TypeMap<User,UserDocument> typeMap = modelMapper.typeMap(User.class, UserDocument.class);
		UserDocument userDocument = modelMapper.map(user, UserDocument.class);
		return userDocument;
	}
	private User convertEntityIntoDTO(UserDocument userDocument) {
		TypeMap<UserDocument,User> typeMap = modelMapper.typeMap(UserDocument.class, User.class);
		User user = modelMapper.map(userDocument, User.class);
		return user;
	}
}
