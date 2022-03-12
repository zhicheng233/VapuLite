/*
 * Decompiled with CFR 0.152.
 */
package gq.vapulite.VapuMod.event.impl;

import gq.vapulite.VapuMod.event.Event;

public class SlowdownEvent
extends Event {
    public Type type;

    public SlowdownEvent(Type type) {
        this.type = type;
    }

    public static enum Type {
        Sprinting;

    }
}

