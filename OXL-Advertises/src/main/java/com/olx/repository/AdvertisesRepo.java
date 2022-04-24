package com.olx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.olx.entity.AdvertisesEntity;


public interface AdvertisesRepo extends JpaRepository<AdvertisesEntity, Integer> {
	List<AdvertisesEntity> findByUsername(String username);
}
