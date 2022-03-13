package gq.vapulite.Manager;

import gq.vapulite.Vapu.ModuleType;
import gq.vapulite.Vapu.modules.Config.IGN;
import gq.vapulite.Vapu.modules.Config.LoadConfig;
import gq.vapulite.Vapu.modules.Config.SaveConfig;
import gq.vapulite.Vapu.modules.Config.Uninject;
import gq.vapulite.Vapu.modules.Module;
import gq.vapulite.Vapu.modules.movement.*;
import gq.vapulite.Vapu.modules.world.*;
import gq.vapulite.Vapu.modules.player.*;
import gq.vapulite.Vapu.modules.render.*;
import gq.vapulite.Vapu.modules.combat.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ModuleManager {

    static ArrayList<Module> Modules = new ArrayList<Module>();

    public static ArrayList<Module> getModules() {
        return Modules;
    }

    public ModuleManager() {

    }

    public static Module getModule(String name) {
        for (Module m : Modules) {
            if (m.getName().toLowerCase().equalsIgnoreCase(name.toLowerCase()))
                return m;
        }
        return null;
    }

    public static List<Module> getModulesInType(ModuleType t) {
        ArrayList<Module> output = new ArrayList<Module>();
        ArrayList<Module> module = new ArrayList<Module>();
        module.addAll(module);
        for (Module m : module) {
            if (m.getCategory() != t) continue;
            output.add(m);
        }
        output.sort(Comparator.comparingInt((Module o) -> Character.toLowerCase(o.getName().charAt(0))).thenComparingInt(o -> o.getName().charAt(0)));
        return output;
    }

    static {
        // 没Add的都是有问题的，不要add
        Modules.add(new AntiBot());
        Modules.add(new Sprint());
        Modules.add(new ClickGUI());
        Modules.add(new IGN());
        Modules.add(new StateMessage());
        Modules.add(new HUD());
        Modules.add(new FullBright());
        Modules.add(new AutoTools());
        Modules.add(new IQBooster());
        Modules.add(new AutoClicker());
        Modules.add(new FastPlace());
        Modules.add(new LoadConfig());
        Modules.add(new SaveConfig());
        Modules.add(new Aimbot());
        Modules.add(new Uninject());
        Modules.add(new InvMove());
        Modules.add(new Health());
        Modules.add(new Killaura());
        Modules.add(new BowAimBot());
        Modules.add(new NoFall());
        Modules.add(new MurderMystery());
        Modules.add(new FuckServer());
        Modules.add(new Reach());
        Modules.add(new StorageESP());
        Modules.add(new ESP());
    }
}
