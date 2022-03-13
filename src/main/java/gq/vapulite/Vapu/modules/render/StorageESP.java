package gq.vapulite.Vapu.modules.render;

import gq.vapulite.Vapu.ModuleType;
import gq.vapulite.Vapu.modules.Module;
import gq.vapulite.Vapu.utils.ColorUtil;
import gq.vapulite.Vapu.value.Option;
import gq.vapulite.render.ColorUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Timer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.lang.reflect.Field;

public class StorageESP extends Module {
    private Option<Boolean> Chest = new Option<Boolean>("Chest","Chest", true);
    private Option<Boolean> EnderChest = new Option<Boolean>("EnderChest","EnderChest", false);
    public StorageESP() {
        super("StorageESP", Keyboard.KEY_NONE, ModuleType.Render,"Chest Renderer ESP");
        this.addValues(this.Chest,this.EnderChest);
        Chinese="ExampleModule";
    }

    @SubscribeEvent
    public void o(final RenderWorldLastEvent ev) {
        for (final TileEntity te : mc.theWorld.loadedTileEntityList) {
            if (te instanceof TileEntityChest && this.Chest.getValue()) {
                int rgb = 0;
                rgb = ColorUtil.getRainbow().getRGB();
                StorageESP.re(te.getPos(), rgb);
            }
            if(te instanceof TileEntityEnderChest && this.EnderChest.getValue()){
                int rgb = 0;
                rgb = ColorUtil.getRainbow().getRGB();
                StorageESP.re(te.getPos(), rgb);
            }
        }
    }

    private static Minecraft mc;

