package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.nissanusa.helios.ownerservice.util.OwnerConstants;

/**
 * @author x178099
 * @use this class will hold the json request object for getting address in
 *      account endpoint
 */
@JsonSerialize(include = Inclusion.NON_EMPTY)
public class OptInVO implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonProperty(OwnerConstants.ALLOW_TEXT)
    private List<String> allowText;
    @JsonProperty(OwnerConstants.ALLOW_AUTO_DIAL)
    private List<String> allowAutoDial;

    /**
     * @return the allowText
     */
    public List<String> getAllowText() {
        return allowText;
    }

    /**
     * @param allowText
     *            the allowText to set
     */
    public void setAllowText(List<String> allowText) {
        this.allowText = allowText;
    }

    /**
     * @return the allowAutoDial
     */
    public List<String> getAllowAutoDial() {
        return allowAutoDial;
    }

    /**
     * @param allowAutoDial
     *            the allowAutoDial to set
     */
    public void setAllowAutoDial(List<String> allowAutoDial) {
        this.allowAutoDial = allowAutoDial;
    }

}
