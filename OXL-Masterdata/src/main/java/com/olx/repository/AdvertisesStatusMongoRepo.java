package com.olx.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.olx.entity.AdvertisesStatusDocument;

public interface AdvertisesStatusMongoRepo extends MongoRepository<AdvertisesStatusDocument, Integer> {

}
