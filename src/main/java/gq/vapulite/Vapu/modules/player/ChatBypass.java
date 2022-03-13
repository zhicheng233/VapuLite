package gq.vapulite.Vapu.modules.player;

import gq.vapulite.Vapu.Client;
import gq.vapulite.Vapu.ModuleType;
import gq.vapulite.Vapu.modules.Module;
import gq.vapulite.Vapu.utils.Helper;
import org.lwjgl.input.Keyboard;

public class ChatBypass extends Module {
    public ChatBypass() {
        super("ChatBypass", Keyboard.KEY_NONE, ModuleType.Player,"");
        Chinese="聊天绕过";
    }

    @Override
    public void enable() {
        Helper.sendMessage("使用/cp <Message>发送ChatBypassed消息");
        Client.ChatBypass = true;
    }

    @Override
    public void disable() {
        Client.ChatBypass = false;
    }



}
