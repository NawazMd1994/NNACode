package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * X055765 To get the service history details from request json
 */

@JsonSerialize(include = Inclusion.NON_EMPTY)
public class PersonVehicleServiceHistoryVO implements Serializable {

	private static final long serialVersionUID = -8371767771331825513L;

	private PersonVO person;
	private VehicleVO vehicle;
	private ServiceVO service;
	private String brand;

	public PersonVO getPerson() {
		return person;
	}
	public void setPerson(PersonVO person) {
		this.person = person;
	}
	public VehicleVO getVehicle() {
		return vehicle;
	}
	public void setVehicle(VehicleVO vehicle) {
		this.vehicle = vehicle;
	}
	public ServiceVO getService() {
		return service;
	}
	public void setService(ServiceVO service) {
		this.service = service;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}

}
