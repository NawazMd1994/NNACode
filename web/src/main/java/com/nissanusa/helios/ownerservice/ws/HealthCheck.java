package com.nissanusa.helios.ownerservice.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/healthcheck")
public class HealthCheck {

    @GET
    @Path("/status")
    @Produces("text/plain")
    public String status() {
        return "owner service is up and running ";
    }

}
