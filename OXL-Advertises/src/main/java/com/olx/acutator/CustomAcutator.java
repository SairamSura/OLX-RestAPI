package com.olx.acutator;

import java.util.HashMap;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id="advstats")
public class CustomAcutator {
	private Map<String, Integer> statusAdvertises = new HashMap<>();
	@PostConstruct
	public void init() {
		statusAdvertises.put("total", 50);
		statusAdvertises.put("opened", 20);
		statusAdvertises.put("posted By Last Month", 20);
	}	
	@ReadOperation
	public Map<String, Integer> advertises() {
		return this.statusAdvertises;
	}
	
	
}