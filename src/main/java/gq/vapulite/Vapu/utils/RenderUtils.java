package gq.vapulite.Vapu.utils;

import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import org.lwjgl.opengl.GL11;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static gq.vapulite.Vapu.utils.Helper.mc;

public class RenderUtils {
    private static final AxisAlignedBB DEFAULT_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
    private static final Map<Integer, Boolean> glCapMap = new HashMap<>();
    public static TimerUtil splashTimer = new TimerUtil();
    public static int splashTickPos = 0;
    public static boolean isSplash = false;

    public static String DF(double value, int maxvalue) {
        DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(maxvalue);
        return df.format(value);
    }

//
//    public static void drawSplash(String text) {
//        ScaledResolution sr = new ScaledResolution(Wrapper.INSTANCE.mc());
//        RenderUtils.drawStringWithRect(text, sr.getScaledWidth() + 2 - splashTickPos, sr.getScaledHeight() - 10, ClickGui.getColor(), ColorUtils.color(0.0f, 0.0f, 0.0f, 0.0f), ColorUtils.color(0.0f, 0.0f, 0.0f, 0.5f));
//        if (splashTimer.isDelay(10L)) {
//            splashTimer.setLastMS();
//            if (isSplash) {
//                if (++splashTickPos == Wrapper.INSTANCE.fontRenderer().getStringWidth(text) + 10) {
//                    isSplash = false;
//                }
//            } else if (splashTickPos > 0) {
//                --splashTickPos;
//            }
//        }
//    }
//
//    public static void drawBorderedRect(double x, double y, double x2, double y2, float l1, int col1, int col2) {
//        RenderUtils.drawRect((int) x, (int) y, (int) x2, (int) y2, col2);
//        float f = (float) (col1 >> 24 & 0xFF) / 255.0f;
//        float f1 = (float) (col1 >> 16 & 0xFF) / 255.0f;
//        float f2 = (float) (col1 >> 8 & 0xFF) / 255.0f;
//        float f3 = (float) (col1 & 0xFF) / 255.0f;
//        GL11.glDisable(3553);
//        GL11.glBlendFunc(770, 771);
//        GL11.glEnable(2848);
//        GL11.glPushMatrix();
//        GL11.glColor4f(f1, f2, f3, f);
//        GL11.glLineWidth(l1);
//        GL11.glBegin(1);
//        GL11.glVertex2d(x, y);
//        GL11.glVertex2d(x, y2);
//        GL11.glVertex2d(x2, y2);
//        GL11.glVertex2d(x2, y);
//        GL11.glVertex2d(x, y);
//        GL11.glVertex2d(x2, y);
//        GL11.glVertex2d(x, y2);
//        GL11.glVertex2d(x2, y2);
//        GL11.glEnd();
//        GL11.glPopMatrix();
//        GL11.glEnable(3553);
//        GL11.glDisable(2848);
//    }
//
//    public static void drawTracer(Entity entity, float red, float green, float blue, float alpha, float ticks) {
//        double renderPosX = Wrapper.INSTANCE.mc().getRenderManager().viewerPosX;
//        double renderPosY = Wrapper.INSTANCE.mc().getRenderManager().viewerPosY;
//        double renderPosZ = Wrapper.INSTANCE.mc().getRenderManager().viewerPosZ;
//        double xPos = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double) ticks - renderPosX;
//        double yPos = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double) ticks + (double) (entity.height / 2.0f) - renderPosY;
//        double zPos = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double) ticks - renderPosZ;
//        GL11.glBlendFunc(770, 771);
//        GL11.glEnable(3042);
//        GL11.glEnable(2848);
//        GL11.glDisable(2896);
//        GL11.glLineWidth(1.0f);
//        GL11.glDisable(3553);
//        GL11.glDisable(2929);
//        GL11.glDepthMask(false);
//        GL11.glColor4f(red, green, blue, alpha);
//        Vec3d eyes = null;
//        eyes = KillAura.facingCam != null ? new Vec3d(0.0, 0.0, 1.0).rotatePitch(-((float) Math.toRadians(KillAura.facingCam[1]))).rotateYaw(-((float) Math.toRadians(KillAura.facingCam[0]))) : (Scaffold.facingCam != null ? new Vec3d(0.0, 0.0, 1.0).rotatePitch(-((float) Math.toRadians(Scaffold.facingCam[1]))).rotateYaw(-((float) Math.toRadians(Scaffold.facingCam[0]))) : new Vec3d(0.0, 0.0, 1.0).rotatePitch(-((float) Math.toRadians(Wrapper.INSTANCE.player().rotationPitch))).rotateYaw(-((float) Math.toRadians(Wrapper.INSTANCE.player().rotationYaw))));
//        GL11.glBegin(1);
//        GL11.glVertex3d(eyes.x, (double) Wrapper.INSTANCE.player().getEyeHeight() + eyes.y, eyes.z);
//        GL11.glVertex3d(xPos, yPos, zPos);
//        GL11.glEnd();
//        GL11.glEnable(3553);
//        GL11.glEnable(2929);
//        GL11.glEnable(2896);
//        GL11.glDepthMask(true);
//    }
//
//    public static void drawESP(Entity entity, float colorRed, float colorGreen, float colorBlue, float colorAlpha, float ticks) {
//        try {
//            double renderPosX = Wrapper.INSTANCE.mc().getRenderManager().viewerPosX;
//            double renderPosY = Wrapper.INSTANCE.mc().getRenderManager().viewerPosY;
//            double renderPosZ = Wrapper.INSTANCE.mc().getRenderManager().viewerPosZ;
//            double xPos = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double) ticks - renderPosX;
//            double yPos = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double) ticks + (double) (entity.height / 2.0f) - renderPosY;
//            double zPos = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double) ticks - renderPosZ;
//            float playerViewY = Wrapper.INSTANCE.mc().getRenderManager().playerViewY;
//            float playerViewX = Wrapper.INSTANCE.mc().getRenderManager().playerViewX;
//            boolean thirdPersonView = Wrapper.INSTANCE.mc().getRenderManager().options.thirdPersonView == 2;
//            GL11.glPushMatrix();
//            GlStateManager.translate(xPos, yPos, zPos);
//            GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
//            GlStateManager.rotate(-playerViewY, 0.0f, 1.0f, 0.0f);
//            GlStateManager.rotate((float) (thirdPersonView ? -1 : 1) * playerViewX, 1.0f, 0.0f, 0.0f);
//            GL11.glEnable(3042);
//            GL11.glDisable(3553);
//            GL11.glDisable(2896);
//            GL11.glDisable(2929);
//            GL11.glDepthMask(false);
//            GL11.glLineWidth(1.0f);
//            GL11.glBlendFunc(770, 771);
//            GL11.glEnable(2848);
//            GL11.glColor4f(colorRed, colorGreen, colorBlue, colorAlpha);
//            GL11.glBegin(1);
//            GL11.glVertex3d(0.0, 1.0, 0.0);
//            GL11.glVertex3d(-0.5, 0.5, 0.0);
//            GL11.glVertex3d(0.0, 1.0, 0.0);
//            GL11.glVertex3d(0.5, 0.5, 0.0);
//            GL11.glVertex3d(0.0, 0.0, 0.0);
//            GL11.glVertex3d(-0.5, 0.5, 0.0);
//            GL11.glVertex3d(0.0, 0.0, 0.0);
//            GL11.glVertex3d(0.5, 0.5, 0.0);
//            GL11.glEnd();
//            GL11.glDepthMask(true);
//            GL11.glEnable(2929);
//            GL11.glEnable(3553);
//            GL11.glEnable(2896);
//            GL11.glDisable(2848);
//            GL11.glDisable(3042);
//            GL11.glPopMatrix();
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//    }
//
//    public static void drawNukerBlocks(Iterable<BlockPos> blocks, float r, float g, float b, float ticks) {
//        GL11.glPushMatrix();
//        GL11.glEnable(3042);
//        GL11.glBlendFunc(770, 771);
//        GL11.glEnable(2848);
//        GL11.glLineWidth(1.0f);
//        GL11.glDisable(3553);
//        GL11.glEnable(2884);
//        GL11.glDisable(2929);
//        GL11.glDisable(2896);
//        WorldClient world = Wrapper.INSTANCE.world();
//        EntityPlayerSP player = Wrapper.INSTANCE.player();
//        for (BlockPos pos : blocks) {
//            IBlockState iblockstate = world.getBlockState(pos);
//            double x = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double) ticks;
//            double y = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double) ticks;
//            double z = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double) ticks;
//            GLUtils.glColor(new Color(r, g, b, 1.0f));
//            AxisAlignedBB boundingBox = iblockstate.getSelectedBoundingBox(world, pos).grow(0.002f).offset(-x, -y, -z);
//            RenderUtils.drawSelectionBoundingBox(boundingBox);
//        }
//        GL11.glEnable(2896);
//        GL11.glEnable(2929);
//        GL11.glEnable(3553);
//        GL11.glDisable(3042);
//        GL11.glDisable(2848);
//        GL11.glPopMatrix();
//    }
//
//    public static void drawXRayBlocks(LinkedList<XRayBlock> blocks, float ticks) {
//        GL11.glPushMatrix();
//        GL11.glEnable(3042);
//        GL11.glBlendFunc(770, 771);
//        GL11.glEnable(2848);
//        GL11.glLineWidth(1.0f);
//        GL11.glDisable(3553);
//        GL11.glEnable(2884);
//        GL11.glDisable(2929);
//        GL11.glDisable(2896);
//        WorldClient world = Wrapper.INSTANCE.world();
//        EntityPlayerSP player = Wrapper.INSTANCE.player();
//        for (XRayBlock block : blocks) {
//            BlockPos pos = block.getBlockPos();
//            XRayData data = block.getxRayData();
//            IBlockState iblockstate = world.getBlockState(pos);
//            double x = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double) ticks;
//            double y = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double) ticks;
//            double z = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double) ticks;
//            int color = new Color(data.getRed(), data.getGreen(), data.getBlue(), 255).getRGB();
//            GLUtils.glColor(color);
//            AxisAlignedBB boundingBox = iblockstate.getSelectedBoundingBox(world, pos).grow(0.002f).offset(-x, -y, -z);
//            RenderUtils.drawSelectionBoundingBox(boundingBox);
//        }
//        GL11.glEnable(2896);
//        GL11.glEnable(2929);
//        GL11.glEnable(3553);
//        GL11.glDisable(3042);
//        GL11.glDisable(2848);
//        GL11.glPopMatrix();
//    }

