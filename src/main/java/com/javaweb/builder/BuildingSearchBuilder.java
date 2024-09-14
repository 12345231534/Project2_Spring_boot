package com.javaweb.builder;

import java.util.ArrayList;
import java.util.List;

public class BuildingSearchBuilder {
	private String name;
	private Integer numberOfBasement;
	private String address;
	private String managerName;
	private String managerPhoneNumber;
	private Integer floorArea;
	private Integer emptyFloorArea;
	private Integer serviceFee;
	private Integer brokerageFee;
	private Integer staffID;
	private Integer areaFrom;
	private Integer areaTo;
	private Integer priceFrom;
	private Integer priceTo;
	private List<String> typeCode = new ArrayList<>();
	
	private BuildingSearchBuilder(Builder builder) {
		this.name = builder.name;
		this.numberOfBasement = builder.numberOfBasement;
		this.address = builder.address;
		this.managerName = builder.managerName;
		this.managerPhoneNumber = builder.managerPhoneNumber;
		this.floorArea = builder.floorArea;
		this.emptyFloorArea = builder.emptyFloorArea;
		this.serviceFee = builder.serviceFee;
		this.brokerageFee = builder.brokerageFee;
		this.staffID = builder.staffID;
		this.areaFrom = builder.areaFrom;
		this.areaTo = builder.areaTo;
		this.priceFrom = builder.priceFrom;
		this.priceTo = builder.priceTo;
		this.typeCode = builder.typeCode;
	}
	
	
	public String getName() {
		return name;
	}
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public String getAddress() {
		return address;
	}
	public String getManagerName() {
		return managerName;
	}
	public String getManagerPhoneNumber() {
		return managerPhoneNumber;
	}
	public Integer getFloorArea() {
		return floorArea;
	}
	public Integer getEmptyFloorArea() {
		return emptyFloorArea;
	}
	public Integer getServiceFee() {
		return serviceFee;
	}
	public Integer getBrokerageFee() {
		return brokerageFee;
	}
	public Integer getStaffID() {
		return staffID;
	}
	public Integer getAreaFrom() {
		return areaFrom;
	}
	public Integer getAreaTo() {
		return areaTo;
	}
	public Integer getPriceFrom() {
		return priceFrom;
	}
	public Integer getPriceTo() {
		return priceTo;
	}
	public List<String> getTypeCode() {
		return typeCode;
	}
	
	public static class Builder{
		private String name;
		private Integer numberOfBasement;
		private String address;
		private String managerName;
		private String managerPhoneNumber;
		private Integer floorArea;
		private Integer emptyFloorArea;
		private Integer serviceFee;
		private Integer brokerageFee;
		private Integer staffID;
		private Integer areaFrom;
		private Integer areaTo;
		private Integer priceFrom;
		private Integer priceTo;
		private List<String> typeCode = new ArrayList<>();
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		public Builder setNumberOfBasement(Integer numberOfBasement) {
			this.numberOfBasement = numberOfBasement;
			return this;
		}
		public Builder setAddress(String address) {
			this.address = address;
			return this;
		}
		public Builder setManagerName(String managerName) {
			this.managerName = managerName;
			return this;
		}
		public Builder setManagerPhoneNumber(String managerPhoneNumber) {
			this.managerPhoneNumber = managerPhoneNumber;
			return this;
		}
		public Builder setFloorArea(Integer floorArea) {
			this.floorArea = floorArea;
			return this;
		}
		public Builder setEmptyFloorArea(Integer emptyFloorArea) {
			this.emptyFloorArea = emptyFloorArea;
			return this;
		}
		public Builder setServiceFee(Integer serviceFee) {
			this.serviceFee = serviceFee;
			return this;
		}
		public Builder setBrokerageFee(Integer brokerageFee) {
			this.brokerageFee = brokerageFee;
			return this;
		}
		public Builder setStaffID(Integer staffID) {
			this.staffID = staffID;
			return this;
		}
		public Builder setAreaFrom(Integer areaFrom) {
			this.areaFrom = areaFrom;
			return this;
		}
		public Builder setAreaTo(Integer areaTo) {
			this.areaTo = areaTo;
			return this;
		}
		public Builder setPriceFrom(Integer priceFrom) {
			this.priceFrom = priceFrom;
			return this;
		}
		public Builder setPriceTo(Integer priceTo) {
			this.priceTo = priceTo;
			return this;
		}
		public Builder setTypeCode(List<String> typeCode) {
			this.typeCode = typeCode;
			return this;
		}
		public BuildingSearchBuilder build() {
			
			return new BuildingSearchBuilder(this);
		}
		
		
	}
}

