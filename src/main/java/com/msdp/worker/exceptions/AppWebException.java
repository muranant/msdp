/*******************************************************************************
 ******************************************************************************/
package com.msdp.worker.exceptions;

public class AppWebException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int statusCode = 500;
	
	private AppWebErrorCodes errorCode;
	
    public AppWebException(AppWebErrorCodes errorCode) {
        super(errorCode.value());        
    }
    
    public AppWebException(String message, String errorCode, int statusCode) {
        super(message);
        this.errorCode = AppWebErrorCodes.fromValue(errorCode);
        this.statusCode = statusCode;
    }

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public AppWebErrorCodes getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(AppWebErrorCodes errorCode) {
		this.errorCode = errorCode;
	}

}
