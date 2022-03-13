package gq.vapulite.Vapu.modules.render;

import gq.vapulite.Vapu.ModuleType;
import gq.vapulite.Vapu.modules.Module;
import gq.vapulite.Vapu.modules.combat.AntiBot;
import gq.vapulite.Vapu.utils.ColorUtil;
import gq.vapulite.Vapu.value.Option;
import gq.vapulite.render.ColorUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class ESP extends Module {
    private Option<Boolean> invisible = new Option<Boolean>("Invisible","invisible", false);
    private Option<Boolean> redOnDalage = new Option<Boolean>("RedOnDalage","redOnDalage", true);
    public ESP() {
        super("ESP", Keyboard.KEY_NONE, ModuleType.Render,"Draw boxes for other player");
        this.addValues(this.invisible,this.redOnDalage);
        Chinese="ExampleModule";
    }
    @SubscribeEvent
    public void o(final RenderWorldLastEvent ev) {

        for (final EntityPlayer en : mc.theWorld.playerEntities) {
            if (en != mc.thePlayer && (!AntiBot.isServerBot(en))) {
                if (!this.invisible.getValue() && en.isInvisible()) {
                    return;
                }
                int rgb = 0;
                rgb = ColorUtil.getRainbow().getRGB();
                StorageESP.ee((Entity)en, rgb, this.redOnDalage.getValue(), (int) 2.0);
            }
        }
    }
}
