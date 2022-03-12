package gq.vapulite.Vapu.modules.movement;

import gq.vapulite.Vapu.ModuleType;
import gq.vapulite.Vapu.gui.UI.ClickGUI;
import gq.vapulite.Vapu.modules.Module;
import gq.vapulite.Vapu.utils.ClientUtil;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class InvMove extends Module {
    public InvMove() {
        super("InvMove", Keyboard.KEY_NONE, ModuleType.Movement);
        Chinese="背包走路";
    }


    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (!(mc.currentScreen instanceof GuiContainer) && !(mc.currentScreen instanceof ClickGUI)) {
            return;
        }
        double speed = 0.05;
        if (!mc.thePlayer.onGround) {
            speed /= 4.0;
        }
        this.handleJump();
        this.handleForward(speed);
        if (!mc.thePlayer.onGround) {
            speed /= 2.0;
        }
        this.handleBack(speed);
        this.handleLeft(speed);
        this.handleRight(speed);
    }

    void moveForward(double speed) {
        float direction = ClientUtil.getDirection();
        mc.thePlayer.motionX -= (double) MathHelper.sin(direction) * speed;
        mc.thePlayer.motionZ += (double) MathHelper.cos(direction) * speed;
    }

    void moveBack(double speed) {
        float direction = ClientUtil.getDirection();
        mc.thePlayer.motionX += (double) MathHelper.sin(direction) * speed;
        mc.thePlayer.motionZ -= (double) MathHelper.cos(direction) * speed;
    }

    void moveLeft(double speed) {
        float direction = ClientUtil.getDirection();
        mc.thePlayer.motionZ += (double) MathHelper.sin(direction) * speed;
        mc.thePlayer.motionX += (double) MathHelper.cos(direction) * speed;
    }

    void moveRight(double speed) {
        float direction = ClientUtil.getDirection();
        mc.thePlayer.motionZ -= (double) MathHelper.sin(direction) * speed;
        mc.thePlayer.motionX -= (double) MathHelper.cos(direction) * speed;
    }

    void handleForward(double speed) {
        if (!Keyboard.isKeyDown(mc.gameSettings.keyBindForward.getKeyCode())) {
            return;
        }
        this.moveForward(speed);
    }

    void handleBack(double speed) {
        if (!Keyboard.isKeyDown(mc.gameSettings.keyBindBack.getKeyCode())) {
            return;
        }
        this.moveBack(speed);
    }

    void handleLeft(double speed) {
        if (!Keyboard.isKeyDown(mc.gameSettings.keyBindLeft.getKeyCode())) {
            return;
        }
        this.moveLeft(speed);
    }

    void handleRight(double speed) {
        if (!Keyboard.isKeyDown(mc.gameSettings.keyBindRight.getKeyCode())) {
            return;
        }
        this.moveRight(speed);
    }

    void handleJump() {
        if (mc.thePlayer.onGround && Keyboard.isKeyDown(mc.gameSettings.keyBindJump.getKeyCode())) {
            mc.thePlayer.jump();
        }
    }
}
