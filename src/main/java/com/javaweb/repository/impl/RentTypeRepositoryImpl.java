package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.RentTypeRepository;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentTypeEntity;
import com.javaweb.utils.ConnectSQL;
@Repository
public class RentTypeRepositoryImpl  implements RentTypeRepository{

	
	@Override
	public RentTypeEntity findByID(Integer id) {
		String sql = "select group_concat(r.name) as renttype "
				+ "from renttype as r "
				+ "inner join buildingrenttype as b on b.renttypeid = r.id "
				+ "where b.buildingid =  "+  id;
		RentTypeEntity rentTypeEntity =  new RentTypeEntity();
		System.out.println(sql);
		try(Connection con =  ConnectSQL.connect();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);){
			while(rs.next()) {
				rentTypeEntity.setName(rs.getString("renttype"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return rentTypeEntity;
	}
	
}
