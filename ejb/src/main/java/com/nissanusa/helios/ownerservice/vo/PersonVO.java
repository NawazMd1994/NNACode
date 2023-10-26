package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.nissanusa.helios.ownerservice.util.OwnerConstants;

/**
 * @author x178099
 * @use this class will hold the json request object for person in
 *      save,update,view and delete endpoints
 */

@JsonSerialize(include = Inclusion.NON_EMPTY)
public class PersonVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(OwnerConstants.EMAIL)
    private String email;
    @JsonProperty(OwnerConstants.PRSNHASH)
    private String personHashId;
    @JsonProperty(OwnerConstants.USRPRFLID)
    private String userProfileId;


    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the personHashId
     */
    public String getPersonHashId() {
        return personHashId;
    }

    /**
     * @param personHashId
     *            the personHashId to set
     */
    public void setPersonHashId(String personHashId) {
        this.personHashId = personHashId;
    }

	/**
	 * @return the userProfileId
	 */
	public String getUserProfileId() {
		return userProfileId;
	}

	/**
	 * @param userProfileId the userProfileId to set
	 */
	public void setUserProfileId(String userProfileId) {
		this.userProfileId = userProfileId;
	}

}
