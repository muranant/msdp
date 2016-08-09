/*******************************************************************************
 ******************************************************************************/
package com.msdp.worker.logger;

import org.apache.commons.lang.StringUtils;

public class AppTextUtils {
	
	public static AppLogger logger =
			AppLogger.getAppLogger(AppTextUtils.class);
	
	/**
	 * Removes Password filed and replace with asterisk,
	 * @param inputXmlString - Capi Xml Request Format String
	 * @return removed password and replace with asterisk symbol
	 */
	public static String removePasswordAndGet(String inputXmlString) {
		String startText="password='";
		String passwordReplaceText="password='*****'";
		String endText="'";
		String passwordStr="'";
		try{
		if(StringUtils.isNotBlank(inputXmlString)) {
 		int x= inputXmlString.indexOf(startText);
		x=x+startText.length();
		
		while(true){
			String ch=inputXmlString.charAt(x)+"";
			passwordStr = passwordStr+ch;
			if( ch.compareTo(endText)==0 || ch.compareTo(" ")==0){
				break;
			}
			x++;
		}
		if(startText.contains("'")) 
			startText = startText.replace("'", "");
		startText = startText+ passwordStr;
		inputXmlString = inputXmlString.replaceAll(startText,passwordReplaceText);
		}
		return inputXmlString;
		}catch (Exception e) {
			logger.error("Invalid login request XML", e);
			return "Invalid login request XML";
		}
	}

}
