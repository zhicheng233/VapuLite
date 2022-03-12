/*
 * Decompiled with CFR 0.152.
 */
package gq.vapulite.VapuMod.event.impl;

import gq.vapulite.VapuMod.event.Event;

public class SafeWalkEvent
extends Event {
    public boolean walkSafely;

    public SafeWalkEvent(boolean walkSafely) {
        this.walkSafely = walkSafely;
    }

    public boolean isWalkSafely() {
        return this.walkSafely;
    }

    public void setWalkSafely(boolean walkSafely) {
        this.walkSafely = walkSafely;
    }
}

