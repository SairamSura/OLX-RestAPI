package com.olx.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.olx.dto.Advertises;


@Service
public interface AdvertisesService {
	public Advertises postAdvertises(String authToken,Advertises advertises);
	public Advertises updateAdvertises(int advertisesId , String authToken, Advertises advertises );
	public List<Advertises> getAdvertises(String authToken);
	public Advertises getPostedAdvertises(int advertiseId,String authToken);
	public boolean deleteAdvertisesPostedByUser(int advertiseId, String authToken);
	public Map<String,List<Advertises>> getAdvertisesBySearch(String searchText);
	public Advertises getAdvertiseById(int advertiseId,String authToken);
	public List<Advertises> searchAdvertisesByFilterCriteria(String searchText, Integer categoryId, String postedBy,
			String dateCondition, LocalDate onDate, LocalDate fromDate, LocalDate toDate, String sortedBy,
			int startIndex, int records);
}
