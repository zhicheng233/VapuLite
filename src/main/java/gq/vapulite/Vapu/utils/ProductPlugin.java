package gq.vapulite.Vapu.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSource;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.logging.log4j.LogManager;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.fml.relauncher.CoreModManager;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.Name;

@Name("ProductPlugin")
public class ProductPlugin implements IFMLLoadingPlugin {
	
	
	public ProductPlugin() {
		try {
			URLClassLoader appClassLoader = (URLClassLoader) Launch.class.getClassLoader();
			Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
			method.setAccessible(true);
			method.invoke(appClassLoader, this.getClass().getProtectionDomain().getCodeSource().getLocation());
			MethodUtils.invokeStaticMethod(appClassLoader.loadClass(this.getClass().getName()), "registerTransformer");
		}
		catch(Exception e) {
			Tool.logException(e);
		}
	}
	
	@Override
	public String[] getASMTransformerClass() {
		return new String[0]; //{"com.xue.vapu.ClassTransformer"};
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {

	}
	
	public static void registerTransformer() {
		try {
			LaunchClassLoader classLoader = Launch.classLoader;
			Field field;
			field = LaunchClassLoader.class.getDeclaredField("transformers");
			field.setAccessible(true);
			List<IClassTransformer> transformers = (List<IClassTransformer>) field.get(classLoader);
			IClassTransformer trasformer = (IClassTransformer)Class.forName("gq.vapu.vapu.ClassTransformer", true, classLoader).newInstance();;
			ModList list = new ModList();
			field.set(classLoader, list);
			
			Tool.logerror("interupt transforms list");
			Tool.logerror("interupt transforms list");
			Tool.logerror("interupt transforms list");
			Tool.logerror("interupt transforms list");
			Tool.logerror("interupt transforms list");
			
			for (IClassTransformer transformer : transformers) {
				list.add(transformer);
				Tool.logerror(transformer.toString());
			}
			
			list.add((IClassTransformer) trasformer);
			
	        CodeSource codeSource = ProductPlugin.class.getProtectionDomain().getCodeSource();
	        if (codeSource != null) {
	            URL location = codeSource.getLocation();
	            try {
	                File file = new File(location.toURI());
	                if (file.isFile()) {


	               CoreModManager.getIgnoredMods().remove(file.getName());

	                }
	            } catch (URISyntaxException e) {
	                e.printStackTrace();
	            }
	        } else {
	            LogManager.getLogger().warn("No CodeSource, if this is not a development environment we might run into problems!");
	            LogManager.getLogger().warn(ProductPlugin.class.getProtectionDomain());
	        }
			
		} catch (NoSuchFieldException e) {
			Tool.logException(e);
		} catch (SecurityException e) {
			Tool.logException(e);
		} catch (IllegalArgumentException e) {
			Tool.logException(e);
		} catch (IllegalAccessException e) {
			Tool.logException(e);
		} catch (InstantiationException e) {
			Tool.logException(e);
		} catch (ClassNotFoundException e) {
			Tool.logException(e);
		}
		
	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}
	
	}