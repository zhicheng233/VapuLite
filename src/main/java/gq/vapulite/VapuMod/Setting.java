package gq.vapulite.VapuMod;

import java.io.File;

import org.lwjgl.input.Keyboard;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;

public class Setting {
	private static Setting instance;
	
	private static final String FILE = "123";
	public int KEY_JOIN_BED_FOUR_FOUR_RUSH = Keyboard.KEY_NUMPAD1;
	public int KEY_GESTURE = Keyboard.KEY_NUMPAD2;
	public int KEY_ANTI_ITEM_LAG = Keyboard.KEY_NUMPAD3;
	public int KEY_AUTO_FILL_ITEM = Keyboard.KEY_NUMPAD4;
	public int INT_ITEM_LAG_MAX = 128;
	public int INT_ITEM_LAG_DOWN_TO = 64;
	public int KEY_MINI_MAP = Keyboard.KEY_NUMPAD5;
	public int KEY_XKHT = Keyboard.KEY_NUMPAD6;
	public int KEY_AUTO_SHIFT = Keyboard.KEY_NUMPAD7;
	public int KEY_GOD_BRIDGE_HELPER = Keyboard.KEY_NUMPAD8;
	public int KEY_PLAY_MUSIC = Keyboard.KEY_NONE;
	public int KEY_THIN = Keyboard.KEY_NONE;
	public int KEY_AUTO_TOOL = Keyboard.KEY_NONE;
	public int KEY_AUTO_WEAPON = Keyboard.KEY_NONE;
	public int KEY_MURDER_MYSTERY_FLY = Keyboard.KEY_NONE;
	public int KEY_MURDER_MYSTERY_FLY_DOWN = Keyboard.KEY_NONE;
	public int KEY_RELOAD_SETTING = Keyboard.KEY_GRAVE;
	public int KEY_WATER_FLY = Keyboard.KEY_NONE;
	public int KEY_AFK = Keyboard.KEY_NONE;
	public int KEY_MURDER_MYSTERY_MARK = Keyboard.KEY_NONE;
	public int KEY_LAG = Keyboard.KEY_NONE;
	public int KEY_KNOCKBACK = Keyboard.KEY_NONE;
	public int KEY_BED_FIRST = Keyboard.KEY_NONE;
	
	public int KEY_TORRENT = Keyboard.KEY_NONE;
	public double TORRENT_SPEED_X = 5.0;
	public double TORRENT_SPEED_Y = 0.5;
	public int KEY_SAVE_BOX = Keyboard.KEY_NONE;
	
	public int MINI_MAP_SIZE = 160;
	public double MINI_MAP_SCALE = 1.6;
	
	public double REACH_BLOCK = 6.0;
	public double REACH_ENTITY = 3.5;
	
	public int KEY_CRIT = Keyboard.KEY_NONE;
	public int KEY_FAR_PLAY = Keyboard.KEY_NONE;
	public double FARPLAY_SPEED_X = 1.0;
	public double FARPLAY_SPEED_Y = 0.3;

	public double FARPLAY_HANG = 2;
	public double FARPLAY_STEP = 5;
	public double VALUE_MINE_BONUS = 0.0;
	
	public File configDir;
	public String version = "1.0.0";
	public Configuration configuration;
	public String[] SHIELD_NAMES = new String[] {};
	public double VALUE_KNOCKBACK_X = 1.0;
	public double VALUE_KNOCKBACK_Y = 1.0;
	public double VALUE_HIT_BOX_EXPAND = 0.0;
	public int KEY_LOCKY = Keyboard.KEY_NONE;
	public double VALUE_SPEED_BONUS = 0.0;
	public double VALUE_SPEEDJUMP_BONUS = 0.0;
	public double VALUE_FAST_SHIFT = 0.3;
	public int VALUE_FORCE_RUN_TYPE = 0;
	public int VALUE_FAST_REGEN = 0;
	public int VALUE_BLOCK_ATTACK_DELAY = -1;
	public double VALUE_GAMMA = 8.0f;
	public double VALUE_SPEED_IN_USING = 0.2;
	public double VALUE_AIR_STEP = 0.0;
	

	
	public static Setting instance() {
		if (instance == null)
			instance = new Setting();
		return instance;
	}
	
	private Setting() {
		configDir = new File(Loader.instance().getConfigDir().getPath()+"\\"+"xue.cfg");
		configuration = new Configuration(configDir, version);
		load();
	}
	
