package gq.vapulite.Vapu.modules.Config;

import gq.vapulite.Vapu.Client;
import gq.vapulite.Vapu.ModuleType;
import gq.vapulite.Vapu.modules.Module;
import gq.vapulite.Vapu.utils.Helper;

import java.io.IOException;

import static org.lwjgl.input.Keyboard.KEY_N;

public class SaveConfig extends Module {
    public SaveConfig() {
        super("SaveConfig", KEY_N, ModuleType.Config,"Save your module setting(config)");
        Chinese="保存配置";
        NoToggle=true;
    }

    public void enable() {
        try {
            Client.SaveConfig();
        } catch (IOException e) {
            e.printStackTrace();
            state=false;
        }
        Helper.sendMessage("Configs Saved.");
        state=false;
    }
}
