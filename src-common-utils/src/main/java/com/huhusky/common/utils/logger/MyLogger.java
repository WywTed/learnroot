package com.huhusky.common.utils.logger;

import org.apache.log4j.Logger;

public class MyLogger {

	public static void AccessLogger(Logger logger, String funcName) {
		logger.info("access function ==>> " + funcName);
	}

}
