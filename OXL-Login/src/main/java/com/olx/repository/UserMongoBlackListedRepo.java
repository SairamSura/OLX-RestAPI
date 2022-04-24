package com.olx.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.olx.entity.BlacklistedTokensDocument;


public interface UserMongoBlackListedRepo extends MongoRepository<BlacklistedTokensDocument, Integer>{
	public BlacklistedTokensDocument findByToken(String token);
}
