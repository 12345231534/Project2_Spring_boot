package com.javaweb.repository;

import com.javaweb.repository.entity.RentTypeEntity;

public interface RentTypeRepository {
	RentTypeEntity findByID (Integer id);
}
