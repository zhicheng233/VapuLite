package gq.vapulite.Vapu.modules.combat;

import gq.vapulite.Vapu.ModuleType;
import gq.vapulite.Vapu.modules.Module;
import gq.vapulite.Vapu.value.Numbers;
import gq.vapulite.Vapu.value.Option;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemSword;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import net.minecraftforge.client.event.MouseEvent;

import java.util.List;
import java.util.Random;

public class Reach extends Module {
    public Random rand;
    private Option<Boolean> RandomReach = new Option<Boolean>("RandomReach","RandomReach", true);
    private Option<Boolean> weaponOnly = new Option<Boolean>("WeaponOnly","weaponOnly", false);
    private Option<Boolean> movingOnly = new Option<Boolean>("MovingOnly","movingOnly", false);
    private Option<Boolean> sprintOnly = new Option<Boolean>("SprintOnly","sprintOnly", false);
    private Option<Boolean> hitThroughBlocks = new Option<Boolean>("HitThroughBlocks","sprintOnly", false);
    private Numbers<Double> MinReach = new Numbers<Double>("Reach", "Reach",3.5, 3.0, 6.0,1.0);
    public Reach() {
            super("Reach", Keyboard.KEY_NONE, ModuleType.Combat,"Make you can attack far target");
            this.addValues(this.weaponOnly,this.movingOnly,this.sprintOnly,this.hitThroughBlocks,this.MinReach);
            Chinese="Reaching";
        }
    @SubscribeEvent
    public void onMove(final MouseEvent ev) {
        if (true) {
            if (this.weaponOnly.getValue()) {
                if (Reach.mc.thePlayer.getCurrentEquippedItem() == null) {
                    return;
                }
                if (!(Reach.mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword) && !(Reach.mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemAxe)) {
                    return;
                }
            }
            if (this.movingOnly.getValue() && Reach.mc.thePlayer.moveForward == 0.0 && Reach.mc.thePlayer.moveStrafing == 0.0) {
                return;
            }
            if (this.sprintOnly.getValue() && !Reach.mc.thePlayer.isSprinting()) {
                return;
            }
            if (!this.hitThroughBlocks.getValue() && Reach.mc.objectMouseOver != null) {
                final BlockPos zzzzz = Reach.mc.objectMouseOver.getBlockPos();
                if (zzzzz != null && Reach.mc.theWorld.getBlockState(zzzzz).getBlock() != Blocks.air) {
                    return;
                }
            }
            if(false){
                double zzzzzD2 = 3.0 + this.rand.nextDouble() * (this.MinReach.getValue() - 3.0);
                final Object[] zzzzzN = Reaching(zzzzzD2, 0.0, 0.0f);
                if (zzzzzN == null) {
                    return;
                }
                mc.objectMouseOver = new MovingObjectPosition((Entity)zzzzzN[0], (Vec3)zzzzzN[1]);
                mc.pointedEntity = (Entity)zzzzzN[0];
            } else {
                double Reach = this.MinReach.getValue();
                final Object[] zzzzzN = Reaching(Reach, 0.0, 0.0f);
                if (zzzzzN == null) {
                    return;
                }
                mc.objectMouseOver = new MovingObjectPosition((Entity)zzzzzN[0], (Vec3)zzzzzN[1]);
                mc.pointedEntity = (Entity)zzzzzN[0];
            }
        }
    }

    public static Object[] Reaching(final double zzzzzD, final double zzzzzE, final float zzzzzPT) {
        final Entity zzzzz2 = Reach.mc.getRenderViewEntity();
        Entity entity = null;
        if (zzzzz2 == null || Reach.mc.theWorld == null) {
            return null;
        }
        Reach.mc.mcProfiler.startSection("pick");
        final Vec3 zzzzz3 = zzzzz2.getPositionEyes(0.0f);
        final Vec3 zzzzz4 = zzzzz2.getLook(0.0f);
        final Vec3 zzzzz5 = zzzzz3.addVector(zzzzz4.xCoord * zzzzzD, zzzzz4.yCoord * zzzzzD, zzzzz4.zCoord * zzzzzD);
        Vec3 zzzzz6 = null;
        final float zzzzz7 = 1.0f;
        final List zzzzz8 = Reach.mc.theWorld.getEntitiesWithinAABBExcludingEntity(zzzzz2, zzzzz2.getEntityBoundingBox().addCoord(zzzzz4.xCoord * zzzzzD, zzzzz4.yCoord * zzzzzD, zzzzz4.zCoord * zzzzzD).expand(1.0, 1.0, 1.0));
        double zzzzz9 = zzzzzD;
        for (int zzzzz10 = 0; zzzzz10 < zzzzz8.size(); ++zzzzz10) {
            final Entity zzzzz11 = (Entity) zzzzz8.get(zzzzz10);
            if (zzzzz11.canBeCollidedWith()) {
                final float zzzzz12 = zzzzz11.getCollisionBorderSize();
                AxisAlignedBB zzzzz13 = zzzzz11.getEntityBoundingBox().expand((double)zzzzz12, (double)zzzzz12, (double)zzzzz12);
                zzzzz13 = zzzzz13.expand(zzzzzE, zzzzzE, zzzzzE);
                final MovingObjectPosition zzzzz14 = zzzzz13.calculateIntercept(zzzzz3, zzzzz5);
                if (zzzzz13.isVecInside(zzzzz3)) {
                    if (0.0 < zzzzz9 || zzzzz9 == 0.0) {
                        entity = zzzzz11;
                        zzzzz6 = ((zzzzz14 == null) ? zzzzz3 : zzzzz14.hitVec);
                        zzzzz9 = 0.0;
                    }
                }
                else if (zzzzz14 != null) {
                    final double zzzzz15 = zzzzz3.distanceTo(zzzzz14.hitVec);
                    if (zzzzz15 < zzzzz9 || zzzzz9 == 0.0) {
                        final boolean canRiderInteract = false;
                        if (zzzzz11 == zzzzz2.ridingEntity) {
                            if (zzzzz9 == 0.0) {
                                entity = zzzzz11;
                                zzzzz6 = zzzzz14.hitVec;
                            }
                        }
                        else {
                            entity = zzzzz11;
                            zzzzz6 = zzzzz14.hitVec;
                            zzzzz9 = zzzzz15;
                        }
                    }
                }
            }
        }
        if (zzzzz9 < zzzzzD && !(entity instanceof EntityLivingBase) && !(entity instanceof EntityItemFrame)) {
            entity = null;
        }
        Reach.mc.mcProfiler.endSection();
        if (entity == null || zzzzz6 == null) {
            return null;
        }
        return new Object[] { entity, zzzzz6 };
    }

    }

