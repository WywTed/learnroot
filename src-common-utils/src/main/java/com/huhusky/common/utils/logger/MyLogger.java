package com.huhusky.common.utils.logger;

import org.slf4j.Logger;

public class MyLogger {

	public static void AccessLogger(Logger logger, String funcName) {
		logger.info("access function ==>> " + funcName);
	}

}
