package gq.vapulite.Vapu.modules;

import gq.vapulite.Vapu.ModuleType;
import org.lwjgl.input.Keyboard;

public class ExampleModule extends Module {
    public ExampleModule() {
        super("ExampleModule", Keyboard.KEY_NONE, ModuleType.Config);
        Chinese="ExampleModule";
    }


}
