package gq.vapulite.Vapu.modules.Config;

import gq.vapulite.Vapu.ModuleType;
import gq.vapulite.Vapu.modules.Module;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

import static org.lwjgl.input.Keyboard.KEY_NONE;

public class IGN extends Module {
    public IGN() {
        super("CopyName", KEY_NONE, ModuleType.Config);
        Chinese="复制名字";
        NoToggle=true;
    }

    public void enable() {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(mc.thePlayer.getName()), null);
        state=false;
    }
}
