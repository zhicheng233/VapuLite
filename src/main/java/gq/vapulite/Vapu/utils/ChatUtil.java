/*
 * Decompiled with CFR 0_132.
 */
package gq.vapulite.Vapu.utils;

import gq.vapulite.Vapu.Client;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class ChatUtil {
    private final ChatComponentText message;

    private ChatUtil(ChatComponentText message) {
        this.message = message;
    }

    /* synthetic */ ChatUtil(ChatComponentText chatComponentText, ChatUtil chatUtils) {
        this(chatComponentText);
    }

    public static String addFormat(String message, String regex) {
        return message.replaceAll("(?i)" + regex + "([0-9a-fklmnor])", "\u00a7$1");
    }

    public void displayClientSided() {
        Minecraft.getMinecraft().thePlayer.addChatMessage(this.message);
    }

    private ChatComponentText getChatComponent() {
        return this.message;
    }

    public static class ChatMessageBuilder {
        private static final EnumChatFormatting defaultMessageColor = EnumChatFormatting.WHITE;
        private final ChatComponentText theMessage = new ChatComponentText("");
        private boolean useDefaultMessageColor = false;
        private ChatStyle workingStyle = new ChatStyle();
        private ChatComponentText workerMessage = new ChatComponentText("");

        public ChatMessageBuilder(boolean prependDefaultPrefix, boolean useDefaultMessageColor) {
            if (prependDefaultPrefix) {
                Client.instance.getClass();
                this.theMessage.appendSibling(new ChatMessageBuilder(false, false)
                        .setColor(EnumChatFormatting.RED).build().getChatComponent());
            }
            this.useDefaultMessageColor = useDefaultMessageColor;
        }

        public ChatMessageBuilder() {
        }

        public ChatMessageBuilder appendText(String text) {
            this.appendSibling();
            this.workerMessage = new ChatComponentText(text);
            this.workingStyle = new ChatStyle();
            if (this.useDefaultMessageColor) {
                this.setColor(defaultMessageColor);
            }
            return this;
        }

        public ChatMessageBuilder setColor(EnumChatFormatting color) {
            this.workingStyle.setColor(color);
            return this;
        }

        public ChatMessageBuilder bold() {
            this.workingStyle.setBold(true);
            return this;
        }

        public ChatMessageBuilder italic() {
            this.workingStyle.setItalic(true);
            return this;
        }

        public ChatMessageBuilder strikethrough() {
            this.workingStyle.setStrikethrough(true);
            return this;
        }

        public ChatMessageBuilder underline() {
            this.workingStyle.setUnderlined(true);
            return this;
        }

        public ChatUtil build() {
            this.appendSibling();
            return new ChatUtil(this.theMessage, null);
        }

        private void appendSibling() {
            this.theMessage.appendSibling(this.workerMessage.setChatStyle(this.workingStyle));
        }
    }

}
