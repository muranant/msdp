/*******************************************************************************
 ******************************************************************************/
package com.msdp.worker.exceptions;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum of the supported Http Status code supported in CAPI Framework
 * 
 */
public enum AppHTTPErrorCodes {

    E400("400", false),
    E401("401", true),
    E402("402", false),
    E403("403", false),
    E404("404", false),
    E409("409", false),
    E410("410", false),
    E500("500", true),
    E503("503", true),
    E504("504", true);


    private static final Map<String, AppHTTPErrorCodes> lookup = new HashMap<String, AppHTTPErrorCodes>();
    static {
        // Create reverse lookup hash map
        for (AppHTTPErrorCodes d : AppHTTPErrorCodes.values())
            lookup.put(d.getHttpErrorCode(), d);
    }

    private String httpErrorCode;
    private boolean retriable;

    private AppHTTPErrorCodes(String cStyle, boolean retriable) {
        this.httpErrorCode = cStyle;
        this.retriable = retriable;
    }

    /**
     * Return the Http Status code enum as String
     */
    public String getHttpErrorCode() {
        return httpErrorCode;
    }
    
    public boolean isRetriable() {
    	return retriable;
    }

    /**
     * Return the Http Status code enum
     */
    public static AppHTTPErrorCodes get(String cStyle) {
        return lookup.get(cStyle);
    }

    @Override
    public String toString() {
        return this.httpErrorCode;
    }

}
