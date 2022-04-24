package com.olx.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.olx.dto.Advertises;
import com.olx.repository.AdvertisesMongoRepo;

public class AdvertisesServiceMongoImp implements AdvertisesService {
	
	@Autowired
	AdvertisesMongoRepo advertisesMongoRepo;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public Advertises postAdvertises(String authToken, Advertises advertises) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Advertises updateAdvertises(int advertisesId, String authToken, Advertises advertises) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Advertises> getAdvertises(String authToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Advertises getPostedAdvertises(int advertiseId, String authToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAdvertisesPostedByUser(int advertiseId, String authToken) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, List<Advertises>> getAdvertisesBySearch(String SearchText) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Advertises getAdvertiseById(int advertiseId, String authToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Advertises> searchAdvertisesByFilterCriteria(String searchText, Integer categoryId, String postedBy,
			String dateCondition, LocalDate onDate, LocalDate fromDate, LocalDate toDate, String sortedBy,
			int startIndex, int records) {
		// TODO Auto-generated method stub
		return null;
	}

}
