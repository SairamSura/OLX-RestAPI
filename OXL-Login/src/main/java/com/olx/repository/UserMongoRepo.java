package com.olx.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.olx.entity.UserDocument;

public interface UserMongoRepo extends MongoRepository<UserDocument, Integer> {

}
