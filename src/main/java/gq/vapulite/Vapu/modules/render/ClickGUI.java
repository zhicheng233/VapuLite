package gq.vapulite.Vapu.modules.render;

import org.lwjgl.input.Keyboard;

import gq.vapulite.Vapu.ModuleType;
import gq.vapulite.Vapu.modules.Module;

public class ClickGUI extends Module {

	public ClickGUI() {
		super("ClickGUI", Keyboard.KEY_RSHIFT, ModuleType.Render);
		Chinese="点击GUI";
		// TODO Auto-generated constructor stub
	}
	
	public void toggle() {
		mc.displayGuiScreen(new gq.vapulite.Vapu.gui.UI.ClickGUI());
	}

}
