package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.RentTypeRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.repository.entity.RentTypeEntity;

@Component
public class BuildingDTOConverter {
	@Autowired
	private DistrictRepository districtRepository;
	@Autowired
	private RentTypeRepository rentTypeRepository;
	@Autowired
	private RentAreaRepository rentAreaRepository;
	
	public BuildingDTO toBuildingDTO (BuildingEntity item) {
		BuildingDTO building = new BuildingDTO();
		building.setName(item.getName());
		DistrictEntity districtEntity = districtRepository.findByID(item.getDistrictID());
		building.setAddress(item.getStreet()+"," + item.getWard() +", " + districtEntity.getName());
		building.setNumberOfBasement(item.getNumberOfBasement());
		building.setRentPrice(item.getRentPrice());
		building.setServiceFee(item.getServiceFee());
		building.setManagerName(item.getManagerName());
		building.setManagerPhoneNumber(item.getManagerPhoneNumber());
		building.setFloorArea(item.getFloorArea());
		building.setBrokerageFee(item.getBrokerageFee());
		RentTypeEntity rentTypeEntity = rentTypeRepository.findByID(item.getId());
		building.setRenttype(rentTypeEntity.getName());
		List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findByID(item.getId());
		String areaResult = rentAreaEntities.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
		building.setRentArea(areaResult); 

		return building;
	}
}
