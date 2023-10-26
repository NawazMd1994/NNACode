package com.nissanusa.helios.ownerservice.vo;



import org.codehaus.jackson.annotate.JsonProperty;

/**
 * x787640 To get the recall service object from request json
 */
public class RecallWrapper {

    @JsonProperty("OEMRecall")
    private OEMRecallVO oemRecall;

	/**
	 * @return the oemRecall
	 */
	public OEMRecallVO getOemRecall() {
		return oemRecall;
	}

	/**
	 * @param oemRecall the oemRecall to set
	 */
	public void setOemRecall(OEMRecallVO oemRecall) {
		this.oemRecall = oemRecall;
	}

	
    
}
