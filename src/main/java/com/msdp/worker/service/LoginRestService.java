package com.msdp.worker.service;

import com.msdp.worker.logger.AppLogger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/login")
public class LoginRestService {

    AppLogger logger = AppLogger.getAppLogger(LoginRestService.class);

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Object postLogin(Object keys) throws Exception {

        Object loginResponse = null;

        try{
            return loginResponse;
        } catch(Exception e) {
            return loginResponse;
        }

    }
}
