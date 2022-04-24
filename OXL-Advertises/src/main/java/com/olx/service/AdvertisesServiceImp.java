package com.olx.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.dto.Advertises;

import com.olx.entity.AdvertisesEntity;
import com.olx.exception.InvalidAdvertisesException;
import com.olx.exception.InvalidAuthTokenException;
import com.olx.exception.InvalidCategoryIdException;
import com.olx.exception.InvalidStatusId;
import com.olx.repository.AdvertisesRepo;
import com.olx.security.JwtUtil;



@Service
public class AdvertisesServiceImp implements AdvertisesService{
	@Autowired
	AdvertisesRepo advertisesRepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	EntityManager entityManager; //JPA
	@Autowired
	LoginServiceDelegate loginServiceDelegate;
	@Autowired
	MasterdataServiceDelegate masterdataServiceDelegate;
	@Autowired
	JwtUtil jwtUtil;

	@Override
	public Advertises postAdvertises(String authToken, Advertises advertises) {
		
		if(loginServiceDelegate.isTokenValid(authToken)) {
			
			int categoryId = advertises.getCategoryId();
			int statusId = advertises.getStatusId();
			String category = masterdataServiceDelegate.getCategory(categoryId);
			String status  = masterdataServiceDelegate.getStatus(statusId);
			AdvertisesEntity advertisesEntity = convertAdvertisesDTOIntoEntity(advertises);
			authToken = authToken.substring(7);
			String userName = jwtUtil.extractUsername(authToken);
			advertisesEntity.setUsername(userName);
			advertisesEntity.setStatus(status);
			advertisesEntity.setCategory(category);
			advertisesEntity.setCreatedDate(LocalDate.now());
			advertisesEntity.setModifiedDate(LocalDate.now());			
			advertisesRepo.save(advertisesEntity);
			return convertEntityIntoDTO(advertisesEntity);
			
		}
		
			throw new InvalidAuthTokenException();
		
		
	}

	@Override
	public Advertises updateAdvertises(int advertisesId, String authToken, Advertises advertises) {
		if (loginServiceDelegate.isTokenValid(authToken)) {
			Optional<AdvertisesEntity> advertiseEntityOptional = advertisesRepo.findById(advertisesId);
			if (advertiseEntityOptional.isPresent()) {
				AdvertisesEntity advertiseEntity = advertiseEntityOptional.get();
				Advertises updatedAdvertise = convertEntityIntoDTO(advertiseEntity);
				int categoryId = advertises.getCategoryId();
				int statusId = advertises.getStatusId();
				String category = masterdataServiceDelegate.getCategory(categoryId);
				String status  = masterdataServiceDelegate.getStatus(statusId);
				updatedAdvertise.setTitle(advertises.getTitle());
				updatedAdvertise.setCategory(category);
				updatedAdvertise.setDescription(advertises.getDescription());
				updatedAdvertise.setModifiedDate(LocalDate.now());
				updatedAdvertise.setPrice(advertises.getPrice());
				updatedAdvertise.setStatus(status);
				return updatedAdvertise;
			} else
				throw new InvalidCategoryIdException();

		} else {
			throw new InvalidAuthTokenException();
		}
	}

	@Override
	public List<Advertises> getAdvertises(String authToken) {
		if(loginServiceDelegate.isTokenValid(authToken)) {
			authToken = authToken.substring(7);
			String username = jwtUtil.extractUsername(authToken);
			List<AdvertisesEntity> advertisesEntityList = advertisesRepo.findByUsername(username);
			List<Advertises> advertisesDtoList = new ArrayList<Advertises>();
			for(AdvertisesEntity advertisesEntity:advertisesEntityList) {
				Advertises advertises = convertEntityIntoDTO(advertisesEntity);
				advertisesDtoList.add(advertises);
				
			}
			return advertisesDtoList;
			
		}
		
			throw new InvalidAuthTokenException();
	}

