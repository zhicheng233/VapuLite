package gq.vapulite.Vapu.modules.Config;

import gq.vapulite.Vapu.Client;
import gq.vapulite.Vapu.ModuleType;
import gq.vapulite.Vapu.modules.Module;
import gq.vapulite.Manager.ModuleManager;

import java.util.ArrayList;

import static org.lwjgl.input.Keyboard.KEY_NONE;

public class Uninject extends Module {
    public Uninject() {
        super("Uninject", KEY_NONE, ModuleType.Config,"Uninject "+Client.name);
        Chinese="卸载";
        NoToggle=true;
    }

    public void enable() {
        this.mc.thePlayer.closeScreen();
        ArrayList<Module> modules = new ArrayList<>(ModuleManager.getModules());
        for (Module m : modules) {
            if (m != null) {
                m.setState(false);
            }
        }
        Client.unInject();
        state=false;
    }
}
