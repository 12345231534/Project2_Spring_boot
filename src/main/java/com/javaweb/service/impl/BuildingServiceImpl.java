package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.RentTypeRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.repository.entity.RentTypeEntity;
import com.javaweb.service.BuildingService;
import com.javaweb.utils.ConversionUtils;

@Service
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	private BuildingRepository buildingRepository;
	@Autowired
	private BuildingDTOConverter buildingDTOConverter;
	
	@Override
	public List<BuildingDTO> findALL(Map<String, Object> params, List<String> typeCode) {

		List<BuildingEntity> buildingEntities = buildingRepository.findALL(params, typeCode); 
		List<BuildingDTO> result = new ArrayList<>();
		for (BuildingEntity item: buildingEntities) {
			BuildingDTO building = buildingDTOConverter.toBuildingDTO(item);
			
			result.add(building);
		}
//		
		return result;
	}

}
