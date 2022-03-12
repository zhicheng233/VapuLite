package gq.vapulite.Vapu.modules.render;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import gq.vapulite.Vapu.ModuleType;
import gq.vapulite.Vapu.modules.Module;

public class FullBlight extends Module {
    private float old;
    public FullBlight() {
        super("FullBlight", Keyboard.KEY_F, ModuleType.Render);
        Chinese="夜视";
    }

    @Override
    public void enable() {
        this.old = mc.gameSettings.gammaSetting;
        Minecraft.getMinecraft().gameSettings.gammaSetting = 300;
    }

    @Override
    public void disable() {
        Minecraft.getMinecraft().gameSettings.gammaSetting = this.old;
    }
}
