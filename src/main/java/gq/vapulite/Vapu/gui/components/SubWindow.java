package gq.vapulite.Vapu.gui.components;

import java.util.ArrayList;

import gq.vapulite.Vapu.utils.Misc;
import gq.vapulite.Vapu.utils.RenderUtil;
import org.lwjgl.input.Mouse;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

public class SubWindow extends Component {
	private final String title;
	private boolean dragging;
	
	private boolean hidden;
	
	protected double x,y;
	public int getX() {
		return (int) (x * Misc.getScaledResolution().getScaledWidth_double());
	}
	public int getY() {
		return (int) (y * Misc.getScaledResolution().getScaledHeight_double());
	}
	public double getX_double() {
		return x * Misc.getScaledResolution().getScaledWidth_double();
	}
	public double getY_double() {
		return y * Misc.getScaledResolution().getScaledHeight_double();
	}
	
	public void setX(double x) {
		this.x = x / Misc.getScaledResolution().getScaledWidth_double();
	}
	
	public void setY(double y) {
		this.y = y / Misc.getScaledResolution().getScaledHeight_double();
	}
	
	private final ArrayList<Component> components = new ArrayList<>();
	
	public void addComponent(Component component) {
		if(component instanceof SubWindow)
			throw new java.lang.IllegalArgumentException();
		components.add(component);
	}
	
	public SubWindow(String title) {
		this.title = title;
	}
	
	public SubWindow(String title, Component... components) {
		this.title = title;
		for (Component component : components) {
			this.components.add(component);
		}
	}
	
	@Override
	public void drawComponent(int mouseX, int mouseY, float partialTicks) {
		if(dragging) {
			ScaledResolution sr = Misc.getScaledResolution();
			double deltaX =  Mouse.getDX() / (double)sr.getScaleFactor(),
				   deltaY = -Mouse.getDY() / (double)sr.getScaleFactor();
			this.x = (getX_double() + deltaX) / sr.getScaledWidth_double();
			this.y = (getY_double() + deltaY) / sr.getScaledHeight_double();
		}
		RenderUtil.drawRoundedRect(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY() + this.getHeight(), 0xCC000000);
		mc.fontRendererObj.drawString(title, this.getX() + 2, this.getY() + 2, 0xFFFFFFFF);
		if(hidden)
			return;
		GlStateManager.pushMatrix();
		GlStateManager.translate(getX_double(), getY_double() + 14, 0);
		for (Component component : components) {
			component.drawComponent(mouseX, mouseY, partialTicks);
			GlStateManager.translate(0, component.getHeight(), 0);
		}
		GlStateManager.popMatrix();
	}
	
	public int getWidth() {
		int width = mc.fontRendererObj.getStringWidth(title);
		for (Component component : components) {
			width = Math.max(width, component.getWidth());
		}
		return width + 4;
	}
	public int getHeight() {
		int height = 12;
		if(hidden)
			return height;
		for (Component component : components) {
			height += component.getHeight();
		}
		return height + 4;
	}
	
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		this.dragging = mouseX > getX() && mouseX < getX() + getWidth() &&
				   		mouseY > getY() && mouseY < getY() + 12;
		for (Component component : components) {
			component.mouseClicked(mouseX, mouseY, mouseButton);
		}
		if(dragging) {
			if(mouseButton == 1) {
				this.hidden = !this.hidden;
			}
			if(mouseButton != 0) {
				dragging = false;
			}
		}
	}
	public void mouseReleased(int mouseX, int mouseY, int state) {
		this.dragging = false;
		for (Component component : components) {
			component.mouseReleased(mouseX, mouseY, state);
		}
	}
	
	public void keyTyped(char typedChar, int keyCode) {
		for (Component component : components) {
			component.keyTyped(typedChar, keyCode);
		}
	}
	
}
