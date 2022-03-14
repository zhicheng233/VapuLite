//TODO: NoCommand
package gq.vapulite.Vapu.modules.other;

import gq.vapulite.Vapu.ModuleType;
import gq.vapulite.Vapu.modules.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class NoCommand extends Module {
    public NoCommand() {
        super("NoCommand", Keyboard.KEY_NONE, ModuleType.Other, "No command.");
        Chinese=("没有指令");
    }

}
