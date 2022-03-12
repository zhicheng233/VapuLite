package gq.vapulite.Vapu.gui;

import gq.vapulite.Vapu.modules.Module;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

import static gq.vapulite.Vapu.Client.moduleManager;

public class BindScreen extends GuiScreen {
    private final Module target;
//    private final GuiScreen parent;

    public BindScreen(Module module) {
        this.target = module;
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);

        if (keyCode == 1) {
            this.mc.thePlayer.closeScreen();
        }

        for(Module m : moduleManager.getModules()) {
            if(keyCode == m.key) {
                this.drawCenteredString(this.fontRendererObj, EnumChatFormatting.RED+"This key is already bind to"  + m.getName(), this.width / 2 - 20, 150, 0xFFFFFF);
            } else if(keyCode != 1) {
                this.target.setKey(keyCode);
                this.mc.thePlayer.closeScreen();

            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Listening, Press any key for bind " + EnumChatFormatting.YELLOW + target.getName(), this.width / 2, 150, 0xFFFFFF);
        this.drawCenteredString(this.fontRendererObj, "Using bind: "+Keyboard.getKeyName(target.getKey()), this.width / 2, 170, 0xFFFFFF);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
