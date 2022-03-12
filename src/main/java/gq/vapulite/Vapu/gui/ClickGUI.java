package gq.vapulite.Vapu.gui;

import java.io.IOException;
import java.util.ArrayList;

import gq.vapulite.Vapu.gui.components.Component;
import gq.vapulite.Vapu.gui.components.SubWindow;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import gq.vapulite.Vapu.ModuleType;

public class ClickGUI extends GuiScreen {
	public ClickGUI() {
		
	}
	
	private static final ArrayList<Component> components = new ArrayList<>();
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        for (Component component : components) {
        	GlStateManager.pushMatrix();
			component.drawComponent(mouseX, mouseY, partialTicks);
			GlStateManager.popMatrix();
		}
    }
	
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        for (Component component : components) {
			component.keyTyped(typedChar, keyCode);
		}
    }
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		for (Component component : components) {
			component.mouseClicked(mouseX, mouseY, mouseButton);
		}
	}
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		for (Component component : components) {
			component.mouseReleased(mouseX, mouseY, state);
		}
	}
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
		for (Component component : components) {
			component.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
		}
	}
	
	static {
		int x = 4;
		for (ModuleType type : ModuleType.values()) {
			SubWindow window = type.getWindow();
			window.setX(x);
			window.setY(10);
			x += window.getWidth() + 4;
			components.add(window);
		}
	}
}
