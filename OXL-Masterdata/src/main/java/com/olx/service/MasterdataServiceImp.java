package com.olx.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.olx.dto.Advertises;
import com.olx.dto.Categories;
import com.olx.entity.AdvertisesStatusEntity;
import com.olx.entity.CategoryEntity;
import com.olx.repository.AdvertisesStatusRepo;
import com.olx.repository.CategoryRepo;



@Service
@Primary
public class MasterdataServiceImp implements MasterdataService {
	@Autowired
	AdvertisesStatusRepo advertisesRepo;
	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public Map<String,List<Categories>> getCategories() {
		List<CategoryEntity> categoryEntityList = categoryRepo.findAll();
		List<Categories> categoryDtoList = new ArrayList<Categories>();
		for(CategoryEntity categoryEntity: categoryEntityList) {
			Categories category = convertCategoryEntityIntoDTO(categoryEntity);
			categoryDtoList.add(category);
			
		}
		Map<String,List<Categories>> categoryList = new HashMap<>();
		categoryList.put("Categories", categoryDtoList);
		return categoryList;
		
		
	}

	@Override
	public Map<String,List<Advertises>> getStatus() {
		List<AdvertisesStatusEntity> avdvertisesEntityList = advertisesRepo.findAll();
		List<Advertises> advertisesDtoList = new ArrayList<Advertises>();
		for(AdvertisesStatusEntity advertisesEntity: avdvertisesEntityList) {
			Advertises advertises = convertAdvertisesEntityIntoDTO(advertisesEntity);
			advertisesDtoList.add(advertises);
			
		}
		Map<String,List<Advertises>> advertisesList = new HashMap<>();
		advertisesList.put("StatusList", advertisesDtoList);
		return advertisesList;
		
		
	}
	
	private AdvertisesStatusEntity convertAdvertisesDTOIntoEntity(Advertises advertises) {
		//return 
		//new StockEntity(stock.getId(),stock.getName(),stock.getMarket(),stock.getPrice());
		TypeMap<Advertises,AdvertisesStatusEntity> typeMap = modelMapper.typeMap(Advertises.class, AdvertisesStatusEntity.class);
		AdvertisesStatusEntity advertisesEntity = modelMapper.map(advertises, AdvertisesStatusEntity.class);
		return advertisesEntity;
	}
	private Advertises convertAdvertisesEntityIntoDTO(AdvertisesStatusEntity advertisesEntity) {
		TypeMap<AdvertisesStatusEntity,Advertises> typeMap = modelMapper.typeMap(AdvertisesStatusEntity.class, Advertises.class);
		Advertises advertises = modelMapper.map(advertisesEntity, Advertises.class);
		return advertises;
	}
	private CategoryEntity convertCategoryDTOIntoEntity(Categories categories) {
		//return 
		//new StockEntity(stock.getId(),stock.getName(),stock.getMarket(),stock.getPrice());
		TypeMap<Categories,CategoryEntity> typeMap = modelMapper.typeMap(Categories.class, CategoryEntity.class);
		CategoryEntity categoryEntity = modelMapper.map(categories, CategoryEntity.class);
		return categoryEntity;
	}
	private Categories convertCategoryEntityIntoDTO(CategoryEntity categoryEntity) {
		TypeMap<AdvertisesStatusEntity,Advertises> typeMap = modelMapper.typeMap(AdvertisesStatusEntity.class, Advertises.class);
		Categories category = modelMapper.map(categoryEntity, Categories.class);
		return category;
	}

	@Override
	public String getCategoryName(int categoryId) {
		Optional<CategoryEntity> opCategoryEntity = categoryRepo.findById(categoryId);
		if(opCategoryEntity.isPresent()) {
			CategoryEntity category = opCategoryEntity.get();
			return category.getCategory();
		}
		return null;
	}

	@Override
	public String getStatusName(int statusId) {
		Optional<AdvertisesStatusEntity> opStatusEntity = advertisesRepo.findById(statusId);
		if(opStatusEntity.isPresent()) {
			AdvertisesStatusEntity status = opStatusEntity.get();
			return status.getStatus();
		}
		return null;
	}

}
