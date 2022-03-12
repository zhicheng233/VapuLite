package gq.vapulite.VapuMod;

import gq.vapulite.eventapi.events.Event;

public class EventMove implements Event {
    public static double x;
    public static double y;
    public static double z;
    private final double motionX;
    private final double motionY;
    private final double motionZ;

    public EventMove(double x, double y, double z) {
        EventMove.x = x;
        EventMove.y = y;
        EventMove.z = z;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        EventMove.x = x;
    }

    public double getY() {
        return y;
    }

    public static void setY(double y) {
        EventMove.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        EventMove.z = z;
    }
}
