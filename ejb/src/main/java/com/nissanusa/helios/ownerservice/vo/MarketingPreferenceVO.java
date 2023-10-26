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
public class MarketingPreferenceVO implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonProperty(OwnerConstants.OPT_IN)
    private boolean optIn;
    @JsonProperty(OwnerConstants.TOPICS)
    private List<String> topics;
    @JsonProperty(OwnerConstants.CHANNELS)
    private List<String> channels;
    @JsonProperty(OwnerConstants.NEWSLETTER)
    private List<String> newsletter;
    @JsonProperty(OwnerConstants.PRODUCTOFFERS)
    private List<String> productOffers;
    @JsonProperty(OwnerConstants.SERVICEOFFERS)
    private List<String> serviceOffers;
    @JsonProperty(OwnerConstants.SCHEDULEDMAINTENANCE)
    private List<String> scheduledMaintenance;
    @JsonProperty(OwnerConstants.FEEDBACK)
    private List<String> feedback;

    /**
     * @return the optIn
     */
    public boolean isOptIn() {
        return optIn;
    }

    /**
     * @param optIn
     *            the optIn to set
     */
    public void setOptIn(boolean optIn) {
        this.optIn = optIn;
    }

    /**
     * @return the topics
     */
    public List<String> getTopics() {
        return topics;
    }

    /**
     * @param topics
     *            the topics to set
     */
    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    /**
     * @return the channels
     */
    public List<String> getChannels() {
        return channels;
    }

    /**
     * @param channels
     *            the channels to set
     */
    public void setChannels(List<String> channels) {
        this.channels = channels;
    }

    /**
     * @return the newsLetter
     */
	public List<String> getNewsletter() {
		return newsletter;
	}

	/**
     * @param newsLetter
     *            the newsLetter to set
     */
	public void setNewsletter(List<String> newsletter) {
		this.newsletter = newsletter;
	}


    /**
     * @return the productOffers
     */
	public List<String> getProductOffers() {
		return productOffers;
	}

	/**
     * @param productOffers
     *            the productOffers to set
     */
	public void setProductOffers(List<String> productOffers) {
		this.productOffers = productOffers;
	}


    /**
     * @return the serviceOffers
     */
	public List<String> getServiceOffers() {
		return serviceOffers;
	}

	/**
     * @param serviceOffers
     *            the serviceOffers to set
     */
	public void setServiceOffers(List<String> serviceOffers) {
		this.serviceOffers = serviceOffers;
	}


    /**
     * @return the scheduledMaintenance
     */
	public List<String> getScheduledMaintenance() {
		return scheduledMaintenance;
	}

	/**
     * @param scheduledMaintenance
     *            the scheduledMaintenance to set
     */
	public void setScheduledMaintenance(List<String> scheduledMaintenance) {
		this.scheduledMaintenance = scheduledMaintenance;
	}


    /**
     * @return the feedback
     */
	public List<String> getFeedback() {
		return feedback;
	}

	/**
     * @param feedback
     *            the feedback to set
     */
	public void setFeedback(List<String> feedback) {
		this.feedback = feedback;
	}
}
