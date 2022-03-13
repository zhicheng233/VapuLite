package gq.vapulite.Vapu.gui.components;

import net.minecraft.client.Minecraft;

public abstract class Component {
	protected final Minecraft mc = Minecraft.getMinecraft();
	public int getWidth() {return 0;}
	public int getHeight() {return 0;}
	public abstract void drawComponent(int mouseX, int mouseY, float partialTicks);
	public void keyTyped(char typedChar, int keyCode) {}
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {}
	public void mouseReleased(int mouseX, int mouseY, int state) {}
	public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {}
}
