package gq.vapulite.Vapu.gui.UI;

import java.util.ArrayList;
import java.util.List;


public class Panel {

    private String panelName;

    private int x;
    private int y;
    private int width;
    private int height;

    private int dragX;
    private int dragY;
    private boolean drag;

    private List<Button> buttons = new ArrayList<>();

    public Panel(final String panelName, final int x, final int y, final int width) {
        this.panelName = panelName;
        this.x = x;
        this.y = y;
        this.width = width;
    }

    public String getPanelName() {
        return panelName;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public boolean isDrag() {
        return drag;
    }

    public void setDrag(boolean drag) {
        this.drag = drag;
    }

    public int getDragX() {
        return dragX;
    }

    public void setDragX(int dragX) {
        this.dragX = dragX;
    }

    public int getDragY() {
        return dragY;
    }

    public void setDragY(int dragY) {
        this.dragY = dragY;
    }

    public boolean isHoverHead(final int mouseX, final int mouseY) {
        return mouseX >= getX() && mouseX <= getX() + getWidth() && mouseY >= getY() && mouseY <= getY() + 20;
    }

    public void addButton(final Button button) {
        buttons.add(button);
    }

    public List<Button> getButtons() {
        return buttons;
    }
}