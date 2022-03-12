package gq.vapulite.utils;

import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IChatComponent;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

public class Tool {
	public static void displayChatMessage(String message) {
//		logerror(Tool.class.getClassLoader().toString());
		Minecraft mc = Minecraft.getMinecraft();
		if (mc.thePlayer == null) {
			log("(MCChat)" + message);
			return;
		}

		final JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("text", message);

		mc.thePlayer.addChatMessage(IChatComponent.Serializer.jsonToComponent(jsonObject.toString()));
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
