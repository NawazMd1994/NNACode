package com.nissanusa.helios.ownerservice.ws;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/*
 *last modified date 06-06-2016 by x178099

 */
/**
 * 
 * @author x178099
 * @use The JaxRsActivator class initializes JAX-RS without the need to use a
 *      web.xml file. This is achieved by extending the Application class and
 *      using the @ApplicationPath annotation
 *
 */
@ApplicationPath("/")
public class JaxRsActivator extends Application {
    /* class body intentionally left blank */
}
