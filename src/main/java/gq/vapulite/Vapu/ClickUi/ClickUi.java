package gq.vapulite.Vapu.ClickUi;

import com.google.common.collect.Lists;
import gq.vapulite.Manager.ModuleManager;
import gq.vapulite.Vapu.ModuleType;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import java.io.IOException;
import java.util.ArrayList;

import static gq.vapulite.Vapu.utils.RenderUtil.reAlpha;

public class ClickUi extends GuiScreen {
    public static ArrayList<Window> windows = Lists.newArrayList();
    public static boolean binding = false;
    public double opacity = 0.0;
    public int scrollVelocity;
    private int scroll;

    public ClickUi() {
        updateScreen();
        if (windows.isEmpty()) {
            int x = 5;
            ModuleType[] arrmoduleType = ModuleType.values();
            int n = arrmoduleType.length;
            int n2 = 0;
            while (n2 < n) {
                ModuleType c = arrmoduleType[n2];
                windows.add(new Window(c, x, 5));
                x += 105;
                ++n2;
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.translate(0,scroll,0);
        mouseY-=scroll;
        this.opacity = this.opacity + 10.0 < 200.0 ? (this.opacity += 10.0) : 200.0;
        drawRect((int) 0.0, (int) 0.0, Display.getWidth(), Display.getHeight(), reAlpha(1, 0.3F));// ����
        ScaledResolution res = new ScaledResolution(this.mc);
        GlStateManager.pushMatrix();
        ScaledResolution scaledRes = new ScaledResolution(this.mc);
        float scale = (float) scaledRes.getScaleFactor() / (float) Math.pow(scaledRes.getScaleFactor(), 2.0);
        int finalMouseY = mouseY;
        windows.forEach(w -> w.render(mouseX, finalMouseY));

        int finalMouseY1 = mouseY;
        windows.forEach(w -> w.mouseScroll(mouseX, finalMouseY1, this.scrollVelocity));
        super.drawScreen(mouseX, mouseY, partialTicks);

        GlStateManager.popMatrix();
        if (Mouse.hasWheel()) {
            int wheel = Mouse.getDWheel();
            this.scrollVelocity = wheel < 0 ? -120 : (wheel > 0 ? 120 : 0);
            if(wheel<0){
                scroll-=15;
            } else if(wheel>0){
                scroll+=15;
                if(scroll>0){
                    scroll=0;
                }
            }
        }


    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        mouseY-=scroll;
        int finalMouseY = mouseY;
        windows.forEach(w -> w.click(mouseX, finalMouseY, mouseButton));
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == 1 && !binding) {
            this.mc.displayGuiScreen(null);
            return;
        }
        windows.forEach(w -> w.key(typedChar, keyCode));
    }

    public void initGui() {
        this.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
        //BlurUtil.blurAll(170, 170);
        super.initGui();
    }

    public void onGuiClosed() {
//        this.mc.entityRenderer.isShaderActive();
//        this.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/fxaa.json"));
        ModuleManager.getModule("ClickGui").setState(false);
    }

    public synchronized void sendToFront(Window window) {
        int panelIndex = 0;
        int i = 0;
        while (i < windows.size()) {
            if (windows.get(i) == window) {
                panelIndex = i;
                break;
            }
            ++i;
        }
        Window t = windows.get(windows.size() - 1);
        windows.set(windows.size() - 1, windows.get(panelIndex));
        windows.set(panelIndex, t);
    }
}
