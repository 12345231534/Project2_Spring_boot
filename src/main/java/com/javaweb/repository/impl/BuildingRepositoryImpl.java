package com.javaweb.repository.impl;

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

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.StringUtil;
import com.javaweb.utils.ConnectSQL;
import com.javaweb.utils.ConversionUtils;
import com.javaweb.utils.NumberUtil;
@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	
	public static void joinTable(Map<String, Object> params, List<String> typeCode, StringBuilder sql) {
		String staffID = (String) params.get("staffID");
		String areaTo = (String) params.get("areaTo");
		String areaFrom = (String) params.get("AreaFrom");

		if(StringUtil.checkString(staffID)) {
			sql.append("INNER JOIN assignmentbuilding on b.id  = assignmentbuilding.buildingid ");
		}
		if(typeCode != null && typeCode.size() != 0) {
			sql.append("INNER JOIN buildingrenttype  on buildingrenttype.buildingid = b.id ");
			sql.append("INNER JOIN renttype  on renttype.id = buildingrenttype.renttypeid ");
		}
		
		if(StringUtil.checkString(areaTo) || StringUtil.checkString(areaFrom)) {
			sql.append("INNER JOIN rentarea as r on r.buildingid = b.id " );
		}
	}
	public static void normalQuery(Map<String, Object> params,  StringBuilder where) {
		for(Map.Entry<String, Object> it : params.entrySet()) {
			if(!it.getKey().equals("staffID") && !it.getKey().startsWith("area") 
					&& !it.getKey().startsWith("price") && !it.getKey().equals("typeCode")) {
				String value = it.getValue().toString();
				if (NumberUtil.isNumber(it.getKey())) {
					where.append(" AND b." + it.getKey() +" = "  + value +" ");
				}
				else {
					where.append(" AND b." + it.getKey() + " LIKE '%" + value + "%' ");
				}
			}
		}
	}
	
	public static void specialQuery(Map<String, Object> params, List<String> typeCode, StringBuilder where) {
		String staffID = (String) params.get("staffID");
		if (StringUtil.checkString(staffID)) {
			where.append(" AND assignmentbuilding.staffid = " + staffID +" ");
		}
		String areaTo = (String) params.get("areaTo");
		String areaFrom = (String) params.get("AreaFrom");
		if(StringUtil.checkString(areaTo) && StringUtil.checkString(areaFrom)) {
			where.append(" AND r.value >= " +areaFrom  + "AND r.value  <= " + areaTo +" ");
		}
		else if (StringUtil.checkString(areaFrom)) {
			where.append(" AND r.value >= " +areaFrom +" ") ;
		}
		else if (StringUtil.checkString(areaTo)) {
			where.append(" AND r.value <= " +areaTo +" ") ;
		}
		String priceFrom = (String) params.get("priceFrom");
		String priceTo = (String) params.get("priceTo");
		if(StringUtil.checkString(priceFrom) && StringUtil.checkString(priceTo)) {
			where.append(" AND b.rentprice >= " +priceFrom  + "AND b.rentprice  <= " + priceTo +" ");
		}
		else if (StringUtil.checkString(priceFrom)) {
			where.append(" AND b.rentprice >= " +priceFrom +" ") ;
		}
		else if (StringUtil.checkString(priceTo)) {
			where.append(" AND b.rentprice <= " +priceTo +" ") ;
		}
		
		//java 8
		if(typeCode != null && typeCode.size() != 0) {
			where.append(" AND (");
			String sql = typeCode.stream().map(it -> "renttype.code like '%"+it+"%' ").collect(Collectors.joining(" or "));
			where.append(sql);
			where.append(" ) ");
		}
	}
	
	
	@Override
	public List<BuildingEntity> findALL(Map<String, Object> params, List<String> typeCode) {
		StringBuilder sql = new StringBuilder("SELECT b.id, b.name,b.street, b.ward, b.street, b.districtid, b.structure, "
				+ "b.numberofbasement, b.floorarea, b.rentprice, b.level, b.servicefee, b.managername, b.managerphonenumber, b.brokeragefee FROM building b  ");
			
		joinTable(params, typeCode, sql);
		sql.append("WHERE 1=1 ");
		normalQuery(params, sql);
		specialQuery(params, typeCode, sql);
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
