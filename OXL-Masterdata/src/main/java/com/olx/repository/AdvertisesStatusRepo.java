package com.olx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olx.entity.AdvertisesStatusEntity;


public interface AdvertisesStatusRepo extends JpaRepository<AdvertisesStatusEntity, Integer> {

}
