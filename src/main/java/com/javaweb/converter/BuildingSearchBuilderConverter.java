package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.utils.MapUtil;
@Component
public class BuildingSearchBuilderConverter {
	public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> params, List<String> typeCode) {
		BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
															.setName(MapUtil.getObject(params, "name", String.class))
															.setAreaFrom(MapUtil.getObject(params, "areaFrom", Integer.class))
															.setAreaTo(MapUtil.getObject(params, "areaTo", Integer.class))
															.setPriceFrom(MapUtil.getObject(params, "priceFrom", Integer.class))
															.setPriceTo(MapUtil.getObject(params, "priceTo", Integer.class))
															.setFloorArea(MapUtil.getObject(params, "floorArea", Integer.class))
															.setBrokerageFee(MapUtil.getObject(params, "brokerageFee", Integer.class))
															.setManagerName(MapUtil.getObject(params, "manageName", String.class))
															.setAddress(MapUtil.getObject(params, "address", String.class))
															.setStaffID(MapUtil.getObject(params, "staffID", Integer.class))
															.setTypeCode(typeCode)
															.setServiceFee(MapUtil.getObject(params, "serviceFee", Integer.class))															
															.build();	
		return buildingSearchBuilder;																
	}
}
