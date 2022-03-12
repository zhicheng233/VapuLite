package gq.vapulite.Vapu.modules.movement;

import gq.vapulite.Vapu.ModuleType;
import gq.vapulite.Vapu.modules.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class Speed extends Module {
    public Speed() {
        super("Speed", Keyboard.KEY_G, ModuleType.Movement);
        Chinese="加速";
    }

    @SubscribeEvent
    public void onUpdate(TickEvent.PlayerTickEvent event) {
        if(getState()) {
            if(mc.thePlayer.onGround)
                mc.thePlayer.jump();
            else
                mc.thePlayer.motionY = -1;
        }
    }
}
