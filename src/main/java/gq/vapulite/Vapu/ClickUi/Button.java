package gq.vapulite.Vapu.ClickUi;

import com.google.common.collect.Lists;
import gq.vapulite.Vapu.modules.Module;

import gq.vapulite.Vapu.value.Value;
import gq.vapulite.font.CFontRenderer;
import gq.vapulite.font.FontLoaders;
import net.minecraft.client.gui.Gui;

import java.awt.*;
import java.util.ArrayList;

import static gq.vapulite.Vapu.utils.RenderUtil.reAlpha;
import static net.minecraft.client.gui.GuiScreen.isShiftKeyDown;

public class Button {
    public Module cheat;
    public Window parent;
    public int x;
    public int y;
    public int index;
    public int remander;
    public double opacity = 0.0;
    public ArrayList<ValueButton> buttons = Lists.newArrayList();
    public boolean expand;
    CFontRenderer font = FontLoaders.F16;

    public Button(Module cheat, int x, int y) {
        CFontRenderer font = FontLoaders.F16;
        CFontRenderer mfont = FontLoaders.F18;
        CFontRenderer bigfont = FontLoaders.F24;
        this.cheat = cheat;
        this.x = x;
        this.y = y;
        int y2 = y + 14;
        for (Value v : cheat.getValues()) {
            this.buttons.add(new ValueButton(v, x + 5, y2));
            y2 += 13;
        }
        this.buttons.add(new KeyBindButton(cheat, x + 5, y2));
    }

    public void render(int mouseX, int mouseY) {
        CFontRenderer font = FontLoaders.F16;
        CFontRenderer mfont = FontLoaders.F18;
        CFontRenderer bigfont = FontLoaders.F24;
        if (this.index != 0) {
            Button b2 = this.parent.buttons.get(this.index - 1);
            this.y = b2.y + 16 + (b2.expand ? 15 * b2.buttons.size() : 0);
            // y+����̫С��̫С�ͱ��һ��ClickGui��
        }

        int i = 0;
        while (i < this.buttons.size()) {
            this.buttons.get(i).y = this.y + 15 + 15 * i;
            this.buttons.get(i).x = this.x + 5;
            ++i;
        }
        Gui.drawRect(this.x - 5, this.y - 5, this.x + 85, this.y + font.getStringHeight(this.cheat.getName()) + 2,
                reAlpha(new Color(255,255,255).getRGB(), 0.8f));
        if (this.cheat.getState()) {
            Gui.drawRect(this.x - 5, this.y - 5, this.x + 85, (int) (this.y + font.getStringHeight(this.cheat.getName()) + 2), reAlpha(new Color(155, 36, 232).getRGB(), 0.5f));

            font.drawString(this.cheat.getName(), this.x, this.y, new Color(255, 255, 255).getRGB());
//			mc.fontRendererObj.drawStringWithShadow(this.cheat.getName(), this.x, this.y, new Color(210, 132, 246).getRGB());
        } else {
            font.drawString(this.cheat.getName(), this.x, this.y, new Color(23, 22, 22).getRGB());
//			mc.fontRendererObj.drawStringWithShadow(this.cheat.getName(), this.x, this.y, new Color(255, 255, 255).getRGB());
        }
        if (!this.expand && this.buttons.size() > 1) {
            //FontLoaders.NovICON20.drawString("G", this.x + 76, this.y + 1, new Color(108, 108, 108).getRGB());
        }
        if (this.expand) {
            this.buttons.forEach(b -> b.render(mouseX, mouseY));
        }
    }

    public void key(char typedChar, int keyCode) {
        this.buttons.forEach(b -> b.key(typedChar, keyCode));
    }

    public void drawCreativeTabHoveringText(String tabName, int mouseX, int mouseY, int button){
        if (mouseX > this.x - 7 && mouseX < this.x + 80 && mouseY > this.y - 6
                && mouseY < this.y + font.getStringHeight(this.cheat.getName()) + 3) {
            if (button == 0) {
                tabName=this.cheat.getName();
            }
            if (button == 1 && !this.buttons.isEmpty()) {
                boolean bl = this.expand = !this.expand;
            }
        }
    }
    public void click(int mouseX, int mouseY, int button) {
        if (mouseX > this.x - 7 && mouseX < this.x + 80 && mouseY > this.y - 6
                && mouseY < this.y + font.getStringHeight(this.cheat.getName()) + 3) {
            if (button == 0) {
                    this.cheat.toggle();
            }
            if (button == 1 && !this.buttons.isEmpty()) {
                boolean bl = this.expand = !this.expand;
            }
        }
        if (this.expand) {
            this.buttons.forEach(b -> b.click(mouseX, mouseY, button));
        }
    }

    public void setParent(Window parent) {
        this.parent = parent;
        int i = 0;
        while (i < this.parent.buttons.size()) {
            if (this.parent.buttons.get(i) == this) {
                this.index = i;
                this.remander = this.parent.buttons.size() - i;
                break;
            }
            ++i;
        }
    }
}
