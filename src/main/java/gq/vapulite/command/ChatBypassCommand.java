package gq.vapulite.command;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

import gq.vapulite.Vapu.Client;
import gq.vapulite.Vapu.utils.Helper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class ChatBypassCommand implements ICommand {

	private Minecraft mc = Minecraft.getMinecraft();
	
	public int width = 240;
	public int height = 16;
	public Client vapuClient =null;
	
	public byte[][] data = new byte[height][width];
	public int width_index = 0;
	public byte[][] encode = new byte[][] {
		new byte[] { 1, 4 },
		new byte[] { 2, 5 },
		new byte[] { 3, 6 },
		new byte[] { 7, 8 },
	};
	
	public ChatBypassCommand(Client vapuClient) {
		this.vapuClient = vapuClient;
	}
	
	public void reset() {
		for (int i=0;i<data.length;i++) {
			for(int j=0;j<data[i].length;j++) {
				data[i][j]=1;
			}
		}
		width_index = 0;
	}
	
	public void put(int[] datas, int w) {
		for(int i=0; i<=datas.length; i++) {
			int x,y;
			x = (i % w) + width_index;
			y = i / w;
			//byte data = (byte) datas[i];
			if (x>=this.width || y >= this.height)
				continue;
			this.data[y][x] = (byte)(datas[i] >> 24 == 0 ? 1 : 0);
		}
		
		this.width_index += w;
	}
	
	public char genChar(int x, int y) {
		int c = 0x2800;
		for(int dx=0; dx<2; dx++) {
			for (int dy=0; dy<4; dy++) {
				int format = 1 << (encode[dy][dx]-1);
				int tx, ty;
				tx = x+dx;
				ty = y+dy;
				if (x>=width || y >= height)
					continue;
				if (this.data[ty][tx]==0)
					c += format;
			}
		}
		
		return (char)c;
	}
	
	public List<String> toStringList() {
		List<String> list = new ArrayList<String>();
		for(int y=0; y<this.height; y+=4) {
			StringBuilder sb = new StringBuilder();
			for (int x=0; x<Math.min(this.width,this.width_index); x+=2) {
				sb.append(this.genChar(x, y));
			}
			list.add(sb.toString());
		}
		return list;
	}
	
	public void send() {
		for (String s : this.toStringList()) {
			this.vapuClient.faList.add(s);
			//mc.thePlayer.sendChatMessage(s);
		}
	}
	
	@Override
	public String getCommandName() {
		return "cp";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/cp nm$L";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if(Client.ChatBypass){
			if (args.length > 0) {
				try {
					this.FA(Joiner.on(' ').join(args));
				}
				catch(Exception e) {
				}
			}
			else {
			}
		} else {
			Helper.sendMessage("ChatBypass未开启");
		}
	}
	
	public void FA(String msg) {
		//this.exampleMod.displayChatMessage(msg);
		FontRenderer fontRenderer = mc.fontRendererObj;
		boolean unicodeFlag = fontRenderer.getUnicodeFlag();
		byte[] glyphWidth = (byte[])(ReflectionHelper.getPrivateValue(FontRenderer.class, fontRenderer, new String[] {"glyphWidth", "field_78287_e"}));
		
		this.reset();
		for (int i = 0; i < msg.length(); ++i)
		{
			char character = msg.charAt(i);
			int page = character / 256;
			BufferedImage bufferImage;
			try {
				bufferImage = TextureUtil.readBufferedImage(
						mc.getResourceManager().getResource(
								new ResourceLocation(
										String.format("textures/font/unicode_page_%02x.png", new Object[] {Integer.valueOf(page)})
										)).getInputStream()
								);
			} catch (IOException e) {
				continue;
			}
			int j = glyphWidth[character] >>> 4;
			int k = glyphWidth[character] & 15;
			fontRenderer.setUnicodeFlag(true);
			int index = "\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000".indexOf(character);
			int x = character % 16 * bufferImage.getWidth()/16;
			int width = (int)((fontRenderer.getCharWidth(character)-1)*2);
			
			x = (int)(width<=1 ? x+4 : (width<=bufferImage.getHeight()/16/8 ? x+2 : (width<=bufferImage.getHeight()/16/4 ? x+1 : (width<=bufferImage.getHeight()/16/2 ? x : x))));
			width = (int)(width<=1 ? width+2 : (width<=bufferImage.getHeight()/16/8 ? width * 3 : (width<=bufferImage.getHeight()/16/4 ? width*2 : (width<=bufferImage.getHeight()/16/2 ? width*1.5 : width))));
			if (character == ' ')
				width /= 2;
			int height = bufferImage.getHeight()/16;
//			MiniMap.this.log(String.format("width: %s", width));
			if (glyphWidth[character]==0 || width==0) {
				continue;
			}
			


			int right = k;
			//int f2 = (character % 16 * 16) + f;
			int y = (character & 255) / 16 * 16;
			//float f4 = f1 - f - 0.02F;
//			MiniMap.this.displayChatMessage(String.valueOf(x));
			int[] tmpdata = new int[width*height];
			if(character != ' ')
				bufferImage.getRGB(x, y, width, height, tmpdata, 0, width);
			this.put(tmpdata, width);
//			for	(int ii = 0; ii<tmpdata.length; ii++) {
//				System.out.print(tmpdata[ii] >> 24 == 0 ? "#" : " ");
//				if((ii + 1) % width == 0) {
//					System.out.println();
//				}
//			}
		}

		this.send();
		fontRenderer.setUnicodeFlag(unicodeFlag);
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return sender.getCommandSenderEntity() instanceof EntityPlayer;
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		//this.exampleMod.log(MinecraftServer.getServer().getAllUsernames().toString());
//		for(String s : MinecraftServer.getServer().getAllUsernames()) {
//			this.exampleMod.log(s);
//		}
		//return Collections.<String>emptyList();
		List<String> list = new ArrayList<String>();
		try {
			//this.exampleMod.log(MinecraftServer.getServer().getConfigurationManager().toString());
			
			Ordering<NetworkPlayerInfo> field_175252_a = Ordering.from(new Comparator<NetworkPlayerInfo>()
	        {
	            private void PlayerComparator()
	            {
	            }

	            public int compare(NetworkPlayerInfo p_compare_1_, NetworkPlayerInfo p_compare_2_)
	            {
	                ScorePlayerTeam scoreplayerteam = p_compare_1_.getPlayerTeam();
	                ScorePlayerTeam scoreplayerteam1 = p_compare_2_.getPlayerTeam();
	                return ComparisonChain.start().compareTrueFirst(p_compare_1_.getGameType() != WorldSettings.GameType.SPECTATOR, p_compare_2_.getGameType() != WorldSettings.GameType.SPECTATOR).compare(scoreplayerteam != null ? scoreplayerteam.getRegisteredName() : "", scoreplayerteam1 != null ? scoreplayerteam1.getRegisteredName() : "").compare(p_compare_1_.getGameProfile().getName(), p_compare_2_.getGameProfile().getName()).result();
	            }
	        });
			String last_s = "";
			for (NetworkPlayerInfo networkPlayerInfoIn : field_175252_a.sortedCopy(mc.thePlayer.sendQueue.getPlayerInfoMap())) {
				String s = networkPlayerInfoIn.getDisplayName() != null && false ? networkPlayerInfoIn.getDisplayName().getFormattedText() : networkPlayerInfoIn.getGameProfile().getName();
				if (!s.equals(last_s))
					list.add(s);
				last_s = s;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
        String[] astring = new String[list.size()];

        for (int i = 0; i < list.size(); ++i)
        {
            astring[i] = list.get(i);
        }

		return CommandBase.getListOfStringsMatchingLastWord(args, astring);
		//return MinecraftServer.getTabCompletions();
	}

	@Override
	public int compareTo(ICommand arg0) {
		// TODO Auto-generated method stub
		return this.getCommandName().compareTo(arg0.getCommandName());
	}

	@Override
	public List<String> getCommandAliases() {
		// TODO Auto-generated method stub
		return Collections.<String>emptyList();
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		// TODO Auto-generated method stub
		return false;
	}
}