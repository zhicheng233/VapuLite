package gq.vapulite.xray;

public class XRayData {
    private final int meta;
    private int id;
    private int red;
    private int green;
    private int blue;

    public XRayData(int id, int meta, int red, int green, int blue) {
        this.id = id;
        this.meta = meta;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMeta() {
        return this.meta;
    }

    public int getRed() {
        return this.red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return this.green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return this.blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }
}