	public void load() {
		configuration.load();
		
		KEY_JOIN_BED_FOUR_FOUR_RUSH = Keyboard.getKeyIndex(configuration.get(Configuration.CATEGORY_GENERAL, "KEY_JOIN_BED_FOUR_FOUR_RUSH", Keyboard.getKeyName(KEY_JOIN_BED_FOUR_FOUR_RUSH), "send /play bedwars_four_four_rush").getString());
		KEY_GESTURE = Keyboard.getKeyIndex(configuration.get(Configuration.CATEGORY_GENERAL, "KEY_GESTURE", Keyboard.getKeyName(KEY_GESTURE), "send /ges").getString());
		KEY_ANTI_ITEM_LAG = Keyboard.getKeyIndex(configuration.get(Configuration.CATEGORY_GENERAL, "KEY_ANTI_ITEM_LAG", Keyboard.getKeyName(KEY_ANTI_ITEM_LAG)).getString());
		KEY_AUTO_FILL_ITEM = Keyboard.getKeyIndex(configuration.get(Configuration.CATEGORY_GENERAL, "KEY_AUTO_FILL_ITEM", Keyboard.getKeyName(KEY_AUTO_FILL_ITEM)).getString());
		KEY_MINI_MAP = Keyboard.getKeyIndex(configuration.get(Configuration.CATEGORY_GENERAL, "KEY_MINI_MAP", Keyboard.getKeyName(KEY_MINI_MAP)).getString());
		KEY_XKHT = Keyboard.getKeyIndex(configuration.get(Configuration.CATEGORY_GENERAL, "KEY_XKHT", Keyboard.getKeyName(KEY_XKHT)).getString());
		KEY_AUTO_SHIFT = Keyboard.getKeyIndex(configuration.get(Configuration.CATEGORY_GENERAL, "KEY_AUTO_SHIFT", Keyboard.getKeyName(KEY_AUTO_SHIFT)).getString());
		KEY_GOD_BRIDGE_HELPER = Keyboard.getKeyIndex(configuration.get(Configuration.CATEGORY_GENERAL, "KEY_GOD_BRIDGE_HELPER", Keyboard.getKeyName(KEY_GOD_BRIDGE_HELPER)).getString());
		KEY_PLAY_MUSIC = Keyboard.getKeyIndex(configuration.get(Configuration.CATEGORY_GENERAL, "KEY_PLAY_MUSIC", Keyboard.getKeyName(KEY_PLAY_MUSIC)).getString());
		KEY_THIN = Keyboard.getKeyIndex(configuration.get(Configuration.CATEGORY_GENERAL, "KEY_THIN", Keyboard.getKeyName(KEY_THIN), "try to go through wall, and climb wall if press jump").getString());
		KEY_AUTO_TOOL = Keyboard.getKeyIndex(configuration.get(Configuration.CATEGORY_GENERAL, "KEY_AUTO_TOOL", Keyboard.getKeyName(KEY_AUTO_TOOL)).getString());
		KEY_MURDER_MYSTERY_FLY = Keyboard.getKeyIndex(configuration.get(Configuration.CATEGORY_GENERAL, "KEY_MURDER_MYSTERY_FLY", Keyboard.getKeyName(KEY_MURDER_MYSTERY_FLY), "more fn: mm aim for ghost throw knife").getString());
		KEY_RELOAD_SETTING = Keyboard.getKeyIndex(configuration.get(Configuration.CATEGORY_GENERAL, "KEY_RELOAD_SETTING", Keyboard.getKeyName(KEY_RELOAD_SETTING), "reload setting, defalut to key \"~\"").getString());
		KEY_MURDER_MYSTERY_FLY_DOWN = Keyboard.getKeyIndex(configuration.get(Configuration.CATEGORY_GENERAL, "KEY_MURDER_MYSTERY_FLY_DOWN", Keyboard.getKeyName(KEY_MURDER_MYSTERY_FLY_DOWN), "when using mm fly, press to fall down instead of using shift").getString());
		KEY_WATER_FLY = Keyboard.getKeyIndex(configuration.get(Configuration.CATEGORY_GENERAL, "KEY_WATER_FLY", Keyboard.getKeyName(KEY_WATER_FLY)).getString());
		KEY_AFK = Keyboard.getKeyIndex(configuration.get(Configuration.CATEGORY_GENERAL, "KEY_AFK", Keyboard.getKeyName(KEY_AFK)).getString());
		KEY_MURDER_MYSTERY_MARK = Keyboard.getKeyIndex(configuration.get(Configuration.CATEGORY_GENERAL, "KEY_MURDER_MYSTERY_MARK", Keyboard.getKeyName(KEY_MURDER_MYSTERY_MARK), "v2, will be hided when any GuiScreen is showing").getString());
		KEY_LAG = Keyboard.getKeyIndex(configuration.get(Configuration.CATEGORY_GENERAL, "KEY_LAG", Keyboard.getKeyName(KEY_LAG), "blink (king Crimson)").getString());
		KEY_KNOCKBACK = Keyboard.getKeyIndex(configuration.get(Configuration.CATEGORY_GENERAL, "KEY_KNOCKBACK", Keyboard.getKeyName(KEY_KNOCKBACK)).getString());
		KEY_AUTO_WEAPON = Keyboard.getKeyIndex(configuration.get(Configuration.CATEGORY_GENERAL, "KEY_AUTO_WEAPON", Keyboard.getKeyName(KEY_AUTO_WEAPON)).getString());
		KEY_BED_FIRST = Keyboard.getKeyIndex(configuration.get(Configuration.CATEGORY_GENERAL, "KEY_BED_FIRST", Keyboard.getKeyName(KEY_BED_FIRST)).getString());
		KEY_SAVE_BOX = Keyboard.getKeyIndex(configuration.get(Configuration.CATEGORY_GENERAL, "KEY_SAVE_BOX", Keyboard.getKeyName(KEY_SAVE_BOX), "save last Containor Gui or open it").getString());
		KEY_CRIT = Keyboard.getKeyIndex(configuration.get(Configuration.CATEGORY_GENERAL, "KEY_CRIT", Keyboard.getKeyName(KEY_CRIT), "safe crit (delay jump after attack)").getString());
		
		KEY_TORRENT = Keyboard.getKeyIndex(configuration.get(Configuration.CATEGORY_GENERAL, "KEY_TORRENT", Keyboard.getKeyName(KEY_TORRENT), "base on blick, fly").getString());
		
		TORRENT_SPEED_X = configuration.get(Configuration.CATEGORY_GENERAL, "TORRENT_SPEED_X", TORRENT_SPEED_X).getDouble();
		TORRENT_SPEED_Y = configuration.get(Configuration.CATEGORY_GENERAL, "TORRENT_SPEED_Y", TORRENT_SPEED_Y).getDouble();
		KEY_FAR_PLAY = Keyboard.getKeyIndex(configuration.get(Configuration.CATEGORY_GENERAL, "KEY_FAR_PLAY", Keyboard.getKeyName(KEY_FAR_PLAY), "attack, bow, drop item, place block, break block in distance, and fast eat").getString());
		FARPLAY_SPEED_X = configuration.get(Configuration.CATEGORY_GENERAL, "FARPLAY_SPEED_X", FARPLAY_SPEED_X).getDouble();
		FARPLAY_SPEED_Y = configuration.get(Configuration.CATEGORY_GENERAL, "FARPLAY_SPEED_Y", FARPLAY_SPEED_Y).getDouble();
		FARPLAY_STEP = configuration.get(Configuration.CATEGORY_GENERAL, "FARPLAY_STEP", FARPLAY_STEP, "must less than 10, set larger if kick for send too much packet at a time").getDouble();
		FARPLAY_HANG = configuration.get(Configuration.CATEGORY_GENERAL, "FARPLAY_HANG", FARPLAY_HANG, "what is the height of rising and falling, in order to find a straight path without obstructions, using nofall").getDouble();
		
		
		
		MINI_MAP_SIZE = configuration.get(Configuration.CATEGORY_GENERAL, "MINI_MAP_SIZE", MINI_MAP_SIZE).getInt();
		MINI_MAP_SCALE = configuration.get(Configuration.CATEGORY_GENERAL, "MINI_MAP_SCALE", MINI_MAP_SCALE).getDouble();
		REACH_BLOCK = configuration.get(Configuration.CATEGORY_GENERAL, "REACH_BLOCK", REACH_BLOCK).getDouble();
		REACH_ENTITY = configuration.get(Configuration.CATEGORY_GENERAL, "REACH_ENTITY", REACH_ENTITY).getDouble();
		
		INT_ITEM_LAG_MAX = configuration.get(Configuration.CATEGORY_GENERAL, "INT_ITEM_LAG_MAX", INT_ITEM_LAG_MAX, "how many item at least in same position when need to unload").getInt();
		INT_ITEM_LAG_DOWN_TO = configuration.get(Configuration.CATEGORY_GENERAL, "INT_ITEM_LAG_DOWN_TO", INT_ITEM_LAG_DOWN_TO, "how many item will be left when need to unload").getInt();
		
		SHIELD_NAMES = configuration.get(Configuration.CATEGORY_GENERAL, "SHIELD_NAMES", SHIELD_NAMES, "[need attach]do not use space or empty line here").getStringList();
		VALUE_KNOCKBACK_X = configuration.get(Configuration.CATEGORY_GENERAL, "VALUE_KNOCKBACK_X", VALUE_KNOCKBACK_X, "multi the horizontal that server give you, include Explosion and Velocity").getDouble();
		VALUE_KNOCKBACK_Y = configuration.get(Configuration.CATEGORY_GENERAL, "VALUE_KNOCKBACK_Y", VALUE_KNOCKBACK_Y, "multi the vertical knockback that server give you, include Explosion and Velocity").getDouble();
		VALUE_HIT_BOX_EXPAND = configuration.get(Configuration.CATEGORY_GENERAL, "VALUE_HIT_BOX_EXPAND", VALUE_HIT_BOX_EXPAND).getDouble();
		KEY_LOCKY = Keyboard.getKeyIndex(configuration.get(Configuration.CATEGORY_GENERAL, "KEY_LOCKY", Keyboard.getKeyName(KEY_LOCKY), "simple fly, or [need attach]falling with no fall when pressing shift").getString());
		VALUE_FORCE_RUN_TYPE = configuration.get(Configuration.CATEGORY_GENERAL, "VALUE_FORCE_RUN_TYPE", VALUE_FORCE_RUN_TYPE, "0 disable, 1 force on Ctrl, 2 force on Ctrl and no run on releasing Ctrl").getInt();
		
		VALUE_MINE_BONUS = configuration.get(Configuration.CATEGORY_GENERAL, "VALUE_MINE_BONUS", VALUE_MINE_BONUS, "fast mine bonus").getDouble();
		VALUE_SPEED_BONUS = configuration.get(Configuration.CATEGORY_GENERAL, "VALUE_SPEED_BONUS", VALUE_SPEED_BONUS, "speed mine bonus").getDouble();
		VALUE_SPEEDJUMP_BONUS = configuration.get(Configuration.CATEGORY_GENERAL, "VALUE_SPEEDJUMP_BONUS", VALUE_SPEEDJUMP_BONUS, "[need attach]bonus addition speed on jump").getDouble();
		VALUE_FAST_SHIFT = configuration.get(Configuration.CATEGORY_GENERAL, "VALUE_FAST_SHIFT", VALUE_FAST_SHIFT, "[need attach]").getDouble();
		VALUE_FAST_REGEN = configuration.get(Configuration.CATEGORY_GENERAL, "VALUE_FAST_REGEN", VALUE_FAST_REGEN, "how many additional c03 packet per tick to fast regen").getInt();
		VALUE_BLOCK_ATTACK_DELAY = configuration.get(Configuration.CATEGORY_GENERAL, "VALUE_BLOCK_ATTACK_DELAY", VALUE_BLOCK_ATTACK_DELAY, "-1 disable, >=0 can attack when block, how many tick it need to pass to block again").getInt();
		VALUE_GAMMA = configuration.get(Configuration.CATEGORY_GENERAL, "VALUE_GAMMA", VALUE_GAMMA, "min gamma for render").getDouble();
//		VALUE_JB_FORMAT = configuration.get(Configuration.CATEGORY_GENERAL, "VALUE_JB_FORMAT", VALUE_JB_FORMAT, "jb format").getString();
		VALUE_SPEED_IN_USING = configuration.get(Configuration.CATEGORY_GENERAL, "VALUE_SPEED_IN_USING", VALUE_SPEED_IN_USING, "speed rate when using item, defalut to 0.2").getDouble();
		VALUE_AIR_STEP = configuration.get(Configuration.CATEGORY_GENERAL, "VALUE_AIR_STEP", VALUE_AIR_STEP, "can step in air, on grounp is 0.6 ").getDouble();
		
		configuration.save();
	}

}
