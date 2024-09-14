package com.javaweb.repository.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.StringUtil;
import com.javaweb.utils.ConnectSQL;
import com.javaweb.utils.ConversionUtils;
import com.javaweb.utils.NumberUtil;
@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	
	public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
		Integer staffID = buildingSearchBuilder.getStaffID();
		Integer areaTo = buildingSearchBuilder.getAreaTo();
		Integer areaFrom = buildingSearchBuilder.getAreaFrom();

		if(staffID != null) {
			sql.append("INNER JOIN assignmentbuilding on b.id  = assignmentbuilding.buildingid ");
		}
		List<String> typeCode = buildingSearchBuilder.getTypeCode();
		if(typeCode != null && typeCode.size() != 0) {
			sql.append("INNER JOIN buildingrenttype  on buildingrenttype.buildingid = b.id ");
			sql.append("INNER JOIN renttype  on renttype.id = buildingrenttype.renttypeid ");
		}
		
		if(areaTo != null ||  areaFrom != null) {
			sql.append("INNER JOIN rentarea as r on r.buildingid = b.id " );
		}
	}
	public static void normalQuery(BuildingSearchBuilder buildingSearchBuilder,  StringBuilder where) {
		
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field item : fields) {
				item.setAccessible(true);
				String fieldName = item.getName();
				if(!fieldName.equals("staffID") && fieldName.startsWith("area") 
						&& !fieldName.startsWith("price") && !fieldName.equals("typeCode")) {
					Object value = item.get(buildingSearchBuilder);
					if(value != null) {
						if(item.getType().getName().equals("java.lang.Integer")) {
							where.append(" AND b." + fieldName +" = "  + value +" ");
						}
						else if(item.getType().getName().equals("java.lang.String")) {
							where.append(" AND b." + fieldName + " LIKE '%" + value + "%' ");
						}
					}
				}
				
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public static void specialQuery(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		Integer staffID = buildingSearchBuilder.getStaffID();
		if (staffID != null) {
			where.append(" AND assignmentbuilding.staffid = " + staffID +" ");
		}
		Integer areaTo = buildingSearchBuilder.getAreaTo();
		Integer areaFrom = buildingSearchBuilder.getAreaFrom();
		if(areaTo != null &&  areaFrom != null) {
			where.append(" AND r.value >= " +areaFrom  + "AND r.value  <= " + areaTo +" ");
		}
		else if (areaFrom != null) {
			where.append(" AND r.value >= " +areaFrom +" ") ;
		}
		else if (areaTo != null) {
			where.append(" AND r.value <= " +areaTo +" ") ;
		}
		Integer priceFrom = buildingSearchBuilder.getPriceFrom();
		Integer priceTo = buildingSearchBuilder.getPriceTo();
		if(priceFrom != null && priceTo != null) {
			where.append(" AND b.rentprice >= " +priceFrom  + "AND b.rentprice  <= " + priceTo +" ");
		}
		else if (priceFrom != null) {
			where.append(" AND b.rentprice >= " +priceFrom +" ") ;
		}
		else if (priceTo != null) {
			where.append(" AND b.rentprice <= " +priceTo +" ") ;
		}
		
		//java 8
		List<String> typeCode = buildingSearchBuilder.getTypeCode();
		if(typeCode != null && typeCode.size() != 0) {
			where.append(" AND (");
			String sql = typeCode.stream().map(it -> "renttype.code like '%"+it+"%' ").collect(Collectors.joining(" or "));
			where.append(sql);
			where.append(" ) ");
		}
	}
	
	
	@Override
	public List<BuildingEntity> findALL(BuildingSearchBuilder buildingSearchBuilder) {
		StringBuilder sql = new StringBuilder("SELECT b.id, b.name,b.street, b.ward, b.street, b.districtid, b.structure, "
				+ "b.numberofbasement, b.floorarea, b.rentprice, b.level, b.servicefee, b.managername, b.managerphonenumber, b.brokeragefee FROM building b  ");
			
		joinTable(buildingSearchBuilder, sql);
		sql.append("WHERE 1=1 ");
		normalQuery(buildingSearchBuilder, sql);
		specialQuery(buildingSearchBuilder, sql);
		sql.append("GROUP BY b.id");
		System.out.println(sql);
		
		List<BuildingEntity> result = new ArrayList<>();
		try(Connection con =  ConnectSQL.connect();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString());){
			while(rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setId(rs.getInt("id"));
				building.setName(rs.getString("name"));
				building.setNumberOfBasement(rs.getInt("numberofbasement"));
				building.setDistrictID(rs.getInt("districtid"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
				building.setRentPrice(rs.getInt("rentprice"));
				building.setServiceFee(rs.getInt("servicefee"));
				building.setManagerName(rs.getString("managername"));
				building.setManagerPhoneNumber(rs.getString("managerphonenumber"));
				building.setFloorArea(rs.getInt("floorarea"));
				building.setBrokerageFee(rs.getInt("brokeragefee"));
				result.add(building);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
}
