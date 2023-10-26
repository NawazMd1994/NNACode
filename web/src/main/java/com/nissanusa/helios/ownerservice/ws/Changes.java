package com.nissanusa.helios.ownerservice.ws;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.logging.Logger;

/**
 * 
 * @author x787640
 * @use to get the details of changes from version file
 *
 */

@Path("/changes")
public class Changes {

    private static final Logger LOG = Logger.getLogger(Changes.class);

    @GET
    @Path("/version")
    @Produces("text/plain")
    public String status() {
        InputStream versionFile = this.getClass().getResourceAsStream(
                "/version.xml");
        String read = "";
        String version = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    versionFile));
            while ((read = in.readLine()) != null) {
                version = version + "\n" + read;
            }
        } catch (FileNotFoundException e) {
            LOG.info("File Not found" + versionFile);
        } catch (IOException e) {
            LOG.info("Error while reading the version File" + versionFile);
        }
        return version;

    }

}
