package com.msdp.worker.service;

import com.msdp.worker.logger.AppLogger;
import com.msdp.worker.utils.AppUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


@Path("/volumes")
public class VolumeRestService {

    AppLogger logger = AppLogger.getAppLogger(VolumeRestService.class);

    @GET
    @Path("{accountId}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Object getDetails(@HeaderParam("X-App-Request") String sessionKey, @PathParam("accountId") String accountId) throws Exception {

        VolumeResponseConfig listConfig = new VolumeResponseConfig();
        ArrayList<VolumeConfig> configArr = new ArrayList<VolumeConfig>(50);
        try{
            for (int i = 0; i < 50; i++) {
                VolumeConfig config = new VolumeConfig();
                config.setId(AppUtils.generateUUID());
                config.setStatus("active");
                long nowTime = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
                Date resultdate = new Date(nowTime);
                config.setTime(sdf.format(resultdate));
                Random r = new Random();
                int randomInt = r.nextInt(100) + 1;
                config.setSize(Integer.toString(randomInt));
                configArr.add(config);
            }
            listConfig.setAccountName(accountId);
            listConfig.setVolumes(configArr);
            return listConfig;
        } catch(Exception e) {
            return null;
        }
    }
}
