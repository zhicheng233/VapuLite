package gq.vapulite.Vapu.modules.combat;

import gq.vapulite.Vapu.Client;
import gq.vapulite.Vapu.ModuleType;
import gq.vapulite.Vapu.Value;
import gq.vapulite.Vapu.modules.Module;
import gq.vapulite.Vapu.utils.TimerUtil;
import gq.vapulite.Vapu.value.Numbers;
import gq.vapulite.Vapu.value.Option;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.C02PacketUseEntity;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static net.minecraft.realms.RealmsMth.sqrt;
import static net.minecraft.realms.RealmsMth.wrapDegrees;

public class Killaura extends Module {
    private final TimerUtil timer = new TimerUtil();
    public static EntityLivingBase target;
    private Numbers<Double> rangeValue = new Numbers<Double>("Range", "Range",4.2, 1.0, 6.0,1.0);
    private Numbers<Double> cps = new Numbers<Double>("Cps", "Cps",10.0, 1.0, 20.0,1.0);
    private Option<Boolean> autoblock = new Option<Boolean>("AutoBlock","AutoBlock", true);
    public Killaura() {
        super("Killaura", Keyboard.KEY_NONE, ModuleType.Combat,"Auto Attack the entity near you");
        this.addValues(this.rangeValue,this.autoblock,this.cps);
        Chinese="杀戮光环";
    }

    @SubscribeEvent
    public void onTick(TickEvent event) {
        if (timer.delay(1000 / ThreadLocalRandom.current().nextInt((int) 1, this.cps.getValue().intValue()))) {
            this.updateTarget();
            assistFaceEntity(target, (float) 20.0, (float) 20.0);
            Object[] objects = mc.theWorld.loadedEntityList.stream().filter(entity -> entity instanceof EntityLivingBase && entity != mc.thePlayer && ((EntityLivingBase) entity).getHealth() > 0F && entity.getDistanceToEntity(mc.thePlayer) <= rangeValue.getValue()).sorted(Comparator.comparingDouble(entity -> entity.getDistanceToEntity(mc.thePlayer))).toArray();
            if (objects.length > 0)
                target = (EntityLivingBase) objects[0];
            if(target == null || AntiBot.isServerBot(target))
                return;
            if(target.getHealth()==0)
                return;
//            if(target == mc.objectMouseOver.entityHit)
//                return;
            mc.thePlayer.swingItem();
            mc.getNetHandler().addToSendQueue(new C02PacketUseEntity(target, C02PacketUseEntity.Action.ATTACK));
            if ((Boolean) this.autoblock.getValue()){
                mc.thePlayer.getHeldItem().useItemRightClick(mc.theWorld, mc.thePlayer);
            }
            target = null;
            timer.reset();
        }
    }

    public static void assistFaceEntity(Entity entity, float yaw, float pitch) {
        double yDifference;
        if (entity == null) {
            return;
        }
        double diffX = entity.posX - mc.thePlayer.posX;
        double diffZ = entity.posZ - mc.thePlayer.posZ;
        if (entity instanceof EntityLivingBase) {
            EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
            yDifference = entityLivingBase.posY + (double) entityLivingBase.getEyeHeight() - (mc.thePlayer.posY + (double) mc.thePlayer.getEyeHeight());
        } else {
            yDifference = (entity.getEntityBoundingBox().minY + entity.getEntityBoundingBox().maxY) / 2.0 - (mc.thePlayer.posY + (double) mc.thePlayer.getEyeHeight());
        }
        double dist = sqrt(diffX * diffX + diffZ * diffZ);
        float rotationYaw = (float) (Math.atan2(diffZ, diffX) * 180.0 / Math.PI) - 90.0f;
        float rotationPitch = (float) (-(Math.atan2(yDifference, dist) * 180.0 / Math.PI));
        if (yaw > 0.0f) {
            mc.thePlayer.rotationYaw = updateRotation(mc.thePlayer.rotationYaw, rotationYaw, yaw / 4.0f);
        }
        if (pitch > 0.0f) {
            mc.thePlayer.rotationPitch = updateRotation(mc.thePlayer.rotationPitch, rotationPitch, pitch / 4.0f);
        }
    }

    public static float updateRotation(float p_70663_1_, float p_70663_2_, float p_70663_3_) {
        float var4 = wrapDegrees(p_70663_2_ - p_70663_1_);
        if (var4 > p_70663_3_) {
            var4 = p_70663_3_;
        }
        if (var4 < -p_70663_3_) {
            var4 = -p_70663_3_;
        }
        return p_70663_1_ + var4;
    }

    void updateTarget() {
        for (Entity object : getEntityList()) {
            EntityLivingBase entity;
            if (!(object instanceof EntityLivingBase) || !this.check(entity = (EntityLivingBase) object)) continue;
            this.target = entity;
        }
    }

    public static List<Entity> getEntityList() {
        if(mc.theWorld != null){return mc.theWorld.getLoadedEntityList();} else {return null;}
    }

    public boolean check(EntityLivingBase entity) {
        if (entity instanceof EntityArmorStand) {
            return false;
        }
        if (entity == mc.thePlayer) {
            return false;
        }
        if (entity.isDead) {
            return false;
        }
        if (entity.getHealth() == 0 ) {
            return false;
        }
        if(AntiBot.isServerBot(entity)){
            return false;
        }
        if(entity.getDistanceToEntity(mc.thePlayer) > 4.2F){
            return false;
        }
        return mc.thePlayer.canEntityBeSeen(entity);
    }
}
