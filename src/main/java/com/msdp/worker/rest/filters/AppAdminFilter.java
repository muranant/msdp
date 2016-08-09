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
@Priority(Priorities.AUTHORIZATION)
public class AppAdminFilter implements ContainerRequestFilter {

    AppLogger logger = AppLogger.getAppLogger(AppAdminFilter.class);

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

        logger.info("=================$$Admin Request Params$$==================");
        logger.info("Request Method: " + requestContext.getMethod());
        logger.info("URI : " + uriInfo.getRequestUri());
        logger.info("Request Time: " + requestContext.getProperty("Request-Time"));
        logger.info("Request ID : " + requestContext.getProperty("Request-ID"));
        logger.info("Session Key : " + sessionKey);
        logger.info("===================================");


        String user = AppAuthentication.getInstance().getUser(sessionKey);

        if("admin".equals(user)) {

            logger.info("Admin user validated.");
        } else {
            logger.info("Only admin can perform this operation!");
            AppAuthentication.notAuthorized(requestContext);
        }






    }

}