    public static void re(final BlockPos bp, final int color) {
        if (bp == null) {
            return;
        }
        final double x = bp.getX() - StorageESP.mc.getRenderManager().viewerPosX;
        final double y = bp.getY() - StorageESP.mc.getRenderManager().viewerPosY;
        final double z = bp.getZ() - StorageESP.mc.getRenderManager().viewerPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        final float a = (color >> 24 & 0xFF) / 255.0f;
        final float r = (color >> 16 & 0xFF) / 255.0f;
        final float g = (color >> 8 & 0xFF) / 255.0f;
        final float b = (color & 0xFF) / 255.0f;
        GL11.glColor4d((double)r, (double)g, (double)b, (double)a);
        RenderGlobal.drawSelectionBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0));
        dbb(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), r, g, b);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }
    public static Timer gt() {
        try {
            final Class<Minecraft> c = Minecraft.class;
            final Field f = c.getDeclaredField(new String(new char[] { 't', 'i', 'm', 'e', 'r' }));
            f.setAccessible(true);
            return (Timer)f.get(mc);
        }
        catch (Exception er) {
            try {
                final Class<Minecraft> c2 = Minecraft.class;
                final Field f2 = c2.getDeclaredField(new String(new char[] { 'f', 'i', 'e', 'l', 'd', '_', '7', '1', '4', '2', '8', '_', 'T' }));
                f2.setAccessible(true);
                return (Timer)f2.get(mc);
            }
            catch (Exception er2) {
                return null;
            }
        }
    }

    public static void ee(final Entity e, int color, final boolean damage, final int type) {
        if (e == null) {
            return;
        }
        final double x = e.lastTickPosX + (e.posX - e.lastTickPosX) * gt().renderPartialTicks - StorageESP.mc.getRenderManager().viewerPosX;
        final double y = e.lastTickPosY + (e.posY - e.lastTickPosY) * gt().renderPartialTicks - StorageESP.mc.getRenderManager().viewerPosY;
        final double z = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * gt().renderPartialTicks - StorageESP.mc.getRenderManager().viewerPosZ;
        if (e instanceof EntityPlayer && damage && ((EntityPlayer)e).hurtTime != 0) {
            color = Color.RED.getRGB();
        }
        final float a = (color >> 24 & 0xFF) / 255.0f;
        final float r = (color >> 16 & 0xFF) / 255.0f;
        final float g = (color >> 8 & 0xFF) / 255.0f;
        final float b = (color & 0xFF) / 255.0f;
        if (type == 1) {
            GlStateManager.pushMatrix();
            GL11.glBlendFunc(770, 771);
            GL11.glEnable(3042);
            GL11.glDisable(3553);
            GL11.glDisable(2929);
            GL11.glDepthMask(false);
            GL11.glLineWidth(3.0f);
            GL11.glColor4f(r, g, b, a);
            RenderGlobal.drawSelectionBoundingBox(new AxisAlignedBB(e.getEntityBoundingBox().minX - 0.05 - e.posX + (e.posX - StorageESP.mc.getRenderManager().viewerPosX), e.getEntityBoundingBox().minY - e.posY + (e.posY - StorageESP.mc.getRenderManager().viewerPosY), e.getEntityBoundingBox().minZ - 0.05 - e.posZ + (e.posZ - StorageESP.mc.getRenderManager().viewerPosZ), e.getEntityBoundingBox().maxX + 0.05 - e.posX + (e.posX - StorageESP.mc.getRenderManager().viewerPosX), e.getEntityBoundingBox().maxY + 0.1 - e.posY + (e.posY - StorageESP.mc.getRenderManager().viewerPosY), e.getEntityBoundingBox().maxZ + 0.05 - e.posZ + (e.posZ - StorageESP.mc.getRenderManager().viewerPosZ)));
            dbb(new AxisAlignedBB(e.getEntityBoundingBox().minX - 0.05 - e.posX + (e.posX - StorageESP.mc.getRenderManager().viewerPosX), e.getEntityBoundingBox().minY - e.posY + (e.posY - StorageESP.mc.getRenderManager().viewerPosY), e.getEntityBoundingBox().minZ - 0.05 - e.posZ + (e.posZ - StorageESP.mc.getRenderManager().viewerPosZ), e.getEntityBoundingBox().maxX + 0.05 - e.posX + (e.posX - StorageESP.mc.getRenderManager().viewerPosX), e.getEntityBoundingBox().maxY + 0.1 - e.posY + (e.posY - StorageESP.mc.getRenderManager().viewerPosY), e.getEntityBoundingBox().maxZ + 0.05 - e.posZ + (e.posZ - StorageESP.mc.getRenderManager().viewerPosZ)), r, g, b);
            GL11.glEnable(3553);
            GL11.glEnable(2929);
            GL11.glDepthMask(true);
            GL11.glDisable(3042);
            GlStateManager.popMatrix();
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        }
        else if (type == 2 || type == 3) {
            final boolean mode = type == 2;
            GL11.glBlendFunc(770, 771);
            GL11.glEnable(3042);
            GL11.glLineWidth(3.0f);
            GL11.glDisable(3553);
            GL11.glDisable(2929);
            GL11.glDepthMask(false);
            GL11.glColor4d((double)r, (double)g, (double)b, (double)a);
            if (mode) {
                RenderGlobal.drawSelectionBoundingBox(new AxisAlignedBB(e.getEntityBoundingBox().minX - 0.05 - e.posX + (e.posX - StorageESP.mc.getRenderManager().viewerPosX), e.getEntityBoundingBox().minY - e.posY + (e.posY - StorageESP.mc.getRenderManager().viewerPosY), e.getEntityBoundingBox().minZ - 0.05 - e.posZ + (e.posZ - StorageESP.mc.getRenderManager().viewerPosZ), e.getEntityBoundingBox().maxX + 0.05 - e.posX + (e.posX - StorageESP.mc.getRenderManager().viewerPosX), e.getEntityBoundingBox().maxY + 0.1 - e.posY + (e.posY - StorageESP.mc.getRenderManager().viewerPosY), e.getEntityBoundingBox().maxZ + 0.05 - e.posZ + (e.posZ - StorageESP.mc.getRenderManager().viewerPosZ)));
            }
            else {
                dbb(new AxisAlignedBB(e.getEntityBoundingBox().minX - 0.05 - e.posX + (e.posX - StorageESP.mc.getRenderManager().viewerPosX), e.getEntityBoundingBox().minY - e.posY + (e.posY - StorageESP.mc.getRenderManager().viewerPosY), e.getEntityBoundingBox().minZ - 0.05 - e.posZ + (e.posZ - StorageESP.mc.getRenderManager().viewerPosZ), e.getEntityBoundingBox().maxX + 0.05 - e.posX + (e.posX - StorageESP.mc.getRenderManager().viewerPosX), e.getEntityBoundingBox().maxY + 0.1 - e.posY + (e.posY - StorageESP.mc.getRenderManager().viewerPosY), e.getEntityBoundingBox().maxZ + 0.05 - e.posZ + (e.posZ - StorageESP.mc.getRenderManager().viewerPosZ)), r, g, b);
            }
            GL11.glEnable(3553);
            GL11.glEnable(2929);
            GL11.glDepthMask(true);
            GL11.glDisable(3042);
        }
        else if (type == 4) {
            GL11.glPushMatrix();
            GL11.glTranslated(x, y - 0.2, z);
            GL11.glScalef(0.03f, 0.03f, 0.03f);
            GL11.glRotated((double)(-StorageESP.mc.getRenderManager().playerViewY), 0.0, 1.0, 0.0);
            GlStateManager.disableDepth();
            Gui.drawRect(-20, -1, -26, 75, Color.black.getRGB());
            Gui.drawRect(-21, 0, -25, 74, color);
            Gui.drawRect(20, -1, 26, 75, Color.black.getRGB());
            Gui.drawRect(21, 0, 25, 74, color);
            Gui.drawRect(-20, -1, 21, 5, Color.black.getRGB());
            Gui.drawRect(-21, 0, 24, 4, color);
            Gui.drawRect(-20, 70, 21, 75, Color.black.getRGB());
            Gui.drawRect(-21, 71, 25, 74, color);
            GlStateManager.enableDepth();
            GL11.glPopMatrix();
        }
    }

    public static void dbb(final AxisAlignedBB abb, final float r, final float g, final float b) {
        final float a = 0.25f;
        final Tessellator ts = Tessellator.getInstance();
        final WorldRenderer vb = ts.getWorldRenderer();
        vb.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vb.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vb.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vb.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vb.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vb.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vb.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        ts.draw();
    }

    public static void dtl(final Entity e, final int color, final float lw) {
        if (e == null) {
            return;
        }
        final double x = e.lastTickPosX + (e.posX - e.lastTickPosX) * gt().renderPartialTicks - StorageESP.mc.getRenderManager().viewerPosX;
        final double y = e.getEyeHeight() + e.lastTickPosY + (e.posY - e.lastTickPosY) * gt().renderPartialTicks - StorageESP.mc.getRenderManager().viewerPosY;
        final double z = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * gt().renderPartialTicks - StorageESP.mc.getRenderManager().viewerPosZ;
        final float a = (color >> 24 & 0xFF) / 255.0f;
        final float r = (color >> 16 & 0xFF) / 255.0f;
        final float g = (color >> 8 & 0xFF) / 255.0f;
        final float b = (color & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(lw);
        GL11.glColor4f(r, g, b, a);
        GL11.glBegin(2);
        GL11.glVertex3d(0.0, 0.0 + StorageESP.mc.thePlayer.getEyeHeight(), 0.0);
        GL11.glVertex3d(x, y, z);
        GL11.glEnd();
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    static {
        StorageESP.mc = Minecraft.getMinecraft();
    }
}
