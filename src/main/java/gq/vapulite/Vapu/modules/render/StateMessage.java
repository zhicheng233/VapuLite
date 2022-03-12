package gq.vapulite.Vapu.modules.render;

import org.lwjgl.input.Keyboard;
import gq.vapulite.Vapu.Client;
import gq.vapulite.Vapu.ModuleType;
import gq.vapulite.Vapu.modules.Module;

public class StateMessage extends Module {
    public StateMessage() {
        super("NoStateMessage", Keyboard.KEY_V, ModuleType.Render);
        Chinese="无开关信息";
    }

    public void enable() {
        Client.MessageON = true;
    }

    public void disable(){
        Client.MessageON = false;
    }
}
