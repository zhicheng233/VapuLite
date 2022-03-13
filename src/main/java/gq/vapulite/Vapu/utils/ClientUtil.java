package gq.vapulite.Vapu.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.util.EnumFacing;

import static gq.vapulite.Vapu.utils.Helper.mc;
import static java.lang.Math.asin;

public class ClientUtil {
    public static float[] getDirectionToBlock(int var0, int var1, int var2, EnumFacing var3) {
        EntityEgg var4 = new EntityEgg(mc.theWorld);
        var4.posX = (double) var0 + 0.5;
        var4.posY = (double) var1 + 0.5;
        var4.posZ = (double) var2 + 0.5;
        var4.posX += (double) var3.getDirectionVec().getX() * 0.25;
        var4.posY += (double) var3.getDirectionVec().getY() * 0.25;
        var4.posZ += (double) var3.getDirectionVec().getZ() * 0.25;
        return getDirectionToEntity(var4);
    }

    private static float[] getDirectionToEntity(Entity var0) {
        return new float[]{ClientUtil.getYaw(var0) + mc.thePlayer.rotationYaw, ClientUtil.getPitch(var0) + mc.thePlayer.rotationPitch};
    }

    public static float getYaw(Entity entity) {
        double x = entity.posX - mc.thePlayer.posX;
        double y = entity.posY - mc.thePlayer.posY;
        double z = entity.posZ - mc.thePlayer.posZ;
        double yaw = Math.atan2(x, z) * 57.29577951308232;
        yaw = -yaw;
        return (float) yaw;
    }

    public static float getPitch(Entity entity) {
        double x = entity.posX - mc.thePlayer.posX;
        double y = entity.posY - mc.thePlayer.posY;
        double z = entity.posZ - mc.thePlayer.posZ;
        double pitch = asin(y /= mc.thePlayer.getDistance(entity.posX,entity.posY,entity.posZ)) * 57.29577951308232;
        pitch = -pitch;
        return (float) pitch;
    }
    public static float getDirection() {
        float var1 = mc.thePlayer.rotationYaw;
        if (mc.thePlayer.moveForward < 0.0f) {
            var1 += 180.0f;
        }
        float forward = 1.0f;
        if (mc.thePlayer.moveForward < 0.0f) {
            forward = -0.5f;
        } else if (mc.thePlayer.moveForward > 0.0f) {
            forward = 0.5f;
        }
        if (mc.thePlayer.moveStrafing > 0.0f) {
            var1 -= 90.0f * forward;
        }
        if (mc.thePlayer.moveStrafing < 0.0f) {
            var1 += 90.0f * forward;
        }
        return var1 *= (float) Math.PI / 180;
    }
}
