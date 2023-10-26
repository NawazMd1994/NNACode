package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.nissanusa.helios.ownerservice.util.OwnerConstants;

@JsonSerialize(include = Inclusion.NON_EMPTY)
public class MessageDetailsVO  implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty(OwnerConstants.MESSAGE_ID)
	private String msgId;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	}
