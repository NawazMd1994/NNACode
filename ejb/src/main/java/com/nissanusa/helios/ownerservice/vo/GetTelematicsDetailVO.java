package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;



import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;



@JsonSerialize(include = Inclusion.NON_EMPTY)
public class GetTelematicsDetailVO implements Serializable {

	    private static final long serialVersionUID = 1808013407092762499L;
	    private PersonVO person;
	    private VehicleVO vehicle;
	   
	    private RequestedServiceVO requestedService;

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

		public RequestedServiceVO getRequestedService() {
			return requestedService;
		}

		public void setRequestedService(RequestedServiceVO requestedService) {
			this.requestedService = requestedService;
		}
	 
	   

	
	 
	 
}
