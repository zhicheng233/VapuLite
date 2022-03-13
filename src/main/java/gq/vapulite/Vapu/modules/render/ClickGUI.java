package gq.vapulite.Vapu.modules.render;

import gq.vapulite.Vapu.ClickUi.ClickUi;
import gq.vapulite.Vapu.VapeClickGui.VapeClickGui;
import org.lwjgl.input.Keyboard;

import gq.vapulite.Vapu.ModuleType;
import gq.vapulite.Vapu.modules.Module;

public class ClickGUI extends Module {

	public ClickGUI() {
		super("ClickGUI", Keyboard.KEY_RSHIFT, ModuleType.Render,"Open ClickGui");
		Chinese="点击GUI";
		// TODO Auto-generated constructor stub
	}
	
	public void toggle() {
		mc.displayGuiScreen(new VapeClickGui());
		this.setState(false);
	}

}
