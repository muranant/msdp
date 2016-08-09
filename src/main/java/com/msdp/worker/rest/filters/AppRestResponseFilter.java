package com.msdp.worker.rest.filters;


import com.msdp.worker.exceptions.AppExceptionResponse;
import com.msdp.worker.logger.AppLogger;
import com.google.gson.Gson;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Priority(Priorities.ENTITY_CODER)
public class AppRestResponseFilter implements ContainerResponseFilter {

    AppLogger logger = AppLogger.getAppLogger(AppRestResponseFilter.class);

    @Context
    UriInfo uriInfo;

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {


        if(responseContext.getEntity() instanceof AppExceptionResponse) {

            AppExceptionResponse exp = (AppExceptionResponse) responseContext.getEntity();
            responseContext.setStatus(Integer.parseInt(exp.getHttpErrorCode()));
        }
        
        logger.info("=================$$Response For$$==================");
        logger.info("Request Method : " + requestContext.getMethod());
        logger.info("URI : " + uriInfo.getRequestUri());
        logger.info("Request Time : " + requestContext.getProperty("Request-Time"));
        logger.info("Request ID : " + requestContext.getProperty("Request-ID"));
        logger.info("Response Status : " + responseContext.getStatus());
        logger.info("Response : " + (new Gson().toJson(responseContext.getEntity())));
        logger.info("===============================================");

    }
}
