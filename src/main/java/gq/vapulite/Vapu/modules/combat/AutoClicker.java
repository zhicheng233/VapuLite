package gq.vapulite.Vapu.modules.combat;

import gq.vapulite.Vapu.ModuleType;
import gq.vapulite.Vapu.utils.TimerUtil;
import gq.vapulite.Vapu.value.Numbers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import gq.vapulite.Vapu.modules.Module;

import java.util.concurrent.ThreadLocalRandom;

public class AutoClicker extends Module {
    private final TimerUtil timer = new TimerUtil();
    private Numbers<Double> cps = new Numbers<Double>("CPS", "Cps",5.0, 5.0, 20.0,1.0);
    public AutoClicker() {
        super("AutoClicker", Keyboard.KEY_K, ModuleType.Combat,"auto Attack when you hold the attack button");
        this.addValues(this.cps);
        Chinese="连点器";
    }

    @SubscribeEvent
    public void onTick(TickEvent event) {
        int key = mc.gameSettings.keyBindAttack.getKeyCode();

        if (mc.gameSettings.keyBindAttack.isKeyDown()) {
            if (timer.delay(1000 / ThreadLocalRandom.current().nextInt((int) 4, (int) this.cps.getValue().intValue()))) {
                mc.thePlayer.swingItem();
                KeyBinding.onTick(key);
                if(Minecraft.getMinecraft().objectMouseOver.entityHit != null) {
                    mc.playerController.attackEntity(mc.thePlayer, Minecraft.getMinecraft().objectMouseOver.entityHit);
//                        if(Client.AutoBlock){
//                            mc.thePlayer.getHeldItem().useItemRightClick(mc.theWorld, mc.thePlayer);
//                        }
                }
                timer.reset();
            }
        }
    }
}
