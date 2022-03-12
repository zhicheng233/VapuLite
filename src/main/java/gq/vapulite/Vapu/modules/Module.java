package gq.vapulite.Vapu.modules;

import gq.vapulite.Vapu.ModuleType;
import gq.vapulite.Vapu.utils.Helper;
import gq.vapulite.Vapu.Client;
import gq.vapulite.values.Value;
import gq.vapulite.values.type.NewMode;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.ArrayList;
import java.util.List;

public class Module {
    public static final Minecraft mc = Minecraft.getMinecraft();
    public boolean state = false;
    public int key;
    public List<Value> values = new ArrayList<>();
    public List<NewMode> newModes = new ArrayList<>();
    public static String Chinese;
    public static boolean NoToggle = false;
    public String name;
    public ModuleType category;

    public Module(String name, int key, ModuleType category) {
        this.name = name;
        this.key = key;
        this.category = category;
    }

    public void addValues(Value... values) {
        Value[] v1 = values;
        int vl = values.length;

        for (int i = 0; i < vl; ++i) {
            Value value = v1[i];
            this.values.add(value);
        }
    }

    public void addValues(NewMode... values) {
        NewMode[] v1 = values;
        int vl = values.length;

        for (int i = 0; i < vl; ++i) {
            NewMode value = v1[i];
            this.newModes.add(value);
        }
    }

    public List<Value> getValues() {
        return this.values;
    }



    public void toggle() {
        if(NoToggle){
            if(Client.MessageON){
                if (this.state) {
                    Helper.sendMessage(this.getName() + " Disabled");
                } else {
                    Helper.sendMessage(this.getName() + " Enabled");
                }
            }
        }
        mc.thePlayer.playSound("random.click", 0.3f, 0.5f);
        this.setState(!this.state);
    }

    public void setState(boolean state) {
        mc.thePlayer.playSound("random.click", 0.3f, 0.5f);
        if (this.state == state) {
            return;
        }
        this.state = state;
        if (state) {
            MinecraftForge.EVENT_BUS.register(this);
            FMLCommonHandler.instance().bus().register(this);
            enable();
        } else {
            MinecraftForge.EVENT_BUS.unregister(this);
            FMLCommonHandler.instance().bus().unregister(this);
            disable();
        }
    }

    public void enable() {

    }

    public void disable() {

    }

    public String getName() {
        return name;
    }

    public String getChinese() {
        return Chinese;
    }

    public int getKey() {
        return key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getState() {
        return state;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public ModuleType getCategory() {
        return category;
    }

    public void setCategory(ModuleType category) {
        this.category = category;
    }

    public void onRenderWorldLast(RenderWorldLastEvent event) {
    }
}
