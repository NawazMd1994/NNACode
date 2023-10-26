package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.nissanusa.helios.ownerservice.util.OwnerConstants;

/**
 * x787640 To get the recall service details from request json
 */
@JsonSerialize(include = Inclusion.NON_EMPTY)
public class OEMRecallVO implements Serializable {
	
   
  private static final long serialVersionUID = -1487040285304495404L;
  
  @JsonProperty(OwnerConstants.Vin)
   private String vin;
  
  @JsonProperty(OwnerConstants.Make)
   private  String make;
  
  @JsonProperty(OwnerConstants.VEHICLE_LINE_NAME)
   private  String vehicleLineName;
  
  @JsonProperty(OwnerConstants.MODEL_YEAR)
   private String modelYear;
  
  @JsonProperty(OwnerConstants.MAX_REFRESH_DATE)
   private String maxRefreshDate;
  
  @JsonProperty(OwnerConstants.MIN_RECALL_DATE)
   private String minRecallDate;
  
  @JsonProperty(OwnerConstants.oemRECALLDATA)
   private List<OEMRecallDataVO> oemRecallDataVO;

/**
 * @return the vin
 */
public String getVin() {
	return vin;
}

/**
 * @param vin the vin to set
 */
public void setVin(String vin) {
	this.vin = vin;
}

/**
 * @return the make
 */
public String getMake() {
	return make;
}

/**
 * @param make the make to set
 */
public void setMake(String make) {
	this.make = make;
}

/**
 * @return the vehicleLineName
 */
public String getVehicleLineName() {
	return vehicleLineName;
}

/**
 * @param vehicleLineName the vehicleLineName to set
 */
public void setVehicleLineName(String vehicleLineName) {
	this.vehicleLineName = vehicleLineName;
}

/**
 * @return the modelYear
 */
public String getModelYear() {
	return modelYear;
}

/**
 * @param modelYear the modelYear to set
 */
public void setModelYear(String modelYear) {
	this.modelYear = modelYear;
}

/**
 * @return the maxRefreshDate
 */
public String getMaxRefreshDate() {
	return maxRefreshDate;
}

/**
 * @param maxRefreshDate the maxRefreshDate to set
 */
public void setMaxRefreshDate(String maxRefreshDate) {
	this.maxRefreshDate = maxRefreshDate;
}

/**
 * @return the minRecallDate
 */
public String getMinRecallDate() {
	return minRecallDate;
}

/**
 * @param minRecallDate the minRecallDate to set
 */
public void setMinRecallDate(String minRecallDate) {
	this.minRecallDate = minRecallDate;
}

/**
 * @return the oemRecallDataVO
 */
public List<OEMRecallDataVO> getOemRecallDataVO() {
	return oemRecallDataVO;
}

/**
 * @param oemRecallDataVO the oemRecallDataVO to set
 */
public void setOemRecallDataVO(List<OEMRecallDataVO> oemRecallDataVO) {
	this.oemRecallDataVO = oemRecallDataVO;
}


   
   

    


}
