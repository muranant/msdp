/*******************************************************************************
 ******************************************************************************/
package com.msdp.worker.exceptions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Exception Response in XML to be sent to REST API Request if an error has occurred in
 * Handler or Task
 * It will be return CAPI or Provider ErrorCode , HttpErrorCode or Http Status code to be send to client
 * and Error Message
 * 
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "ExceptionResponse")
public class AppExceptionResponse {

    @XmlAttribute(name = "errorCode")
    private String errorCode;

    @XmlAttribute(name = "httpErrorCode")
    private String httpErrorCode;

    @XmlAttribute(name = "errorMessage")
    private String errorMessage;

    /**
     * return the CAPI or Provider specific ErrorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the CAPI or Provider specific ErrorCode
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * return the Provider specific Error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the Provider specific Error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * return the CAPI or Provider specific Error Code
     */
    public String getHttpErrorCode() {
        return httpErrorCode;
    }

    /**
     * Sets the CAPI or Provider specific Error Code
     */
    public void setHttpErrorCode(String httpErrorCode) {
        this.httpErrorCode = httpErrorCode;
    }

}
