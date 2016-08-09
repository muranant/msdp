package com.msdp.worker.rest.filters;


import com.msdp.worker.auth.AppAdmin;
import com.msdp.worker.auth.AppAuthentication;
import com.msdp.worker.logger.AppLogger;
import com.msdp.worker.utils.AppUtils;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Date;


@AppAdmin
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AppAuthFilter implements ContainerRequestFilter {

    private static AppLogger logger = AppLogger.getAppLogger(AppAuthFilter.class);

    private final static String SESSION_HEADER = "X-App-Request";

    @Context
    UriInfo uriInfo;

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        requestContext.setProperty("Request-Time", new Date());
        requestContext.setProperty("Request-ID", AppUtils.generateUUID());

        String sessionKey = requestContext.getHeaderString(SESSION_HEADER);

        logger.info("=================$$Auth Request Params$$==================");
        logger.info("Request Method: " + requestContext.getMethod());
        logger.info("URI : " + uriInfo.getRequestUri());
        logger.info("Request Time: " + requestContext.getProperty("Request-Time"));
        logger.info("Request ID : " + requestContext.getProperty("Request-ID"));
        logger.info("Session Key : " + sessionKey);
        logger.info("===================================");


        if("login".equals(uriInfo.getPath().trim())) {

            return;

        }

        if(sessionKey == null) {

            AppAuthentication.notAuthorized(requestContext);

        } else {

            logger.info("Validating session key...");

            boolean isSessionValid = AppAuthentication.getInstance().validateSession(sessionKey);


            //UnAuthorize if session is invalid
            if(!isSessionValid) {

                AppAuthentication.notAuthorized(requestContext);
            }
        }



    }

}
