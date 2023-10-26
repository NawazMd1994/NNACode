package com.nissanusa.helios.ownerservice.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "SBSCRN_PRDT", catalog = "")
@NamedQueries({ @NamedQuery(name = "OwnerPortalSubScriptionProduct.findBySubscriptionProductCode", query = "SELECT o FROM OwnerPortalSubScriptionProduct o WHERE o.subscriptionProductCode = :subscriptionProductCode"),

})
public class OwnerPortalSubScriptionProduct implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 2229163948264093221L;

    @Id
    @Basic(optional = false)
    @Column(name = "SBSCRN_PRDT_CD", nullable = false)
    private String subscriptionProductCode;

    @Basic(optional = false)
    @Column(name = "SBSCRN_PRDT_DS", nullable = false, length = 17)
    private String subscriptionProductDescription;

    @Basic(optional = false)
    @Column(name = "VHCL_TLMTCS_OPTN_TYP_CD", nullable = false, length = 17)
    private String vehicleTelematicsOptionCode;

    /**
     * @return the subscriptionProductCode
     */
    public String getSubscriptionProductCode() {
        return subscriptionProductCode;
    }

    /**
     * @param subscriptionProductCode
     *            the subscriptionProductCode to set
     */
    public void setSubscriptionProductCode(String subscriptionProductCode) {
        this.subscriptionProductCode = subscriptionProductCode;
    }

    /**
     * @return the subscriptionProductDescription
     */
    public String getSubscriptionProductDescription() {
        return subscriptionProductDescription;
    }

    /**
     * @param subscriptionProductDescription
     *            the subscriptionProductDescription to set
     */
    public void setSubscriptionProductDescription(
            String subscriptionProductDescription) {
        this.subscriptionProductDescription = subscriptionProductDescription;
    }

    /**
     * @return the vehicleTelematicsOptionCode
     */
    public String getVehicleTelematicsOptionCode() {
        return vehicleTelematicsOptionCode;
    }

    /**
     * @param vehicleTelematicsOptionCode
     *            the vehicleTelematicsOptionCode to set
     */
    public void setVehicleTelematicsOptionCode(
            String vehicleTelematicsOptionCode) {
        this.vehicleTelematicsOptionCode = vehicleTelematicsOptionCode;
    }

}
