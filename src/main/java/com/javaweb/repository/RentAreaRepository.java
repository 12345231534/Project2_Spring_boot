package com.javaweb.repository;

import java.util.List;

import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.repository.entity.RentTypeEntity;

public interface RentAreaRepository {
	List<RentAreaEntity> findByID (Integer id);
}
