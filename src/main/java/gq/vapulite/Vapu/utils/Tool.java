package gq.vapulite.Vapu.utils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

public class Tool {
	public static void displayChatMessage(String message) {
		Helper.sendMessage(message);
	}
	
	public static void log(String s, Object... args) {
		LogManager.getLogger().log(Level.INFO, String.format(s, args));
	}
	
	public static void logerror(String s, Object... args) {
		LogManager.getLogger().log(Level.ERROR, String.format(s, args));
	}
	
	public static void logException(Throwable e) {
		logerror(ExceptionUtils.getStackTrace(e));
	}
}
