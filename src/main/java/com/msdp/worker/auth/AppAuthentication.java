package com.msdp.worker.auth;

import com.msdp.worker.exceptions.AppExceptionResponse;
import com.msdp.worker.exceptions.AppHTTPErrorCodes;
import com.msdp.worker.exceptions.AppWebErrorCodes;
import com.msdp.worker.logger.AppLogger;
import com.google.gson.Gson;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AppAuthentication {

    private static Map<String, AppSession> sessionCache = new HashMap<String, AppSession>();
    private static AppAuthentication service = null;
    private static AppLogger logger = AppLogger.getAppLogger(AppAuthentication.class);

    private AppAuthentication(){}

    public static AppAuthentication getInstance(){

        if(service == null) {

            service = new AppAuthentication();
        }


        return service;

    }

    public void updateSession(String userName, String sessionKey, Integer expirationInMinutes) {

        AppSession session = new AppSession();

        session.setUserName(userName);
        session.setSessionKey(sessionKey);

        if(expirationInMinutes == -1) {
            logger.info("No expiration time set!");
            session.setExpiration(null);
        } else {

            Calendar c =  Calendar.getInstance();
            long t = c.getTimeInMillis();

            Date expiry = new Date(t + (expirationInMinutes * 60000));
            session.setExpiration(expiry);

        }


        sessionCache.put(sessionKey, session);
    }

    public static void removeSession(String sessionKey) {
        logger.info("Remove session key: " + sessionKey);
        sessionCache.remove(sessionKey);
    }

    private boolean isSessionExpired(AppSession session) {

        long currentTime = Calendar.getInstance().getTimeInMillis();

        if(session.getExpiration() == null) {
            logger.info("No expiration set for sessionKey : " + session.getSessionKey());
            return false;
        }
        long expirationTime = session.getExpiration().getTime();


        if(currentTime < expirationTime) {
            logger.info("Session is still valid");
            return false;

        } else {
            logger.info("Session has expired");


            removeSession(session.getSessionKey());
            return true;
        }

    }

    public boolean validateSession(String sessionKey) {

        AppSession session = sessionCache.get(sessionKey);

        //No session found in cache
        if(session == null) {

            logger.info("Session key not found in cache");
            return false;


            //Session found. Verify expiration
        } else {

            logger.info("Session key found");
            return !isSessionExpired(session);

        }
    }

    public String getUser(String sessionKey) {

        if(validateSession(sessionKey)) {
            return sessionCache.get(sessionKey).getUserName();
        } else {
            return null;
        }
    }

    public static void notAuthorized(ContainerRequestContext requestContext) throws WebApplicationException {

        //Create the exception response
        AppExceptionResponse er = new AppExceptionResponse();
        er.setErrorCode(AppWebErrorCodes.UNAUTHORIZED.value());
        er.setErrorMessage(AppWebErrorCodes.UNAUTHORIZED.value());
        er.setHttpErrorCode(AppHTTPErrorCodes.E401.toString());

        logger.info("Accept: " + requestContext.getHeaderString("Accept").trim());

        String response = null;

        if(MediaType.APPLICATION_XML.equals(requestContext.getHeaderString("Accept").trim())) {

            response = "<ExceptionResponse errorMessage=\"" + er.getErrorMessage() + "\" httpErrorCode=\"" + er.getHttpErrorCode() + "\" errorCode=\"" + er.getErrorCode() + "\"/>";
        } else {
            response = (new Gson()).toJson(er);
        }



        //Set exception message as response entity
        Response.ResponseBuilder builder = null;
        builder = Response.status(Response.Status.UNAUTHORIZED).entity(response);
        throw new WebApplicationException(builder.build());

    }




}
