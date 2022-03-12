package gq.vapulite.Vapu.modules.movement;

import gq.vapulite.Vapu.ModuleType;
import gq.vapulite.Vapu.modules.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import gq.vapulite.Vapu.Value;

public class NoSlowDown extends Module {
    private Value<Float> speedValue = new Value<>("Speed", 1.0F);
    public NoSlowDown() {
        super("NoSlowDown", Keyboard.KEY_R, ModuleType.Movement);
        Chinese="没有减速";
    }

    @SubscribeEvent
    public void onUpdate(TickEvent event) {
        mc.thePlayer.movementInput.moveStrafe = speedValue.getObject();
        mc.thePlayer.movementInput.moveForward = speedValue.getObject();
    }
}
