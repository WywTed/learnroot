package com.huhusky.common.utils.http;

import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResponseHandler {

	private final static Logger LOGGER = Logger.getLogger(ResponseHandler.class);

	public static void outputJson(HttpServletResponse response, String content) {
		try {
			LOGGER.debug(content);
			response.setContentType(ContentType.JSON);
			response.getWriter().write(content);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void output(HttpServletResponse response, String content, boolean allowRemoteDomain) {
		try {
			LOGGER.info(content);
			if (allowRemoteDomain) {
				allowRemoteDomain(response);
			}
			response.setContentType(ContentType.JSON);
			response.getWriter().write(content);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void outputString(HttpServletResponse response, String content) {
		try {
			LOGGER.debug(content);
			response.setContentType(ContentType.TEXT_HTML);
			response.getWriter().write(content);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void output(HttpServletResponse response, String content) {
		try {
			LOGGER.debug(content);
			response.setContentType(ContentType.JSON);
			response.getWriter().write(content);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void outputImage(HttpServletResponse response, BufferedImage image, boolean noCache) {
		try {
			if (noCache) {
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
			}
			ImageIO.write(image, "JPEG", response.getOutputStream());
			response.getOutputStream().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendError(HttpServletResponse response, int code) {
		try {
			response.sendError(code);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void allowRemoteDomain(HttpServletResponse response) {
		LOGGER.info("allow origin *");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST,OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
	}

}
