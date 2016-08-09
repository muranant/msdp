/*******************************************************************************
 ******************************************************************************/
package com.msdp.worker.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

public class AppLogger extends Logger {
	
	private static AppLoggerFactory myFactory = new AppLoggerFactory();
	
	private static final String MDC_USERNAME ="username";
	private static final String MDC_OBJECT_INFO ="objinfo";
	private static final String APPENDERID_NOTSET_MSG = "Default";

	
	public AppLogger(String name) {
		super(name);
	}
	
	public static AppLogger getAppLogger(Class<?> clazz) { // parasoft-suppress OOP.AHSM "This is handled correctly"
		return (AppLogger) Logger.getLogger(clazz.getName(), myFactory);
	}

	/**
	 * Log as info Level
	 * 
	 * @param message
	 *            - Info message to log
	 */
	public void info(Object message) {
		super.info(getLocationInfo() + " : " + message); // parasoft-suppress BD.SECURITY.TDLOG "This is handled correctly"
	}

	/**
	 * Log as info Level
	 * 
	 * @param message
	 *            - Info message to log
	 * @param e
	 *            - Exception object
	 */
	public void info(Object message, Throwable e) {
		super.info(getLocationInfo() + " : " + message, e);
	}

	/**
	 * Log as warning level
	 * 
	 * @param message
	 *            - Warning message to log
	 */
	public void warn(Object message) {
		super.warn(getLocationInfo() + " : " + message);
	}

	/**
	 * Log as Error Level
	 * 
	 * @param message
	 *            - Error message to log
	 */
	public void error(Object message) {
		super.error(getLocationInfo() + " : " + message);
	}

	/**
	 * Log as Error Level
	 * 
	 * @param message
	 *            - Error message to log
	 * @param e
	 *            - Exception object
	 */
	public void error(Object message, Throwable e) {
		super.error(getLocationInfo() + " : " + message, e);
	}

	/**
	 * Log as debug level
	 * 
	 * @param message
	 *            - debug message to log
	 */
	public void debug(Object message) {
		super.debug(getLocationInfo() + " : " + message);
	}

	/**
	 * Log as debug level
	 * 
	 * @param message
	 *            - debug message to log
	 * @param e
	 *            - Exception object
	 */
	public void debug(Object message, Throwable e) {
		super.debug(getLocationInfo() + " : " + message, e);
	}

	private String getLocationInfo() {
		StackTraceElement thread = Thread.currentThread().getStackTrace()[3];
		return "(" + thread.getFileName() + ":" + thread.getLineNumber() + ") ";
	}

	/**
	 * Sets current appender Id in current thread
	 * 
	 * @param appenderId
	 *            - current Id to set
	 */
	public void setCurrentAppenderId(String appenderId) {
		if (appenderId == null || appenderId.isEmpty()) {
			appenderId = APPENDERID_NOTSET_MSG;
		}
		MDC.put(MDC_OBJECT_INFO, appenderId); // parasoft-suppress BD.SECURITY.TDLOG "This is handled correctly"
	}

	public boolean isErrorEnabled() {
		return true;
	}
	
	/**
	 * Sets current username in current thread
	 * @param value
	 */
	public void setMDC(String value) {
		if (value == null || value.isEmpty()) {
			value = "default-user";
		}
		MDC.put(MDC_USERNAME, value); // parasoft-suppress BD.SECURITY.TDLOG "This is handled correctly"
	}
	
}
