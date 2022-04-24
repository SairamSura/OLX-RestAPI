package com.olx.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.olx.entity.CategoryDocument;

public interface CategoryMongoRepo extends MongoRepository<CategoryDocument, Integer>{

}
