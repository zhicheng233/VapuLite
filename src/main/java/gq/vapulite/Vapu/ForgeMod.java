package gq.vapulite.Vapu;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;

@Mod(modid="VapuLite", name="VapuLite", version="1.0.0", acceptedMinecraftVersions="[1.8.9]")
public class ForgeMod {
	@SideOnly(Side.CLIENT)
    @Mod.EventHandler
    public void init(FMLPreInitializationEvent event) throws IOException {
        new Client();
    }
	
	@SideOnly(Side.SERVER)
    @Mod.EventHandler
    public void initServer(FMLPreInitializationEvent event) {
        System.out.println("666主播居然要给服务器安装Vapu");
    }
}
