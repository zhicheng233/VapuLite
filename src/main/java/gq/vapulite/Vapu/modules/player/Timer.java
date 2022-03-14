//TODO: Timer
package gq.vapulite.Vapu.modules.player;

import gq.vapulite.Vapu.ModuleType;
import gq.vapulite.Vapu.modules.Module;
import gq.vapulite.Vapu.value.Numbers;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class Timer extends Module {
    public Timer (){
        super("Timer", Keyboard.KEY_NONE, ModuleType.Player, "Make world quickly");
        Chinese=("变速齿轮");
    }

    private Numbers<Double> timer = new Numbers<Double>("Speed", "Speed",1.0, 1.0, 10.0,1.0);

    @SubscribeEvent
    public void onUpdate(TickEvent.PlayerTickEvent event) {
        if (mc.thePlayer == null) ;
        return;

//        mc.timer.timerSpeed = 1.0;
    }

    @SubscribeEvent
    public void onDisable() {
//        mc.timer.timerSpeed = 1.0;
    }

}
