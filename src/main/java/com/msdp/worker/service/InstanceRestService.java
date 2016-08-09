package com.msdp.worker.service;

import com.msdp.worker.logger.AppLogger;
import com.msdp.worker.utils.AppUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


@Path("/instances")
public class InstanceRestService {

    AppLogger logger = AppLogger.getAppLogger(InstanceRestService.class);

    @GET
    @Path("{accountId}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Object getDetail(@HeaderParam("X-App-Request") String sessionKey, @PathParam("accountId") String accountId) throws Exception {

        logger.info("In getDetail");
        InstanceResponseConfig listConfig = new InstanceResponseConfig();
        ArrayList<InstanceConfig> configArr = new ArrayList<InstanceConfig>(60);
        try{
            for (int i = 0; i < 60; i++) {
                InstanceConfig config = new InstanceConfig();
                config.setId(AppUtils.generateUUID());
                config.setName("instance"+Integer.toString(i));
                config.setUpTime("Very less");
                config.setStatus("running");
                long nowTime = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
                Date date = new Date(nowTime);
                config.setTime(sdf.format(date));
                configArr.add(config);
            }
            listConfig.setAccountName(accountId);
            listConfig.setInstances(configArr);
            return listConfig;
        } catch(Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
