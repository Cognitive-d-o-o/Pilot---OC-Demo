package com.cgt.backendComponent.helper;

import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 


public class CustomLogger {
	private static final Logger logger = LogManager.getLogger();
	private static final String TEMPLATE = "\n==============================" + "%s"
			+ "\n==============================";

	public static void formatLogMessage(String logLevel, UUID ID, String message) {
		String returnMessage = ID + " | " + message;
		logMessage(logLevel, returnMessage);
	}

	public static void formatLogMessage(String logLevel, UUID ID, String className, String methodName, String message) {
		String returnMessage = "" + "\nClass name -> " + className + "\nMethod name -> " + methodName + "\nID -> " + ID
				+ "\nMessage -> " + message;

		String finalMessage = String.format(TEMPLATE, returnMessage);

		logMessage(logLevel, finalMessage);
	}

	public static void formatLogMessage(String logLevel, UUID ID, String className, String methodName, String message,
			List<String> list) {
		StringBuilder sb = new StringBuilder();
		list.forEach(item -> {
			sb.append(item + "\n");
		});

		String returnMessage = "" + "\nClass name -> " + className + "\nMethod name -> " + methodName + "\nID -> " + ID
				+ "\nMessage -> " + message + "\n" + sb.toString();

		String finalMessage = String.format(TEMPLATE, returnMessage);

		logMessage(logLevel, finalMessage);
	}

	public static void formatLogMessage(String logLevel, UUID ID, String className, String methodName,
			String exceptionName, String message) {
		String returnMessage = "" + "\nClass name -> " + className + "\nMethod name -> " + methodName + "\nID -> " + ID
				+ "\nException name -> " + exceptionName + "\nMessage -> " + message;

		String finalMessage = String.format(TEMPLATE, returnMessage);

		logMessage(logLevel, finalMessage);
	}

	public static void formatLogMessage(String logLevel, UUID ID, String className, String methodName,
			String exceptionName, int statusCode, String response) {
		String returnMessage = "" + "\nClass name -> " + className + "\nMethod name -> " + methodName + "\nID -> " + ID
				+ "\nException name -> " + exceptionName + "\nStatus code -> " + statusCode + "\nResponse -> "
				+ response;

		String finalMessage = String.format(TEMPLATE, returnMessage);

		logMessage(logLevel, finalMessage);
	}

	public static void formatLogMessage(String logLevel, UUID ID, String className, String methodName,
			String exceptionName, Exception message) {
		String returnMessage = "" + "\nClass name -> " + className + "\nMethod name -> " + methodName + "\nID -> " + ID
				+ "\nException name -> " + exceptionName + "\nMessage -> " + message;

		String finalMessage = String.format(TEMPLATE, returnMessage);

		logMessage(logLevel, finalMessage);
	}

	public static void logMessage(String logLevel, String message) {
		switch (logLevel) {
		case "INFO":
			logger.info(message);
			break;
		case "DEBUG":
			logger.debug(message);
			break;
		case "ERROR":
			logger.error(message);
			break;
		case "FATAL":
			logger.fatal(message);
			break;
		default:
			logger.trace(message);
		}
	}
}
