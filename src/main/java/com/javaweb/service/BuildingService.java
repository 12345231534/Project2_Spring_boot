package com.javaweb.service;

import java.util.List;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.entity.BuildingEntity;

public interface BuildingService {
	List<BuildingDTO> findALL(String name, Long districtID);
}
