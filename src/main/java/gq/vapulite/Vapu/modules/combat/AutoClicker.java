package gq.vapulite.Vapu.modules.combat;

import gq.vapulite.Vapu.Client;
import gq.vapulite.Vapu.ModuleType;
import gq.vapulite.Vapu.utils.TimerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import gq.vapulite.Vapu.modules.Module;

import java.util.concurrent.ThreadLocalRandom;

public class AutoClicker extends Module {
    private final TimerUtil timer = new TimerUtil();
    public AutoClicker() {
        super("AutoClicker", Keyboard.KEY_K, ModuleType.Combat);
        Chinese="连点器";
    }

    @SubscribeEvent
    public void onTick(TickEvent event) {
        int key = mc.gameSettings.keyBindAttack.getKeyCode();

        if (mc.gameSettings.keyBindAttack.isKeyDown()) {
                if (timer.delay(1000 / ThreadLocalRandom.current().nextInt((int) 10, (int) 14 + 1))) {
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
