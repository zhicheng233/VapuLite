package gq.vapulite.utils;

import gq.vapulite.Vapu.modules.Module;
import gq.vapulite.Vapu.utils.TimerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

import java.awt.*;

public class Notification {
    public static Minecraft mc = Minecraft.getMinecraft();

    public boolean isClassicNotification;
    public String message;
    public String title;
    public String icon;
    public TimerUtil timer;
    public Type type;
    public long stayTime;
    Module module;
    private float animationX;
    private float animationY;
    private float width;
    private final float height;

    public Notification(String title, String message, Type type, long stayTime) {
        this(title, message, type, stayTime, null);
    }

    public Notification(String title, String message, Type type, long stayTime, Module module) {
        this.module = module;

        this.message = message;
        this.title = title;
            isClassicNotification = true;
            width = Math.max(mc.fontRendererObj.getStringWidth(message) + 30, 120);
        this.height = 22.0f;
        this.animationX = 140.0f;
        this.type = type;
        this.stayTime = stayTime;

        this.timer = new TimerUtil();
        timer.reset();
    }

    public void draw(float x, float offsetY) {
        float target = isFinished() ? width : 0;

        this.animationX = AnimationUtils.getAnimationState(this.animationX, target, (Math.max(10, (Math.abs(this.animationX - (target))) * 40) * 0.4f));

        if (animationY == 0) {
            animationY = offsetY;
        }

        animationY = AnimationUtils.getAnimationState(animationY, offsetY, (Math.max(10, (Math.abs(animationY - (offsetY))) * 40) * 0.3f));

        float x1 = x - width + this.animationX + (isClassicNotification ? 10 : 0);
        float x2 = x + animationX + (isClassicNotification ? 10 : 0);

        float y1 = animationY - 2;
        float y2 = y1 + height;

        //Color LOL
        int color = new Color(26, 26, 26, 200).getRGB();
        int fontColor = 0xff000000;
        switch (this.type) {
            case MODULE:
            case INFO:
                color = 0xff4286f5;
                fontColor = 0xff4286f5;
                break;

            case WARNING:
                color = 0xffefbc12;
                fontColor = 0xffefbc12;
                break;

            case ERROR:
                color = 0xfff04747;
                fontColor = 0xfff04747;
                break;

            case SUCCESS:
                color = 0xff72b55e;
                fontColor = 0xff23ad5c;
        }

            float width = 140.0f;
            float height = 25.0f;
            GuiRenderUtils.drawBorderedRect(x1 - 16, y1 - 15, width + 5, height, 0.5f, new Color(0, 0, 0, 150), new Color(200, 200, 200, 200));
            mc.fontRendererObj.drawString(this.message, (int) (x1 - 8), (int) (y1 - 7), new Color(255, 255, 255).getRGB());
    }

    public boolean shouldDelete() {
        return isFinished() && this.animationX == width;
    }

    public float getHeight() {
        return height;
    }

    private boolean isFinished() {
        return timer.delay(stayTime);
    }

    public void drawArrow(float left, float top, float right, float bottom, int color) {
        float shiet;
        if (left < right) {
            shiet = left;
            left = right;
            right = shiet;
        }
        if (top < bottom) {
            shiet = top;
            top = bottom;
            bottom = shiet;
        }
        float a = (float) (color >> 24 & 255) / 255.0F;
        float b = (float) (color >> 16 & 255) / 255.0F;
        float c = (float) (color >> 8 & 255) / 255.0F;
        float d = (float) (color & 255) / 255.0F;
        WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 1);
        GlStateManager.color(b, c, d, a);
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(left, bottom + 6f, 0.0D).endVertex();
        worldRenderer.pos(right, bottom, 0.0D).endVertex();
        worldRenderer.pos(right, top, 0.0D).endVertex();
        worldRenderer.pos(left, top - 6f, 0.0D).endVertex();
        Tessellator.getInstance().draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 2.0F);
    }

    public enum Type {
        INFO, WARNING, ERROR, SUCCESS, MODULE
    }

}
