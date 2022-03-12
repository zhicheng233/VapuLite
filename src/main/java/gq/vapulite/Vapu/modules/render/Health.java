package gq.vapulite.Vapu.modules.render;

import gq.vapulite.Vapu.Client;
import gq.vapulite.Vapu.ModuleType;
import gq.vapulite.Vapu.modules.Module;
import gq.vapulite.Vapu.utils.ColorUtils;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Comparator;

public class Health extends Module {
    int fuck = 0;
    private int width;
    public Health() {
        super("Health", Keyboard.KEY_NONE, ModuleType.Render);
        Chinese="血量显示";
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Text event) {
        if (mc.thePlayer.getHealth() >= 0.0f && mc.thePlayer.getHealth() < 10.0f) {
            this.width = 3;
        }
        if (mc.thePlayer.getHealth() >= 10.0f && mc.thePlayer.getHealth() < 100.0f) {
            this.width = 5;
        }
        mc.fontRendererObj.drawStringWithShadow("♥" + MathHelper.ceiling_float_int(mc.thePlayer.getHealth()), (float) (new ScaledResolution(mc).getScaledWidth() / 2 - this.width), (float) (new ScaledResolution(mc).getScaledHeight() / 2 - 15), -1);
    }

}
