package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.nissanusa.helios.ownerservice.util.OwnerConstants;

/**
 * X055765 To get the service history details from request json
 */
@JsonSerialize(include = Inclusion.NON_EMPTY)
public class ServiceVO implements Serializable{

	private static final long serialVersionUID = -1487040285304495404L;

	@JsonProperty(OwnerConstants.HISTORY)
	private List<HistoryVO> history;

	/**
	 * @return the HistoryVO
	 */
	public List<HistoryVO> getHistory() {
		return history;
	}

	/**
	 * @param HistoryVO the HistoryVO to set
	 */
	public void setHistory(List<HistoryVO> history) {
		this.history = history;
	}

}
