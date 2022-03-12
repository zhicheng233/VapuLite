package gq.vapulite.Vapu.modules.combat;

import gq.vapulite.Vapu.Client;
import gq.vapulite.Vapu.ModuleType;
import gq.vapulite.Vapu.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

import static net.minecraft.realms.RealmsMth.sqrt;
import static net.minecraft.realms.RealmsMth.wrapDegrees;

public class AutoBlock extends Module {
    public AutoBlock() {
        super("AutoBlock", Keyboard.KEY_NONE, ModuleType.Combat);
        Chinese="自动格挡";
    }


    @Override
    public void enable(){
        Client.AutoBlock = true;
    }

    @Override
    public void disable(){
        Client.AutoBlock = false;
    }

}
