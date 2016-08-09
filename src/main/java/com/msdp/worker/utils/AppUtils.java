/*******************************************************************************
 ******************************************************************************/
package com.msdp.worker.utils;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.UUID;


/**
 * This class defines common methods which we can use in several classes to
 * generate unique ids
 * 
 * @version 1.0
 * @since 2014-05-28
 */
public class AppUtils {

	private static Logger logger = Logger.getLogger(AppUtils.class);
	/**
	 * This method returns unique id which we can for image, template , server,
	 * etc.,
	 * 
	 * @return String unique id by combination of chars, numbers and - .
	 */
	public static String generateUUID() {
		return CapiRandomUuidGenerator.getInstance().createUUID();
	}
	
	/**
	 * This method generates a unique key, which will be used for session handling
	 * @return unique key
	 */
	public static String generateKey() {
	    String uuid = UUID.randomUUID().toString();
	    uuid = uuid.replace("-", "");
	
	    return uuid.toUpperCase();
	}

	/*
	 * Method for creating directory
	 */

	public static boolean createDirectory(String directoryName) throws Exception{

		boolean result = false;
		File files = new File(directoryName);
		if (!files.exists()) {
			if (files.mkdirs()) {
				result = true;
				logger.info(" directories are created!");
			} else {
				logger.info("Failed to create directories!");
			}
		}else{
			result=true;
		}
		return result;
	}
	public static boolean removeDirectory(String directoryName) throws Exception{

		File files = new File(directoryName);
		if (files.exists()) {
			FileUtils.deleteDirectory(files);
		}
		return true;
	}
}
