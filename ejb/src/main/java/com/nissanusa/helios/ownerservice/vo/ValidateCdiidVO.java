package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.nissanusa.helios.ownerservice.util.OwnerConstants;

/**
 * @author x178099
 * @use this class will hold the json request object for validateCdiid endpoint
 */

@JsonSerialize(include = Inclusion.NON_EMPTY)
public class ValidateCdiidVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(OwnerConstants.CDIID)
    private String cdiid;
    @JsonProperty(OwnerConstants.EMAIL)
    private String email;
    @JsonProperty(OwnerConstants.PRSNHASH)
    private String personHashId;

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
     * @return the cdiid
     */
    public String getCdiid() {
        return cdiid;
    }

    /**
     * @param cdiid
     *            the cdiid to set
     */
    public void setCdiid(String cdiid) {
        this.cdiid = cdiid;
    }

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

}
