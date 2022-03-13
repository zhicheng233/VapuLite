package gq.vapulite.Vapu.modules.Config;

import gq.vapulite.Vapu.Client;
import gq.vapulite.Vapu.ModuleType;
import gq.vapulite.Vapu.modules.Module;
import gq.vapulite.Vapu.utils.Helper;

import java.io.IOException;

import static org.lwjgl.input.Keyboard.KEY_X;

public class LoadConfig extends Module {
    public LoadConfig() {
        super("LoadConfig", KEY_X, ModuleType.Config,"Load your configs");
        Chinese="加载配置";
        NoToggle=true;
    }

    public void enable() {
        try {
            Client.LoadConfig();
        } catch (IOException e) {
            e.printStackTrace();
            state=false;
        }
        Helper.sendMessage("Configs Loaded.");
        state=false;
    }
}
