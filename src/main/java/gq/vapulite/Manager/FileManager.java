package gq.vapulite.Manager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import gq.vapulite.Vapu.Client;
import gq.vapulite.Vapu.modules.Module;
import gq.vapulite.Vapu.utils.Helper;

import java.io.*;

public class FileManager {

    private final File dir = new File(System.getenv("APPDATA"), Client.name);
    private final File modules = new File(dir, Client.config + ".json");
    private final Gson gson = new Gson();

    public FileManager() {
        dir.mkdirs();
    }

    public void saveModules() throws IOException {
        if(!modules.exists())
            modules.createNewFile();

        final JsonObject jsonObject = new JsonObject();

        for(final Module module : ModuleManager.getModules()) {
            final JsonObject moduleJson = new JsonObject();

            moduleJson.addProperty("state", module.getState());
            moduleJson.addProperty("key", module.getKey());

//            for(final Value value : module.getValues()) {
//                if(value.getObject() instanceof Number)
//                    moduleJson.addProperty(value.getValueName(), (Number) value.getObject());
//                else if(value.getObject() instanceof Boolean)
//                    moduleJson.addProperty(value.getValueName(), (Boolean) value.getObject());
//                else if(value.getObject() instanceof String)
//                    moduleJson.addProperty(value.getValueName(), (String) value.getObject());
//            }

            jsonObject.add(module.name, moduleJson);
        }

        final PrintWriter printWriter = new PrintWriter(modules);
        printWriter.println(gson.toJson(jsonObject));
        printWriter.flush();
        printWriter.close();
    }

    public void loadModules() throws IOException {
        if(!modules.exists()) {
            Helper.sendMessage("No Configs Found!");
            return;
        }

        final BufferedReader bufferedReader = new BufferedReader(new FileReader(modules));

        final JsonElement jsonElement = gson.fromJson(bufferedReader, JsonElement.class);

        if(jsonElement instanceof JsonNull)
            return;

        final JsonObject jsonObject = (JsonObject) jsonElement;

        for(final Module module : ModuleManager.getModules()) {
            if(!jsonObject.has(module.name))
                continue;

            final JsonElement moduleElement = jsonObject.get(module.getName());

            if(moduleElement instanceof JsonNull)
                continue;

            final JsonObject moduleJson = (JsonObject) moduleElement;

            if(moduleJson.has("state"))
                module.setState(moduleJson.get("state").getAsBoolean());
            if(moduleJson.has("key"))
                module.setKey(moduleJson.get("key").getAsInt());

//            for(final Value value : module.getValues()) {
//                if(!moduleJson.has(value.getValueName()))
//                    continue;
//
//                if(value.getObject() instanceof Float)
//                    value.setObject(moduleJson.get(value.getValueName()).getAsFloat());
//                else if(value.getObject() instanceof Double)
//                    value.setObject(moduleJson.get(value.getValueName()).getAsDouble());
//                else if(value.getObject() instanceof Integer)
//                    value.setObject(moduleJson.get(value.getValueName()).getAsInt());
//                else if(value.getObject() instanceof Long)
//                    value.setObject(moduleJson.get(value.getValueName()).getAsLong());
//                else if(value.getObject() instanceof Byte)
//                    value.setObject(moduleJson.get(value.getValueName()).getAsByte());
//                else if(value.getObject() instanceof Boolean)
//                    value.setObject(moduleJson.get(value.getValueName()).getAsBoolean());
//                else if(value.getObject() instanceof String)
//                    value.setObject(moduleJson.get(value.getValueName()).getAsString());
//            }
        }
    }
}
