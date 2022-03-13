package gq.vapulite.Vapu.modules.combat;

import gq.vapulite.Vapu.ModuleType;
import gq.vapulite.Vapu.modules.Module;
import gq.vapulite.Manager.ModuleManager;
import net.minecraft.entity.Entity;
import org.lwjgl.input.Keyboard;

import java.util.Objects;

public class AntiBot extends Module {
        public AntiBot() {
            super("AntiBot", Keyboard.KEY_NONE, ModuleType.Combat,"Make cheats exclude the hypixel robot");
            Chinese="反机器人";
        }

        public static boolean isServerBot(Entity entity) {
            if (Objects.requireNonNull(ModuleManager.getModule("AntiBot")).state) {
                return !entity.getDisplayName().getFormattedText().startsWith("\u00a7") || entity.isInvisible() || entity.getDisplayName().getFormattedText().toLowerCase().contains("npc");
            }
            return false;
        }
    }

