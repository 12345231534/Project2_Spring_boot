package com.javaweb.converter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.repository.entity.RentTypeEntity;




@Component
public class BuildingDTOConverter {



	public BuildingDTO toBuildingDTO (BuildingEntity item) {
		BuildingDTO building = new BuildingDTO();
		building.setName(item.getName());

		building.setAddress(item.getStreet()+"," + item.getWard() +", " + item.getDistrict().getName());
		building.setNumberOfBasement(item.getNumberOfBasement());
		building.setRentPrice(item.getRentPrice());
		building.setManagerName(item.getManagerName());
		building.setManagerPhoneNumber(item.getManagerPhoneNumber());
		building.setFloorArea(item.getFloorArea());

//		RentTypeEntity rentTypeEntity = rentTypeRepository.findByID(item.getId());
//		building.setRenttype(rentTypeEntity.getName());
		List<RentTypeEntity> rentTypeEntities = item.getRenttype();
		String renttype= rentTypeEntities.stream().map(it -> it.getName().toString()).collect(Collectors.joining(", "));
		building.setRenttype(renttype);
		List<RentAreaEntity> rentAreaEntities = item.getRentAreaEntities();
		String areaResult = rentAreaEntities.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
		building.setRentArea(areaResult); 

		return building;
	}
}
