package com.nissanusa.www.VehicleInformation;

import java.util.HashSet;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.jboss.logging.Logger;

import com.nissanusa.helios.ownerservice.util.OwnerConstants;

public class HeaderHandler  implements SOAPHandler<SOAPMessageContext>,OwnerConstants{
	
	private static final Logger LOG = Logger.getLogger(HeaderHandler.class);

	public boolean handleMessage(SOAPMessageContext smc) {
	    Boolean outboundProperty = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

	    if (outboundProperty.booleanValue()) {
	        SOAPMessage message = smc.getMessage();
	        try {
	            SOAPEnvelope envelope = smc.getMessage().getSOAPPart().getEnvelope();
	            envelope.addAttribute(new QName("xmlns:veh"), "http://www.nissanusa.com/VehicleInformation/");
	            envelope.addAttribute(new QName("xmlns:xsd"), "http://webservices.vehicleinfo.com/xsd");
	            SOAPHeader header = envelope.addHeader();
	            SOAPElement security =
	                    header.addChildElement("Security", "wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
	            SOAPElement usernameToken =security.addChildElement("UsernameToken", "wsse");
	            usernameToken.addAttribute(new QName("xmlns:wsu"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
	            SOAPElement username =usernameToken.addChildElement("Username", "wsse");
	            
	            username.addTextNode(BIDW_USER_NAME);
	            SOAPElement password =usernameToken.addChildElement("Password", "wsse");
	            password.setAttribute("Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
	            password.addTextNode(BIDW_PASSWORD);
	            //message.writeTo(System.out);
	        } catch (Exception e) {
	        	LOG.error("Exception in Headerhandler:: Method:handleMessage :: ",e);
	        }
	    } else {
	        try {
	            //This handler does nothing with the response from the Web Service so
	            //we just print out the SOAP message.
	            SOAPMessage message = smc.getMessage();
	           // message.writeTo(System.out);
	        } catch (Exception ex) {
	        	LOG.error("Exception in Headerhandler:: Method:handleMessage :: ",ex);
	        } 
	    }
	    return outboundProperty;
	}

	public Set getHeaders() {
	    // The code below is added on order to invoke Spring secured WS.
	    // Otherwise,
	    // http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd
	    // won't be recognised 
	    final QName securityHeader = new QName(
	            "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd",
	            "Security", "wsse");
	    final HashSet headers = new HashSet();
	    headers.add(securityHeader);
	    return headers;
	}

	public boolean handleFault(SOAPMessageContext context) {
	    //throw new UnsupportedOperationException("Not supported yet.");
	    return true;
	}

	public void close(MessageContext context) {
	//throw new UnsupportedOperationException("Not supported yet.");
	}
}
