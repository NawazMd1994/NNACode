package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.nissanusa.helios.ownerservice.util.OwnerConstants;

public class VehiclesListVO implements Serializable{

    private static final long serialVersionUID = 1L;
  
    @JsonProperty(OwnerConstants.MODELCODE)
    private String modelCode;
    @JsonProperty(OwnerConstants.FACTORYOPTIONCODE)
    private String factoryOptionCode;
    @JsonProperty(OwnerConstants.EXTERIORCOLORCODE)
    private String exteriorColorCode;
    @JsonProperty(OwnerConstants.VIN)
    private String vin;
    @JsonProperty(OwnerConstants.BODYSTYLENAME)
    private String bodyStyleName;
    @JsonProperty(OwnerConstants.MODELNAME)
    private String modelName;
    
	public String getModelCode() {
		return modelCode;
	}
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	
	public String getFactoryOptionCode() {
		return factoryOptionCode;
	}
	public void setFactoryOptionCode(String factoryOptionCode) {
		this.factoryOptionCode = factoryOptionCode;
	}
	public String getExteriorColorCode() {
		return exteriorColorCode;
	}
	public void setExteriorColorCode(String exteriorColorCode) {
		this.exteriorColorCode = exteriorColorCode;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getBodyStyleName() {
		return bodyStyleName;
	}
	public void setBodyStyleName(String bodyStyleName) {
		this.bodyStyleName = bodyStyleName;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
    
}
