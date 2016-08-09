package com.msdp.worker.service;

import com.msdp.worker.logger.AppLogger;
import com.msdp.worker.utils.AppUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Random;


@Path("/accounts")
public class AccountRestService {

    AppLogger logger = AppLogger.getAppLogger(AccountRestService.class);

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Object postAccount(@HeaderParam("X-App-Request") String sessionKey, AccountConfig config) throws Exception {
        try{
            return config;
        } catch(Exception e) {
            return config;
        }
    }

    @GET
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Object getAccounts(@HeaderParam("X-App-Request") String sessionKey) throws Exception {

        logger.info("In getDetail");
        AccountsResponseConfig listConfig = new AccountsResponseConfig();
        ArrayList<AccountConfig> configArr = new ArrayList<AccountConfig>(6);
        try{
            int numRecords=0;
            for (int i = 0; i < 6; i++) {
                AccountConfig config = new AccountConfig();
                config.setId(AppUtils.generateUUID());
                config.setName("account"+Integer.toString(i));
                config.setKey(AppUtils.generateUUID());
                config.setCloud("Amazon");
                Random r = new Random();
                int randomInt = r.nextInt(100) + 1;
                config.setNumInstances(Integer.toString(randomInt));
                randomInt = r.nextInt(100) + 1;
                config.setNumVolumes(Integer.toString(randomInt));
                randomInt = r.nextInt(100) + 1;
                config.setNumSnapshots(Integer.toString(randomInt));
                configArr.add(config);
                numRecords++;
            }
            listConfig.setNumRecords(Integer.toString(numRecords));
            listConfig.setAccounts(configArr);
            return listConfig;
        } catch(Exception e) {
            return null;
        }
    }

}
