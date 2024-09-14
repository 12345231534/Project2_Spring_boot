package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.repository.entity.RentTypeEntity;
import com.javaweb.utils.ConnectSQL;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository{

	@Override
	public List<RentAreaEntity> findByID(Integer id) {
		String sql = "select *  from rentarea as r where r.buildingid = "+ id;
				
				
		List<RentAreaEntity> result =  new ArrayList<RentAreaEntity>();

		try(Connection con =  ConnectSQL.connect();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);){
			while(rs.next()) {
				RentAreaEntity rentAreaEntity = new RentAreaEntity();
				rentAreaEntity.setValue(rs.getString("value"));
				result.add(rentAreaEntity);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
}