	@Override
	public Advertises getPostedAdvertises(int advertiseId, String authToken) {
		if(loginServiceDelegate.isTokenValid(authToken)) {
			authToken = authToken.substring(7);
			Optional<AdvertisesEntity> optionalAdvertisesEntity = advertisesRepo.findById(advertiseId);
			if(optionalAdvertisesEntity.isPresent()) {
				AdvertisesEntity advertisesEntity = optionalAdvertisesEntity.get();
				Advertises advertises = convertEntityIntoDTO(advertisesEntity);
				return advertises;
		}
			else {
				throw new InvalidAdvertisesException();
			}
		}
			else {
				throw new InvalidAuthTokenException();
			}
			
	}

	@Override
	public boolean deleteAdvertisesPostedByUser(int advertiseId, String authToken) {
		if(loginServiceDelegate.isTokenValid(authToken)) {
			authToken = authToken.substring(7);
			Optional<AdvertisesEntity> optionalAdvertisesEntity = advertisesRepo.findById(advertiseId);
			if(optionalAdvertisesEntity.isPresent()) {
				advertisesRepo.deleteById(advertiseId);
				return true;
		}
		}
				throw new InvalidAuthTokenException();
	}

	
	@Override
	public Map<String, List<Advertises>> getAdvertisesBySearch(String searchText) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AdvertisesEntity> advertiseQuery = criteriaBuilder.createQuery(AdvertisesEntity.class);
		Root<AdvertisesEntity> advertiseRoot = advertiseQuery.from(AdvertisesEntity.class);

