package com.javaweb.repository.impl;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
@Primary
public class BuildingRepositoryImpl implements BuildingRepository {

	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List<BuildingEntity> findALL(BuildingSearchBuilder buildingSearchBuilder) {
		// TODO Auto-generated method stub
		//JPQL
//		String sql = "FROM BuildingEntity";
//		Query query = entityManager.createQuery(sql,BuildingEntity.class);
//		return query.getResultList();
		String sql = "SELECT * FROM building";
		Query query = entityManager.createNativeQuery(sql,BuildingEntity.class);
		return query.getResultList();
		

	}
	
}
