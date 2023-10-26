/**
 * 
 */
package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * @author RS101547 To get the telematics json object from request json
 *
 */
@JsonSerialize(include = Inclusion.NON_EMPTY)
public class GetTelematicsVO implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1808013407092762499L;
    private PersonVO person;
    private VehicleVO vehicle;
    private List<TelematicsSubscriptionVO> subscription;
    private SubscriptionSsoTokenVO ssoToken;
    private TelematicsVO telematics;

    
    /**
     * @return the telematics
     */
    public TelematicsVO getTelematics() {
		return telematics;
	}

    /**
     * @param telematics
     *            the telematics to set
     */
	public void setTelematics(TelematicsVO telematics) {
		this.telematics = telematics;
	}

	/**
     * @return the person
     */
    public PersonVO getPerson() {
        return person;
    }

    /**
     * @param person
     *            the person to set
     */
    public void setPerson(PersonVO person) {
        this.person = person;
    }

    /**
     * @return the vehicle
     */
    public VehicleVO getVehicle() {
        return vehicle;
    }

    /**
     * @param vehicle
     *            the vehicle to set
     */
    public void setVehicle(VehicleVO vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * @return the subscription
     */
    public List<TelematicsSubscriptionVO> getSubscription() {
        return subscription;
    }

    /**
     * @param subscription
     *            the subscription to set
     */
    public void setSubscription(List<TelematicsSubscriptionVO> subscription) {
        this.subscription = subscription;
    }

    /**
     * @return the ssoToken
     */
    public SubscriptionSsoTokenVO getSsoToken() {
        return ssoToken;
    }

    /**
     * @param ssoToken
     *            the ssoToken to set
     */
    public void setSsoToken(SubscriptionSsoTokenVO ssoToken) {
        this.ssoToken = ssoToken;
    }

}