    public static void drawOutlinedEntityESP(double x, double y, double z, double width, double height, float red,
                                             float green, float blue, float alpha) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glLineWidth(1.5f);
        GL11.glColor4f(red, green, blue, alpha);
        drawOutlinedBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    public static void drawOutlinedBoundingBox(AxisAlignedBB aa) {
        Tessellator tessellator = Tessellator.getInstance();
        GlStateManager.alphaFunc(GL11.GL_GREATER, 0.05f);
        WorldRenderer bufferBuilder = tessellator.getWorldRenderer();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        bufferBuilder.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        bufferBuilder.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        bufferBuilder.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        bufferBuilder.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        tessellator.draw();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        bufferBuilder.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        bufferBuilder.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        bufferBuilder.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        bufferBuilder.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        tessellator.draw();
        bufferBuilder.begin(1, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        bufferBuilder.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        bufferBuilder.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        bufferBuilder.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        bufferBuilder.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        bufferBuilder.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        bufferBuilder.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        bufferBuilder.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        tessellator.draw();

    }

    public static void drawBlockESP(BlockPos pos, float red, float green, float blue) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glLineWidth(1.0f);
        GL11.glDisable(3553);
        GL11.glEnable(2884);
        GL11.glDisable(2929);
        GL11.glDisable(2896);
        double renderPosX = mc.getRenderManager().viewerPosX;
        double renderPosY = mc.getRenderManager().viewerPosY;
        double renderPosZ = mc.getRenderManager().viewerPosZ;
        GL11.glTranslated(-renderPosX, -renderPosY, -renderPosZ);
        GL11.glTranslated(pos.getX(), pos.getY(), pos.getZ());
        GL11.glColor4f(red, green, blue, 0.3f);
        RenderUtils.drawSolidBox();
        GL11.glColor4f(red, green, blue, 0.7f);
        RenderUtils.drawOutlinedBox();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(2896);
        GL11.glEnable(2929);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }
//
//    public static void drawSelectionBoundingBox(AxisAlignedBB boundingBox) {
//        Tessellator tessellator = Tessellator.getInstance();
//        BufferBuilder vertexbuffer = tessellator.getBuffer();
//        vertexbuffer.begin(3, DefaultVertexFormats.POSITION);
//        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).endVertex();
//        vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).endVertex();
//        vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).endVertex();
//        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).endVertex();
//        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).endVertex();
//        tessellator.draw();
//        vertexbuffer.begin(3, DefaultVertexFormats.POSITION);
//        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).endVertex();
//        vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).endVertex();
//        vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).endVertex();
//        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).endVertex();
//        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).endVertex();
//        tessellator.draw();
//        vertexbuffer.begin(1, DefaultVertexFormats.POSITION);
//        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).endVertex();
//        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).endVertex();
//        vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).endVertex();
//        vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).endVertex();
//        vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).endVertex();
//        vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).endVertex();
//        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).endVertex();
//        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).endVertex();
//        tessellator.draw();
//    }
//
//    public static void drawColorBox(AxisAlignedBB axisalignedbb, float red, float green, float blue, float alpha) {
//        Tessellator ts = Tessellator.getInstance();
//        BufferBuilder vb = ts.getBuffer();
//        vb.begin(7, DefaultVertexFormats.POSITION_TEX);
//        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
//        ts.draw();
//        vb.begin(7, DefaultVertexFormats.POSITION_TEX);
//        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
//        ts.draw();
//        vb.begin(7, DefaultVertexFormats.POSITION_TEX);
//        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
//        ts.draw();
//        vb.begin(7, DefaultVertexFormats.POSITION_TEX);
//        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
//        ts.draw();
//        vb.begin(7, DefaultVertexFormats.POSITION_TEX);
//        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
//        ts.draw();
//        vb.begin(7, DefaultVertexFormats.POSITION_TEX);
//        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
//        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
//        ts.draw();
//    }
//
    public static void drawSolidBox() {
        RenderUtils.drawSolidBox(DEFAULT_AABB);
    }

