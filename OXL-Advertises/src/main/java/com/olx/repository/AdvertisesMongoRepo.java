package com.olx.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.olx.entity.AdvertisesDocument;

public interface AdvertisesMongoRepo extends MongoRepository<AdvertisesDocument, Integer>{

}
