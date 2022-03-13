package gq.vapulite.Vapu.modules.movement;

import gq.vapulite.Vapu.ModuleType;
import gq.vapulite.Vapu.modules.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class Spider extends Module {
    public Spider() {
        super("Spider", Keyboard.KEY_NONE, ModuleType.Movement,"");
        Chinese="蜘蛛侠";
    }
    @SubscribeEvent
    public void onTick(TickEvent event) {
        if (!mc.thePlayer.isOnLadder() && mc.thePlayer.motionY < 0.2) {
            mc.thePlayer.motionY = 0.2;
        }
    }
}
