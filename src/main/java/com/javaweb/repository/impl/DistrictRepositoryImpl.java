package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.utils.ConnectSQL;
@Repository
public class DistrictRepositoryImpl implements DistrictRepository {
	@Override
	public DistrictEntity findByID(Integer id) {
		String sql = "SELECT name from district where id = " + id;
		DistrictEntity districtEntity =  new DistrictEntity();
		try(Connection con =  ConnectSQL.connect();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);){
			while(rs.next()) {
				districtEntity.setName(rs.getString("name"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return districtEntity;
	}
	
}
