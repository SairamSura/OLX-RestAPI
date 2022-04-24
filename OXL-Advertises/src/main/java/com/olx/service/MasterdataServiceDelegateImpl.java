package com.olx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MasterdataServiceDelegateImpl implements MasterdataServiceDelegate {
	
	@Autowired
	RestTemplate restTemplate;
	@Override
	public String getCategory(int categoryId) {
		HttpEntity entity = new HttpEntity<>(categoryId);
		ResponseEntity<String> response = 
				// this.restTemplate.getForEntity("http://localhost:9002/olx/masterdata/category/{id}", String.class,categoryId)
				//this.restTemplate.getForEntity("http://MASTERDATA-SERVICE/olx/masterdata/category/{id}", String.class,categoryId);
				this.restTemplate.getForEntity("http://API-GATEWAY/olx/masterdata/category/{id}", String.class,categoryId);
		return response.getBody();
	}

	@Override
	public String getStatus(int statusId) {
		HttpEntity entity = new HttpEntity<>(statusId);
		ResponseEntity<String> response = 
				//this.restTemplate.getForEntity("http://localhost:9002/olx/masterdata/category/{id}", String.class,categoryId)
				//this.restTemplate.getForEntity("http://MASTERDATA-SERVICE/olx/masterdata/status/{id}", String.class,statusId);
				this.restTemplate.getForEntity("http://API-GATEWAY/olx/masterdata/status/{id}", String.class,statusId);
		return response.getBody();
	}

}
