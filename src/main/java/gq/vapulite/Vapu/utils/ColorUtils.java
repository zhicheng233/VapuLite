package gq.vapulite.Vapu.utils;

import java.awt.*;

public class ColorUtils {
    public static int rainbow(int delay){
        double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20);
        rainbowState %= 360;
        return Color.getHSBColor((float) (rainbowState / 360f), 0.8f, 0.7f).getRGB();
    }
}
