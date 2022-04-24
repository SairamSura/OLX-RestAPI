package com.olx.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.dto.Advertises;
import com.olx.dto.Categories;
import com.olx.entity.AdvertisesStatusDocument;
import com.olx.entity.AdvertisesStatusEntity;
import com.olx.entity.CategoryDocument;

import com.olx.repository.AdvertisesStatusMongoRepo;

import com.olx.repository.CategoryMongoRepo;


@Service(value="MONGO_SERVICE")
public class MasterdataMongoServiceImp implements MasterdataService{

	@Autowired
	AdvertisesStatusMongoRepo advertisesStatusMongoRepo;
	@Autowired
	CategoryMongoRepo categoryMongoRepo;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public Map<String,List<Categories>> getCategories() {
		List<CategoryDocument> categoryDocumnetList = categoryMongoRepo.findAll();
		List<Categories> categoryDtoList = new ArrayList<Categories>();
		for(CategoryDocument categoryDoc: categoryDocumnetList) {
			Categories category = convertCategoryDocumentIntoDTO(categoryDoc);
			categoryDtoList.add(category);
			
		}
		Map<String,List<Categories>> categoryList = new HashMap<>();
		categoryList.put("Categories", categoryDtoList);
		return categoryList;
		
		
	}

	@Override
	public Map<String,List<Advertises>> getStatus() {
		List<AdvertisesStatusDocument> avdvertisesDocList = advertisesStatusMongoRepo.findAll();
		List<Advertises> advertisesDtoList = new ArrayList<Advertises>();
		for(AdvertisesStatusDocument advertisesDocument: avdvertisesDocList) {
			Advertises advertises = convertAdvertisesDocumentIntoDTO(advertisesDocument);
			advertisesDtoList.add(advertises);
			
		}
		Map<String,List<Advertises>> advertisesList = new HashMap<>();
		advertisesList.put("StatusList", advertisesDtoList);
		return advertisesList;
		
		
	}
	
	private AdvertisesStatusEntity convertAdvertisesDTOIntoDocument(Advertises advertises) {
		//return 
		//new StockEntity(stock.getId(),stock.getName(),stock.getMarket(),stock.getPrice());
		TypeMap<Advertises,AdvertisesStatusEntity> typeMap = modelMapper.typeMap(Advertises.class, AdvertisesStatusEntity.class);
		AdvertisesStatusEntity advertisesEntity = modelMapper.map(advertises, AdvertisesStatusEntity.class);
		return advertisesEntity;
	}
	private Advertises convertAdvertisesDocumentIntoDTO(AdvertisesStatusDocument advertisesDocument) {
		TypeMap<AdvertisesStatusDocument,Advertises> typeMap = modelMapper.typeMap(AdvertisesStatusDocument.class, Advertises.class);
		Advertises advertises = modelMapper.map(advertisesDocument, Advertises.class);
		return advertises;
	}
	private CategoryDocument convertCategoryDTOIntoDocument(Categories categories) {
		//return 
		//new StockEntity(stock.getId(),stock.getName(),stock.getMarket(),stock.getPrice());
		TypeMap<Categories,CategoryDocument> typeMap = modelMapper.typeMap(Categories.class, CategoryDocument.class);
		CategoryDocument categoryDocument = modelMapper.map(categories, CategoryDocument.class);
		return categoryDocument;
	}
	private Categories convertCategoryDocumentIntoDTO(CategoryDocument categoryDocument) {
		TypeMap<AdvertisesStatusDocument,Advertises> typeMap = modelMapper.typeMap(AdvertisesStatusDocument.class, Advertises.class);
		Categories category = modelMapper.map(categoryDocument, Categories.class);
		return category;
	}

	@Override
	public String getCategoryName(int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStatusName(int statusId) {
		// TODO Auto-generated method stub
		return null;
	}

}
