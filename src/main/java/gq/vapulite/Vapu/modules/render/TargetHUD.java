package gq.vapulite.Vapu.modules.render;

import gq.vapulite.Vapu.ModuleType;
import gq.vapulite.Vapu.modules.Module;
import gq.vapulite.Manager.ModuleManager;
import gq.vapulite.Vapu.modules.combat.Killaura;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class TargetHUD extends Module {
    public TargetHUD() {
        super("TargetHUD", Keyboard.KEY_NONE, ModuleType.Render,"");
        Chinese="目标HUD";
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Text event) {
        ScaledResolution sr = new ScaledResolution(mc);
        if (Killaura.target != null && ModuleManager.getModule("TargetHUD").getState()
                & ModuleManager.getModule("Killaura").getState()) {
            final String name = Killaura.target.getName() + " ";
            mc.fontRendererObj.drawStringWithShadow(name, (float) (sr.getScaledWidth() / 2) - (mc.fontRendererObj.getStringWidth(name) / 2),
                    (float) (sr.getScaledHeight() / 2 - 30), -1);
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/gui/icons.png"));
            int i = 0;
            while ((float) i < Killaura.target.getMaxHealth() / 2.0f) {
                Minecraft.getMinecraft().ingameGUI.drawTexturedModalRect((float) (sr.getScaledWidth() / 2)
                                - Killaura.target.getMaxHealth() / 2.0f * 10.0f / 2.0f + (float) (i * 10),
                        (float) (sr.getScaledHeight() / 2 - 16), 16, 0, 9, 9);
                ++i;
            }
            i = 0;
            while ((float) i < Killaura.target.getHealth() / 2.0f) {
                Minecraft.getMinecraft().ingameGUI.drawTexturedModalRect((float) (sr.getScaledWidth() / 2)
                                - Killaura.target.getMaxHealth() / 2.0f * 10.0f / 2.0f + (float) (i * 10),
                        (float) (sr.getScaledHeight() / 2 - 16), 52, 0, 9, 9);
                ++i;
            }
        }
    }

}