    public static void drawSolidBox(AxisAlignedBB bb) {
        GL11.glBegin(7);
        GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
        GL11.glEnd();
    }

    public static void drawOutlinedBox() {
        RenderUtils.drawOutlinedBox(DEFAULT_AABB);
    }

    public static void drawOutlinedBox(AxisAlignedBB bb) {
        GL11.glBegin(1);
        GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
        GL11.glEnd();
    }
//
//    public static void drawBoundingBox(AxisAlignedBB aa) {
//        Tessellator tessellator = Tessellator.getInstance();
//        BufferBuilder vertexBuffer = tessellator.getBuffer();
//        vertexBuffer.begin(3, DefaultVertexFormats.POSITION);
//        vertexBuffer.pos(aa.minX, aa.minY, aa.minZ);
//        vertexBuffer.pos(aa.minX, aa.maxY, aa.minZ);
//        vertexBuffer.pos(aa.maxX, aa.minY, aa.minZ);
//        vertexBuffer.pos(aa.maxX, aa.maxY, aa.minZ);
//        vertexBuffer.pos(aa.maxX, aa.minY, aa.maxZ);
//        vertexBuffer.pos(aa.maxX, aa.maxY, aa.maxZ);
//        vertexBuffer.pos(aa.minX, aa.minY, aa.maxZ);
//        vertexBuffer.pos(aa.minX, aa.maxY, aa.maxZ);
//        tessellator.draw();
//        vertexBuffer.begin(3, DefaultVertexFormats.POSITION);
//        vertexBuffer.pos(aa.maxX, aa.maxY, aa.minZ);
//        vertexBuffer.pos(aa.maxX, aa.minY, aa.minZ);
//        vertexBuffer.pos(aa.minX, aa.maxY, aa.minZ);
//        vertexBuffer.pos(aa.minX, aa.minY, aa.minZ);
//        vertexBuffer.pos(aa.minX, aa.maxY, aa.maxZ);
//        vertexBuffer.pos(aa.minX, aa.minY, aa.maxZ);
//        vertexBuffer.pos(aa.maxX, aa.maxY, aa.maxZ);
//        vertexBuffer.pos(aa.maxX, aa.minY, aa.maxZ);
//        tessellator.draw();
//        vertexBuffer.begin(3, DefaultVertexFormats.POSITION);
//        vertexBuffer.pos(aa.minX, aa.maxY, aa.minZ);
//        vertexBuffer.pos(aa.maxX, aa.maxY, aa.minZ);
//        vertexBuffer.pos(aa.maxX, aa.maxY, aa.maxZ);
//        vertexBuffer.pos(aa.minX, aa.maxY, aa.maxZ);
//        vertexBuffer.pos(aa.minX, aa.maxY, aa.minZ);
//        vertexBuffer.pos(aa.minX, aa.maxY, aa.maxZ);
//        vertexBuffer.pos(aa.maxX, aa.maxY, aa.maxZ);
//        vertexBuffer.pos(aa.maxX, aa.maxY, aa.minZ);
//        tessellator.draw();
//        vertexBuffer.begin(3, DefaultVertexFormats.POSITION);
//        vertexBuffer.pos(aa.minX, aa.minY, aa.minZ);
//        vertexBuffer.pos(aa.maxX, aa.minY, aa.minZ);
//        vertexBuffer.pos(aa.maxX, aa.minY, aa.maxZ);
//        vertexBuffer.pos(aa.minX, aa.minY, aa.maxZ);
//        vertexBuffer.pos(aa.minX, aa.minY, aa.minZ);
//        vertexBuffer.pos(aa.minX, aa.minY, aa.maxZ);
//        vertexBuffer.pos(aa.maxX, aa.minY, aa.maxZ);
//        vertexBuffer.pos(aa.maxX, aa.minY, aa.minZ);
//        tessellator.draw();
//        vertexBuffer.begin(3, DefaultVertexFormats.POSITION);
//        vertexBuffer.pos(aa.minX, aa.minY, aa.minZ);
//        vertexBuffer.pos(aa.minX, aa.maxY, aa.minZ);
//        vertexBuffer.pos(aa.minX, aa.minY, aa.maxZ);
//        vertexBuffer.pos(aa.minX, aa.maxY, aa.maxZ);
//        vertexBuffer.pos(aa.maxX, aa.minY, aa.maxZ);
//        vertexBuffer.pos(aa.maxX, aa.maxY, aa.maxZ);
//        vertexBuffer.pos(aa.maxX, aa.minY, aa.minZ);
//        vertexBuffer.pos(aa.maxX, aa.maxY, aa.minZ);
//        tessellator.draw();
//        vertexBuffer.begin(3, DefaultVertexFormats.POSITION);
//        vertexBuffer.pos(aa.minX, aa.maxY, aa.maxZ);
//        vertexBuffer.pos(aa.minX, aa.minY, aa.maxZ);
//        vertexBuffer.pos(aa.minX, aa.maxY, aa.minZ);
//        vertexBuffer.pos(aa.minX, aa.minY, aa.minZ);
//        vertexBuffer.pos(aa.maxX, aa.maxY, aa.minZ);
//        vertexBuffer.pos(aa.maxX, aa.minY, aa.minZ);
//        vertexBuffer.pos(aa.maxX, aa.maxY, aa.maxZ);
//        vertexBuffer.pos(aa.maxX, aa.minY, aa.maxZ);
//        tessellator.draw();
//    }
//
//    public static void drawOutlineBoundingBox(AxisAlignedBB boundingBox) {
//        Tessellator tessellator = Tessellator.getInstance();
//        BufferBuilder vertexbuffer = tessellator.getBuffer();
//        vertexbuffer.begin(3, DefaultVertexFormats.POSITION);
//        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).endVertex();
//        vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).endVertex();
//        vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).endVertex();
//        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).endVertex();
//        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).endVertex();
//        tessellator.draw();
//        vertexbuffer.begin(3, DefaultVertexFormats.POSITION);
//        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).endVertex();
//        vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).endVertex();
//        vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).endVertex();
//        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).endVertex();
//        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).endVertex();
//        tessellator.draw();
//        vertexbuffer.begin(1, DefaultVertexFormats.POSITION);
//        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).endVertex();
//        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).endVertex();
//        vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).endVertex();
//        vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).endVertex();
//        vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).endVertex();
//        vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).endVertex();
//        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).endVertex();
//        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).endVertex();
//        tessellator.draw();
//    }
//
//    public static void drawTri(double x1, double y1, double x2, double y2, double x3, double y3, double width, Color c) {
//        GL11.glEnable(3042);
//        GL11.glDisable(3553);
//        GL11.glEnable(2848);
//        GL11.glBlendFunc(770, 771);
//        GLUtils.glColor(c);
//        GL11.glLineWidth((float) width);
//        GL11.glBegin(3);
//        GL11.glVertex2d(x1, y1);
//        GL11.glVertex2d(x2, y2);
//        GL11.glVertex2d(x3, y3);
//        GL11.glEnd();
//        GL11.glDisable(2848);
//        GL11.glEnable(3553);
//    }
//
//    public static void drawHLine(float par1, float par2, float par3, int color) {
//        if (par2 < par1) {
//            float var5 = par1;
//            par1 = par2;
//            par2 = var5;
//        }
//        RenderUtils.drawRect(par1, par3, par2 + 1.0f, par3 + 1.0f, color);
//    }
//
//    /**
//     * Draws a solid color rectangle with the specified coordinates and color (ARGB format). Args: x1, y1, x2, y2, color
//     */
//    public static void drawRect(float left, float top, float right, float bottom, int color) {
//        if (left < right) {
//            float i = left;
//            left = right;
//            right = i;
//        }
//
//        if (top < bottom) {
//            float j = top;
//            top = bottom;
//            bottom = j;
//        }
//
//        float f3 = (float) (color >> 24 & 255) / 255.0F;
//        float f = (float) (color >> 16 & 255) / 255.0F;
//        float f1 = (float) (color >> 8 & 255) / 255.0F;
//        float f2 = (float) (color & 255) / 255.0F;
//        Tessellator tessellator = Tessellator.getInstance();
//        BufferBuilder worldrenderer = tessellator.getBuffer();
//        GlStateManager.enableBlend();
//        GlStateManager.disableTexture2D();
//        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
//        GlStateManager.color(f, f1, f2, f3);
//        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
//        worldrenderer.pos((double) left, (double) bottom, 0.0D).endVertex();
//        worldrenderer.pos((double) right, (double) bottom, 0.0D).endVertex();
//        worldrenderer.pos((double) right, (double) top, 0.0D).endVertex();
//        worldrenderer.pos((double) left, (double) top, 0.0D).endVertex();
//        tessellator.draw();
//        GlStateManager.enableTexture2D();
//        GlStateManager.disableBlend();
//    }
//
//    public static void drawVLine(float par1, float par2, float par3, int color) {
//        if (par3 < par2) {
//            float var5 = par2;
//            par2 = par3;
//            par3 = var5;
//        }
//        RenderUtils.drawRect(par1, par2 + 1.0f, par1 + 1.0f, par3, color);
//    }
//
//
//    public static void drawGradientSideways(double left, double top, double right, double bottom, int col1, int col2) {
//        GL11.glEnable(3042);
//        GL11.glDisable(3553);
//        GL11.glBlendFunc(770, 771);
//        GL11.glEnable(2848);
//        GL11.glShadeModel(7425);
//
//        quickDrawGradientSideways(left, top, right, bottom, col1, col2);
//
//        GL11.glEnable(3553);
//        GL11.glDisable(3042);
//        GL11.glDisable(2848);
//        GL11.glShadeModel(7424);
//        GL11.glColor4f(1f, 1f, 1f, 1f);
//    }
//
//    public static void quickDrawGradientSideways(double left, double top, double right, double bottom, int col1, int col2) {
//        GL11.glBegin(7);
//        GLUtils.glColor(col1);
//        GL11.glVertex2d(left, top);
//        GL11.glVertex2d(left, bottom);
//        GLUtils.glColor(col2);
//        GL11.glVertex2d(right, bottom);
//        GL11.glVertex2d(right, top);
//        GL11.glEnd();
//    }
//
//    public static void drawCircleRect(float x, float y, float x1, float y1, float radius, int color) {
//        GLUtils.glColor(color);
//        GL11.glEnable(GL11.GL_BLEND);
//        GL11.glDisable(GL11.GL_TEXTURE_2D);
//        GL11.glDisable(GL11.GL_CULL_FACE);
//        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//        GL11.glEnable(GL11.GL_LINE_SMOOTH);
//        GL11.glPushMatrix();
//        GL11.glLineWidth(1F);
//        GL11.glBegin(GL11.GL_POLYGON);
//
//        float xRadius = (float) Math.min((x1 - x) * 0.5, radius);
//        float yRadius = (float) Math.min((y1 - y) * 0.5, radius);
//        quickPolygonCircle(x + xRadius, y + yRadius, xRadius, yRadius, 180, 270, 4);
//        quickPolygonCircle(x1 - xRadius, y + yRadius, xRadius, yRadius, 90, 180, 4);
//        quickPolygonCircle(x1 - xRadius, y1 - yRadius, xRadius, yRadius, 0, 90, 4);
//        quickPolygonCircle(x + xRadius, y1 - yRadius, xRadius, yRadius, 270, 360, 4);
//
//        GL11.glEnd();
//        GL11.glPopMatrix();
//        GL11.glEnable(GL11.GL_TEXTURE_2D);
//        GL11.glEnable(GL11.GL_CULL_FACE);
//        GL11.glDisable(GL11.GL_LINE_SMOOTH);
//        GL11.glColor4f(1F, 1F, 1F, 1F);
//    }
//
//    private static void quickPolygonCircle(float x, float y, float xRadius, float yRadius, int start, int end, int split) {
//        for (int i = end; i >= start; i -= split) {
//            GL11.glVertex2d(x + Math.sin(i * Math.PI / 180.0D) * xRadius, y + Math.cos(i * Math.PI / 180.0D) * yRadius);
//        }
//    }
//
//    public static void drawEntityOnScreen(final int posX, final int posY, final int scale, final EntityLivingBase entity) {
//        GlStateManager.pushMatrix();
//        GlStateManager.enableColorMaterial();
//
//        GlStateManager.translate(posX, posY, 50.0);
//        GlStateManager.scale((-scale), scale, scale);
//        GlStateManager.rotate(180F, 0F, 0F, 1F);
//        GlStateManager.rotate(135F, 0F, 1F, 0F);
//        RenderHelper.enableStandardItemLighting();
//        GlStateManager.rotate(-135F, 0F, 1F, 0F);
//        GlStateManager.translate(0.0, 0.0, 0.0);
//
//        float renderYawOffset = entity.renderYawOffset;
//        float rotationYaw = entity.rotationYaw;
//        float rotationPitch = entity.rotationPitch;
//        float prevRotationYawHead = entity.prevRotationYawHead;
//        float rotationYawHead = entity.rotationYawHead;
//
//
//        entity.renderYawOffset = 0;
//        entity.rotationYaw = 0;
//        entity.rotationPitch = 90;
//        entity.rotationYawHead = entity.rotationYaw;
//        entity.prevRotationYawHead = entity.rotationYaw;
//
//        RenderManager rendermanager = Wrapper.INSTANCE.mc().getRenderManager();
//        rendermanager.setPlayerViewY(180F);
//        rendermanager.setRenderShadow(false);
//        rendermanager.renderEntity(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
//        rendermanager.setRenderShadow(true);
//
//        entity.renderYawOffset = renderYawOffset;
//        entity.rotationYaw = rotationYaw;
//        entity.rotationPitch = rotationPitch;
//        entity.prevRotationYawHead = prevRotationYawHead;
//        entity.rotationYawHead = rotationYawHead;
//
//        GlStateManager.popMatrix();
//        RenderHelper.disableStandardItemLighting();
//        GlStateManager.disableRescaleNormal();
//        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
//        GlStateManager.disableTexture2D();
//        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
//    }
//
//    public static void makeScissorBox(final float x, final float y, final float x2, final float y2) {
//        makeScissorBox(x, y, x2, y2, 1);
//    }
//
//    public static void makeScissorBox(final float x, final float y, final float x2, final float y2, final float scaleOffset) {
//        final ScaledResolution scaledResolution = new ScaledResolution(Wrapper.INSTANCE.mc());
//        final float factor = scaledResolution.getScaleFactor() * scaleOffset;
//        GL11.glScissor((int) (x * factor), (int) ((scaledResolution.getScaledHeight() - y2) * factor), (int) ((x2 - x) * factor), (int) ((y2 - y) * factor));
//    }
//
//    public static void resetCaps() {
//        glCapMap.forEach(RenderUtils::setGlState);
//        glCapMap.clear();
//    }
//
//    public static void clearCaps() {
//        glCapMap.clear();
//    }
//
//    public static void enableGlCap(final int cap) {
//        setGlCap(cap, true);
//    }
//
//    public static void enableGlCap(final int... caps) {
//        for (final int cap : caps)
//            setGlCap(cap, true);
//    }
//
//    public static void disableGlCap(final int cap) {
//        setGlCap(cap, false);
//    }
//
//    public static void disableGlCap(final int... caps) {
//        for (final int cap : caps)
//            setGlCap(cap, false);
//    }
//
//    public static void setGlCap(final int cap, final boolean state) {
//        glCapMap.put(cap, GL11.glGetBoolean(cap));
//        setGlState(cap, state);
//    }
//
//    public static void setGlState(final int cap, final boolean state) {
//        if (state)
//            GL11.glEnable(cap);
//        else
//            GL11.glDisable(cap);
//    }
//
//    public static boolean isHovering(float mouseX, float mouseY, float xLeft, float yUp, float xRight, float yBottom) {
//        return mouseX > xLeft && mouseX < xRight && mouseY > yUp && mouseY < yBottom;
//    }
//
//    public static boolean isHoveringBound(float mouseX, float mouseY, float xLeft, float yUp, float width, float height) {
//        return mouseX > xLeft && mouseX < xLeft + width && mouseY > yUp && mouseY < yUp + height;
//    }
//
//    public static void color(int color) {
//        float f = (float) (color >> 24 & 255) / 255.0f;
//        float f1 = (float) (color >> 16 & 255) / 255.0f;
//        float f2 = (float) (color >> 8 & 255) / 255.0f;
//        float f3 = (float) (color & 255) / 255.0f;
//        GL11.glColor4f((float) f1, (float) f2, (float) f3, (float) f);
//    }
//
//    public static void doGlScissor(int x, int y, float width, float height, float scale) {
//        int scaleFactor = 1;
//
//        while (scaleFactor < scale && Wrapper.INSTANCE.mc().displayWidth / (scaleFactor + 1) >= 320 && Wrapper.INSTANCE.mc().displayHeight / (scaleFactor + 1) >= 240) {
//            ++scaleFactor;
//        }
//
//        GL11.glScissor((int) (x * scaleFactor), (int) (Wrapper.INSTANCE.mc().displayHeight - (y + height) * scaleFactor), (int) (width * scaleFactor), (int) (height * scaleFactor));
//    }
//
//    public static void drawImage(ResourceLocation image, int x, int y, float width, float height, float alpha) {
//        GL11.glPushMatrix();
//        GL11.glDisable((int) 2929);
//        GL11.glEnable((int) 3042);
//        GL11.glDepthMask((boolean) false);
//        OpenGlHelper.glBlendFunc((int) 770, (int) 771, (int) 1, (int) 0);
//        GL11.glColor4f((float) 1.0f, (float) 0.0f, (float) 0.0f, alpha);
//        Wrapper.INSTANCE.mc().getTextureManager().bindTexture(image);
//        drawModalRectWithCustomSizedTexture((int) x, (int) y, (float) 0.0f, (float) 0.0f, (int) width, (int) height,
//                (float) width, (float) height);
//        GL11.glDepthMask((boolean) true);
//        GL11.glDisable((int) 3042);
//        GL11.glEnable((int) 2929);
//        GL11.glPopMatrix();
//
//        GL11.glColor4f((float) 1.0f, (float) 1.0f, (float) 1.0f, 1f);
//    }
//
//    public static void drawImage(ResourceLocation image, float x, float y, float width, float height) {
//        GL11.glDisable((int) 2929);
//        GL11.glEnable((int) 3042);
//        GL11.glDepthMask((boolean) false);
//        OpenGlHelper.glBlendFunc((int) 770, (int) 771, (int) 1, (int) 0);
//        GL11.glColor4f((float) 1.0f, (float) 1.0f, (float) 1.0f, 1f);
//        Wrapper.INSTANCE.mc().getTextureManager().bindTexture(image);
//        drawModalRectWithCustomSizedTexture(x, y, (float) 0.0f, (float) 0.0f, width, height, (float) width, (float) height);
//        GL11.glDepthMask((boolean) true);
//        GL11.glDisable((int) 3042);
//        GL11.glEnable((int) 2929);
//    }
//
//    /**
//     * Draws a textured rectangle at z = 0. Args: x, y, u, v, width, height, textureWidth, textureHeight
//     */
//    public static void drawModalRectWithCustomSizedTexture(int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight) {
//        float f = 1.0F / textureWidth;
//        float f1 = 1.0F / textureHeight;
//        Tessellator tessellator = Tessellator.getInstance();
//        BufferBuilder worldrenderer = tessellator.getBuffer();
//        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
//        worldrenderer.pos((double) x, (double) (y + height), 0.0D).tex((double) (u * f), (double) ((v + (float) height) * f1)).endVertex();
//        worldrenderer.pos((double) (x + width), (double) (y + height), 0.0D).tex((double) ((u + (float) width) * f), (double) ((v + (float) height) * f1)).endVertex();
//        worldrenderer.pos((double) (x + width), (double) y, 0.0D).tex((double) ((u + (float) width) * f), (double) (v * f1)).endVertex();
//        worldrenderer.pos((double) x, (double) y, 0.0D).tex((double) (u * f), (double) (v * f1)).endVertex();
//        tessellator.draw();
//    }
//
//    /**
//     * Float Version of Draws a textured rectangle at z = 0. Args: x, y, u, v, width, height, textureWidth, textureHeight
//     */
//    public static void drawModalRectWithCustomSizedTexture(float x, float y, float u, float v, float width, float height, float textureWidth, float textureHeight) {
//        float f = 1.0F / textureWidth;
//        float f1 = 1.0F / textureHeight;
//        Tessellator tessellator = Tessellator.getInstance();
//        BufferBuilder worldrenderer = tessellator.getBuffer();
//        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
//        worldrenderer.pos((double) x, (double) (y + height), 0.0D).tex((double) (u * f), (double) ((v + (float) height) * f1)).endVertex();
//        worldrenderer.pos((double) (x + width), (double) (y + height), 0.0D).tex((double) ((u + (float) width) * f), (double) ((v + (float) height) * f1)).endVertex();
//        worldrenderer.pos((double) (x + width), (double) y, 0.0D).tex((double) ((u + (float) width) * f), (double) (v * f1)).endVertex();
//        worldrenderer.pos((double) x, (double) y, 0.0D).tex((double) (u * f), (double) (v * f1)).endVertex();
//        tessellator.draw();
//    }
//
//    public static void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, EntityLivingBase ent) {
//        drawEntityOnScreen(posX, posY, scale, mouseX, mouseY, ent, 135);
//    }
//
//    public static void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, EntityLivingBase ent, float rotate) {
//        GlStateManager.enableColorMaterial();
//        GlStateManager.pushMatrix();
//        GlStateManager.translate((float) posX, (float) posY, 50.0F);
//        GlStateManager.scale((float) (-scale), (float) scale, (float) scale);
//        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
//        float f = ent.renderYawOffset;
//        float f1 = ent.rotationYaw;
//        float f2 = ent.rotationPitch;
//        float f3 = ent.prevRotationYawHead;
//        float f4 = ent.rotationYawHead;
//        GlStateManager.rotate(rotate, 0.0F, 1.0F, 0.0F);
//        RenderHelper.enableStandardItemLighting();
//        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
//        GlStateManager.rotate(-((float) Math.atan((double) (mouseY / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
//        ent.renderYawOffset = (float) Math.atan((double) (mouseX / 40.0F)) * 20.0F;
//        ent.rotationYaw = (float) Math.atan((double) (mouseX / 40.0F)) * 40.0F;
//        ent.rotationPitch = -((float) Math.atan((double) (mouseY / 40.0F))) * 20.0F;
//        ent.rotationYawHead = ent.rotationYaw;
//        ent.prevRotationYawHead = ent.rotationYaw;
//        GlStateManager.translate(0.0F, 0.0F, 0.0F);
//        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
//        rendermanager.setPlayerViewY(180.0F);
//        rendermanager.setRenderShadow(false);
//        rendermanager.renderEntity(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
//        rendermanager.setRenderShadow(true);
//        ent.renderYawOffset = f;
//        ent.rotationYaw = f1;
//        ent.rotationPitch = f2;
//        ent.prevRotationYawHead = f3;
//        ent.rotationYawHead = f4;
//        GlStateManager.popMatrix();
//        RenderHelper.disableStandardItemLighting();
//        GlStateManager.disableRescaleNormal();
//        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
//        GlStateManager.disableTexture2D();
//        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
//    }
}

