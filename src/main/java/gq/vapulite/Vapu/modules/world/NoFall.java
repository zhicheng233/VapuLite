package gq.vapulite.Vapu.modules.world;

import gq.vapulite.Vapu.ModuleType;
import gq.vapulite.Vapu.modules.Module;
import gq.vapulite.Vapu.utils.TimerUtil;
import net.minecraft.block.Block;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import net.minecraft.item.Item;

import java.util.concurrent.ThreadLocalRandom;

import static gq.vapulite.Vapu.ModuleType.World;

public class NoFall extends Module {
    private final TimerUtil timer = new TimerUtil();
    private boolean handling;
    public NoFall() {
        super("AutoMLG", Keyboard.KEY_NONE, World,"Auto use bukkit when you fall");
        Chinese="无摔落";
    }

    @SubscribeEvent
    public void onTick(TickEvent event) {
            if (mc.isGamePaused() || event.phase == TickEvent.Phase.END) {
                return;
            }
            if (this.inPosition() && this.holdWaterBucket()) {
                this.handling = true;
            }
            if (this.handling) {
                this.mlg();
                if (mc.thePlayer.onGround || mc.thePlayer.motionY > 0.0) {
                    this.reset();
                }
        }

    }

    private boolean inPosition() {
        if (mc.thePlayer.motionY < -0.6 && !mc.thePlayer.onGround && !mc.thePlayer.capabilities.isFlying && !mc.thePlayer.capabilities.isCreativeMode && !this.handling) {
            final BlockPos playerPos = mc.thePlayer.getPosition();
            for (int i = 1; i < 3; ++i) {
                final BlockPos blockPos = playerPos.down(i);
                final Block block = mc.theWorld.getBlockState(blockPos).getBlock();
                if (block.isBlockSolid((IBlockAccess) mc.theWorld, blockPos, EnumFacing.UP)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean holdWaterBucket() {
        if (this.containsItem(mc.thePlayer.getHeldItem(), Items.water_bucket)) {
            return true;
        }
        for (int i = 0; i < InventoryPlayer.getHotbarSize(); ++i) {
            if (this.containsItem(mc.thePlayer.inventory.mainInventory[i], Items.water_bucket)) {
                mc.thePlayer.inventory.currentItem = i;
                return true;
            }
        }
        return false;
    }

    private void mlg() {
        final ItemStack heldItem = mc.thePlayer.getHeldItem();
        if (this.containsItem(heldItem, Items.water_bucket) && mc.thePlayer.rotationPitch >= 70.0f) {
            final MovingObjectPosition object = mc.objectMouseOver;
            if (object.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && object.sideHit == EnumFacing.UP) {
                mc.playerController.sendUseItem((EntityPlayer) mc.thePlayer, (World) mc.theWorld, heldItem);
            }
        }
    }

    private void reset() {
        final ItemStack heldItem = mc.thePlayer.getHeldItem();
        if (this.containsItem(heldItem, Items.bucket)) {
            mc.playerController.sendUseItem((EntityPlayer) mc.thePlayer, (net.minecraft.world.World) mc.theWorld, heldItem);
        }
        this.handling = false;
    }

    private boolean containsItem(final ItemStack itemStack, final Item item) {
        return itemStack != null && itemStack.getItem() == item;
    }



}
