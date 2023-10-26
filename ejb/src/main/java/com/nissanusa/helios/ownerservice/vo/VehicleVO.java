package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.nissanusa.helios.ownerservice.util.OwnerConstants;

/**
 * @author x178099
 * @use this class will hold the json request object for getting vehicle details
 *      in save,update,view and delete endpoints.
 */
@JsonSerialize(include = Inclusion.NON_EMPTY)
public class VehicleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(OwnerConstants.VIN)
    private String vin;
    @JsonProperty(OwnerConstants.NICKNAME)
    private String nickname;
    @JsonProperty(OwnerConstants.MILEAGE)
    private Integer mileage;
    @JsonProperty(OwnerConstants.AVGMILEAGE)
    private Integer averageMileage;
    @JsonProperty(OwnerConstants.MODELCODE)
    private String modelCode;
    @JsonProperty(OwnerConstants.MODELNAME)
    private String modelName;
    @JsonProperty(OwnerConstants.LINECODE)
    private String modelLineCode;
    @JsonProperty(OwnerConstants.INTERIORCOLORNAME)
    private String interiorColorName;
    @JsonProperty(OwnerConstants.INTERIORCOLORCODE)
    private String interiorColorCode;
    @JsonProperty(OwnerConstants.EXTERIORCOLORCODE)
    private String exteriorColorCode;
    @JsonProperty(OwnerConstants.EXTERIORCOLORNAME)
    private String exteriorColorName;
    @JsonProperty(OwnerConstants.OPTIONCODE)
    private String optionCode;
    @JsonProperty(OwnerConstants.MAKE)
    private String make;
    @JsonProperty(OwnerConstants.FILENAME)
    private String fileName;
    @JsonProperty(OwnerConstants.YEAR)
    private String modelYear;
    @JsonProperty(OwnerConstants.CONTENT_TYPE)
    private String contentType;
    @JsonProperty(OwnerConstants.CONTENT)
    private byte[] content;
    @JsonProperty(OwnerConstants.LEASETERM)
    private Integer leaseTerm;
//    @JsonProperty(OwnerConstants.DEALERID)
//    private String dealerId;
    @JsonProperty(OwnerConstants.CS_STATUS)
    private String status;
    @JsonProperty(OwnerConstants.TRIALPERIOD)
    private String trialPeriod;
    @JsonProperty(OwnerConstants.BODYSTYLENAME)
    private String bodyStyleName;
    @JsonProperty(OwnerConstants.OWNED)
    private List<VehiclesListVO> owned;
    @JsonProperty(OwnerConstants.PREFERRED_DEALER)
    private String preferredDealer;

    @JsonProperty(OwnerConstants.ISDELETED)
    private IsVehicleDeletedVO isVehicleDeletedVO;
    

    /**
	 * @return the leaseTerm
	 */
	public Integer getLeaseTerm() {
		return leaseTerm;
	}

	/**
	 * @param leaseTerm the leaseTerm to set
	 */
	public void setLeaseTerm(Integer leaseTerm) {
		this.leaseTerm = leaseTerm;
	}


    /**
	 * @return the preferredDealer
	 */
	public String getPreferredDealer() {
		return preferredDealer;
	}

	/**
	 * @param preferredDealer the preferredDealer to set
	 */
	public void setPreferredDealer(String preferredDealer) {
		this.preferredDealer = preferredDealer;
	}
//    public String getDealerId() {
//		return dealerId;
//	}

//	public void setDealerId(String dealerId) {
//		this.dealerId = dealerId;
//	}

	/**
     * @return the vin
     */
    public String getVin() {
        return vin;
    }

    /**
     * @param vin
     *            the vin to set
     */
    public void setVin(String vin) {
        this.vin = vin;
    }

    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     *            the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return the mileage
     */
    public Integer getMileage() {
        return mileage;
    }

    /**
     * @param mileage
     *            the mileage to set
     */
    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    /**
     * @return the averageMileage
     */
    public Integer getAverageMileage() {
        return averageMileage;
    }

    /**
     * @param averageMileage
     *            the averageMileage to set
     */
    public void setAverageMileage(Integer averageMileage) {
        this.averageMileage = averageMileage;
    }

    /**
     * @return the modelCode
     */
    public String getModelCode() {
        return modelCode;
    }

    /**
     * @param modelCode
     *            the modelCode to set
     */
    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    /**
     * @return the modelName
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * @param modelName
     *            the modelName to set
     */
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    /**
     * @return the modelLineCode
     */
    public String getModelLineCode() {
        return modelLineCode;
    }

    /**
     * @param modelLineCode
     *            the modelLineCode to set
     */
    public void setModelLineCode(String modelLineCode) {
        this.modelLineCode = modelLineCode;
    }

    /**
     * @return the interiorColorName
     */
    public String getInteriorColorName() {
        return interiorColorName;
    }

    /**
     * @param interiorColorName
     *            the interiorColorName to set
     */
    public void setInteriorColorName(String interiorColorName) {
        this.interiorColorName = interiorColorName;
    }

    /**
     * @return the interiorColorCode
     */
    public String getInteriorColorCode() {
        return interiorColorCode;
    }

    /**
     * @param interiorColorCode
     *            the interiorColorCode to set
     */
    public void setInteriorColorCode(String interiorColorCode) {
        this.interiorColorCode = interiorColorCode;
    }

    /**
     * @return the exteriorColorCode
     */
    public String getExteriorColorCode() {
        return exteriorColorCode;
    }

    /**
     * @param exteriorColorCode
     *            the exteriorColorCode to set
     */
    public void setExteriorColorCode(String exteriorColorCode) {
        this.exteriorColorCode = exteriorColorCode;
    }

    /**
     * @return the exteriorColorName
     */
    public String getExteriorColorName() {
        return exteriorColorName;
    }

    /**
     * @param exteriorColorName
     *            the exteriorColorName to set
     */
    public void setExteriorColorName(String exteriorColorName) {
        this.exteriorColorName = exteriorColorName;
    }

    /**
     * @return the optionCode
     */
    public String getOptionCode() {
        return optionCode;
    }

    /**
     * @param optionCode
     *            the optionCode to set
     */
    public void setOptionCode(String optionCode) {
        this.optionCode = optionCode;
    }

    /**
     * @return the make
     */
    public String getMake() {
        return make;
    }

    /**
     * @param make
     *            the make to set
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * @return the modelYear
     */
    public String getModelYear() {
        return modelYear;
    }

    /**
     * @param modelYear
     *            the modelYear to set
     */
    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return the content
	 */
	public byte[] getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTrialPeriod() {
		return trialPeriod;
	}

	public void setTrialPeriod(String trialPeriod) {
		this.trialPeriod = trialPeriod;
	}

	public List<VehiclesListVO> getOwned() {
		return owned;
	}

	public void setOwned(List<VehiclesListVO> owned) {
		this.owned = owned;
	}

	public String getBodyStyleName() {
		return bodyStyleName;
	}

	public void setBodyStyleName(String bodyStyleName) {
		this.bodyStyleName = bodyStyleName;
	}

	public IsVehicleDeletedVO getIsVehicleDeletedVO() {
		return isVehicleDeletedVO;
	}

	public void setIsVehicleDeletedVO(IsVehicleDeletedVO isVehicleDeletedVO) {
		this.isVehicleDeletedVO = isVehicleDeletedVO;
	}
	
	
}
