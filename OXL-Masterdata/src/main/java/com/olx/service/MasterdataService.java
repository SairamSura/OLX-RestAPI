package com.olx.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.olx.dto.Advertises;
import com.olx.dto.Categories;


@Service
public interface MasterdataService {
	public Map<String,List<Categories>> getCategories();
	public Map<String,List<Advertises>> getStatus();
	public String getCategoryName(int categoryId);
	public String getStatusName(int statusId);
}
