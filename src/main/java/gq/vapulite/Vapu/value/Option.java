package gq.vapulite.Vapu.value;

public class Option<V>
extends Value<V> {
    public static int animx = 0;
    public Option(String displayName, String name, V enabled) {
        super(displayName, name);
        this.setValue(enabled);
    }
    public int getAnimx(){
        return animx;
    }
    public void setAnimx(int animx){
            Option.animx = animx;
    }
}