		Predicate predicateTitle = criteriaBuilder.and();
		Predicate predicateDescription = criteriaBuilder.and();
		Predicate predicateSearchText = criteriaBuilder.and();
		if (searchText != null && !searchText.equalsIgnoreCase("")) {
			predicateTitle = criteriaBuilder.like(advertiseRoot.get("title"), "%" + searchText + "%");
			predicateDescription = criteriaBuilder.like(advertiseRoot.get("description"), "%" + searchText + "%");
			predicateSearchText = criteriaBuilder.or(predicateTitle, predicateDescription);
			advertiseQuery.where(predicateSearchText);

			TypedQuery<AdvertisesEntity> typedQuery = entityManager.createQuery(advertiseQuery);

			List<AdvertisesEntity> advertiseEntityList = typedQuery.getResultList();
			List<Advertises> advertiseDtoList = convertEntityListIntoDTOList(advertiseEntityList);
			Map<String,List<Advertises>> advertiseEntity = new HashMap<>();
			advertiseEntity.put("Advertises", advertiseDtoList);
			return advertiseEntity;

		} else
			throw new InvalidStatusId();
	}

	@Override
	public Advertises getAdvertiseById(int advertiseId, String authToken) {
		if(loginServiceDelegate.isTokenValid(authToken)) {
			authToken = authToken.substring(7);
			Optional<AdvertisesEntity> optionalAdvertisesEntity = advertisesRepo.findById(advertiseId);
			if(optionalAdvertisesEntity.isPresent()) {
				AdvertisesEntity advertisesEntity = optionalAdvertisesEntity.get();
				Advertises advertises = convertEntityIntoDTO(advertisesEntity);
				return advertises;
		}
			else {
				throw new InvalidAdvertisesException();
			}
		}
			else {
				throw new InvalidAuthTokenException();
			}
	}

	@Override
	public List<Advertises> searchAdvertisesByFilterCriteria(String searchText, Integer categoryId, String postedBy,
			String dateCondition, LocalDate onDate, LocalDate fromDate, LocalDate toDate, String sortedBy,
			int startIndex, int records) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AdvertisesEntity> criteriaQuery = criteriaBuilder.createQuery(AdvertisesEntity.class);
		Root<AdvertisesEntity> root = criteriaQuery.from(AdvertisesEntity.class);

		Predicate predicateTitle = criteriaBuilder.and();
		Predicate predicateDescription = criteriaBuilder.and();
		Predicate predicateSearchText = criteriaBuilder.and();
		Predicate predicateCategory = criteriaBuilder.and();
		Predicate predicateDateConditionEquals = criteriaBuilder.and();
		Predicate predicateDateConditionGreateThan = criteriaBuilder.and();
		Predicate predicateDateConditionLessThan = criteriaBuilder.and();
		Predicate predicateDateConditionBetweenFromDate = criteriaBuilder.and();
		Predicate predicatePostedBy = criteriaBuilder.and();
		Predicate predicateDateCondition = criteriaBuilder.and();
		Predicate predicateOrderBy = criteriaBuilder.and();
		Predicate predicateFinal = criteriaBuilder.and();

		if (searchText != null && !"".equalsIgnoreCase(searchText)) {
			predicateTitle = criteriaBuilder.like(root.get("title"), "%" + searchText + "%");
			predicateDescription = criteriaBuilder.like(root.get("description"), "%" + searchText + "%");
			predicateSearchText = criteriaBuilder.or(predicateTitle, predicateDescription);
		}

		if (postedBy != null && !"".equalsIgnoreCase(postedBy)) {
			predicatePostedBy = criteriaBuilder.equal(root.get("username"), postedBy);
		}

		if (dateCondition != null && dateCondition.contains("equal")) {
			predicateDateConditionEquals = criteriaBuilder.equal(root.get("createdDate"), onDate);
		}

		if (dateCondition != null && dateCondition.contains("greatethan")) {
			predicateDateConditionGreateThan = criteriaBuilder.greaterThan(root.get("createdDate"), fromDate);
		}

		if (dateCondition != null && dateCondition.contains("lessthan")) {
			predicateDateConditionLessThan = criteriaBuilder.greaterThan(root.get("createdDate"), onDate);
		}

		if (dateCondition != null && dateCondition.contains("between")) {
			predicateDateConditionBetweenFromDate = criteriaBuilder.between(root.get("createdDate"), fromDate, toDate);
		}

		predicateDateCondition = criteriaBuilder.and(predicateDateConditionEquals, predicateDateConditionGreateThan,
				predicateDateConditionLessThan, predicateDateConditionBetweenFromDate);

		if(categoryId != null) {
			predicateCategory = criteriaBuilder.equal(root.get("category"), categoryId);
		}

		predicateFinal = criteriaBuilder.and(predicateSearchText, predicateCategory, predicateDateCondition,
				predicatePostedBy);
		criteriaQuery.where(predicateFinal);
		if (sortedBy != null && !sortedBy.equalsIgnoreCase("")) {
			if (sortedBy == "title") {
				criteriaQuery = criteriaQuery.orderBy(criteriaBuilder.asc(root.get("title")));
			} else {
				criteriaQuery = criteriaQuery.orderBy(criteriaBuilder.asc(root.get("price")));
			}

		}
		TypedQuery<AdvertisesEntity> typedQuery = entityManager.createQuery(criteriaQuery);

		typedQuery.setFirstResult(startIndex);
		typedQuery.setMaxResults(records);
		List<AdvertisesEntity> advertiseEntityList = typedQuery.getResultList();
		return convertEntityListIntoDTOList(advertiseEntityList);
	}
	

	
	
	private AdvertisesEntity convertAdvertisesDTOIntoEntity(Advertises advertises) {
		//return 
		//new StockEntity(stock.getId(),stock.getName(),stock.getMarket(),stock.getPrice());
		TypeMap<Advertises,AdvertisesEntity> typeMap = modelMapper.typeMap(Advertises.class, AdvertisesEntity.class);
		AdvertisesEntity advertisesEntity = modelMapper.map(advertises, AdvertisesEntity.class);
		return advertisesEntity;
	}
	private Advertises convertEntityIntoDTO(AdvertisesEntity advertisesEntity) {
		TypeMap<AdvertisesEntity,Advertises> typeMap = modelMapper.typeMap(AdvertisesEntity.class, Advertises.class);
		Advertises advertises = modelMapper.map(advertisesEntity, Advertises.class);
		return advertises;
	}

	private List<Advertises> convertEntityListIntoDTOList(List<AdvertisesEntity> advertiseEntityList) {
		List<Advertises> advertisesList = new ArrayList<>();
		for (AdvertisesEntity advertiseEntity : advertiseEntityList) {
			TypeMap<AdvertisesEntity, Advertises> typeMap = modelMapper.typeMap(AdvertisesEntity.class, Advertises.class);
			Advertises advertise = modelMapper.map(advertiseEntity, Advertises.class);
			advertisesList.add(advertise);
		}

		return advertisesList;
	}
	

}
