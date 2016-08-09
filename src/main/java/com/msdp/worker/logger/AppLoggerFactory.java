/*******************************************************************************
 ******************************************************************************/
package com.msdp.worker.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

public class AppLoggerFactory implements LoggerFactory {

	public AppLoggerFactory() {
	}

	public Logger makeNewLoggerInstance(String name) {
		return new AppLogger(name);
	}
}
