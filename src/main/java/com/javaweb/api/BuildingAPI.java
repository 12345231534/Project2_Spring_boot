package com.javaweb.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.customexception.FieldRequiredException;
import com.javaweb.model.BuildingDTO;
import com.javaweb.model.ErrorResponseDTO;
import com.javaweb.service.BuildingService;
import com.mysql.cj.protocol.Resultset;
@RestController

public class BuildingAPI {
	@Autowired
	private BuildingService buildingService;
	@GetMapping(value = "/api/buildings")
	public List<BuildingDTO> getBuilding(@RequestParam(name = "name", required = false  ) String name,
											@RequestParam(name = "districtID", required = false) Long districtID ,
											@RequestParam(name = "typeCode", required = false) List<String> typeCode){
		List<BuildingDTO> result = buildingService.findALL(name, districtID);
		return result;
	}
	
	

	@GetMapping(value = "/api/building")
	public Object getBuiding(@RequestParam(value = "name", required = false) String nameBuilding,
									@RequestParam(value = "numberOfBasement", required = false) Integer numberOfBasement,
									@RequestParam(value = "ward", required = false) String ward) {
		try {
			System.out.println(5/1);
		}
		catch (Exception e) {
			// TODO: handle exception
			ErrorResponseDTO error = new ErrorResponseDTO();
			error.setError(e.getMessage());
			List<String> details = new ArrayList<>();
			details.add("Loi chia cho 0");
			error.setDetail(details);
			return error;
			
		}
		System.out.println(5/0);
		BuildingDTO result = new BuildingDTO();
		result.setName(nameBuilding);
		result.setNumberOfBasement(numberOfBasement);

		return result;
	}
	public void valiDate(BuildingDTO buildingDTO) {
		if(buildingDTO.getName() == null || buildingDTO.getName() == ""|| buildingDTO.getNumberOfBasement()== null) {
			throw new FieldRequiredException("Name or num is null");
		}
	}
	
	@PostMapping(value = "/api/building")
	public Object postBuilding (@RequestBody BuildingDTO building) {
//		try {
//			valiDate(building);
//		}
//		catch (FieldRequiredException e) {
//			// TODO: handle exception
//			ErrorResponseDTO error =  new ErrorResponseDTO();
//			error.setError(e.getMessage());
//			List<String> detail = new ArrayList<String>();
//			detail.add("Check lai name or num");
//			error.setDetail(detail);
//			return error;
//		}
		valiDate(building);
		return building;
	}
	@DeleteMapping(value = "api/building/{id}")
	public String deleteByID(@PathVariable Integer id) {
		return "Da xoa id: "+ id;
	}
	
}
