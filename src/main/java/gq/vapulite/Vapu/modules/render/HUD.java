package gq.vapulite.Vapu.modules.render;

import gq.vapulite.Vapu.ModuleType;
import gq.vapulite.Vapu.utils.ColorUtils;
import gq.vapulite.Vapu.Client;
import gq.vapulite.Vapu.modules.Module;
import gq.vapulite.font.FontLoaders;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Comparator;

public class HUD extends Module {
    public HUD() {
        super("HUD", Keyboard.KEY_H, ModuleType.Render);
        Chinese="HUD界面";
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Text event) {
        ScaledResolution s = new ScaledResolution(mc);
        int width = new ScaledResolution(mc).getScaledWidth();
        int height = new ScaledResolution(mc).getScaledHeight();
        int y = 1;
        if (mc.currentScreen != null && !(mc.currentScreen instanceof GuiMainMenu)) return;
        FontLoaders.C18.drawStringWithShadow(Client.name,2,2, ColorUtils.rainbow(1));
        ArrayList<Module> modules = new ArrayList<>();
        for (Module m : Client.instance.moduleManager.getModules()) {
            modules.add(m);
        }
        modules.sort(new Comparator<Module>() {
            @Override
            public int compare(Module o1, Module o2) {

                return FontLoaders.C18.getStringWidth(o2.getName()) - FontLoaders.C16.getStringWidth(o1.getName());
            }
        });
        int i = 0;
        for (Module m : modules) {
            if (m != null) {
                if (m != null && m.getState()) {
                    int moduleWidth = FontLoaders.C18.getStringWidth(m.name);
                    if(Client.CHINESE){

                        FontLoaders.C18.drawString(m.Chinese, width - moduleWidth - 1, y, ColorUtils.rainbow(2)+i);
                    } else {

                        FontLoaders.C18.drawString(m.name, width - moduleWidth - 1, y, ColorUtils.rainbow(2)+i);
                    }
                    y += FontLoaders.C18.getHeight();
                }
            }
        }
    }

}
