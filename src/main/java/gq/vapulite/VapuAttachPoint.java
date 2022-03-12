package gq.vapulite;

import gq.vapulite.Vapu.Client;
import net.minecraft.launchwrapper.LaunchClassLoader;

import java.lang.instrument.Instrumentation;

public class VapuAttachPoint {
    public static void agentmain(String args, Instrumentation instrumentation) throws Exception {
        for (Class<?> classes : instrumentation.getAllLoadedClasses()) {
            if (classes.getName().startsWith("net.minecraft.client.Minecraft")) {
                LaunchClassLoader classLoader = (LaunchClassLoader)classes.getClassLoader();
                classLoader.addURL(VapuAttachPoint.class.getProtectionDomain().getCodeSource().getLocation());
                Class<?> client = classLoader.loadClass(Client.class.getName());
                client.newInstance();
            }
        }
    }
}