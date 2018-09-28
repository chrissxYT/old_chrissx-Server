package de.chrissx.server.mainplugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

import de.chrissx.bes.Saver;
import de.chrissx.server.commands.AntiGriefListener;
import de.chrissx.server.commands.Biome;
import de.chrissx.server.commands.Broadcast;
import de.chrissx.server.commands.CmdExec;
import de.chrissx.server.commands.Color;
import de.chrissx.server.commands.CommandSpy;
import de.chrissx.server.commands.CreateWorld;
import de.chrissx.server.commands.DeleteWorld;
import de.chrissx.server.commands.HP;
import de.chrissx.server.commands.Home;
import de.chrissx.server.commands.Ip;
import de.chrissx.server.commands.ItemGive;
import de.chrissx.server.commands.Nick;
import de.chrissx.server.commands.Ping;
import de.chrissx.server.commands.Pos1;
import de.chrissx.server.commands.Pos2;
import de.chrissx.server.commands.RageMode;
import de.chrissx.server.commands.RageModeList;
import de.chrissx.server.commands.RageModeSetup;
import de.chrissx.server.commands.RageModeStart;
import de.chrissx.server.commands.RageModeStats;
import de.chrissx.server.commands.ReGen;
import de.chrissx.server.commands.SaveLog;
import de.chrissx.server.commands.Set;
import de.chrissx.server.commands.SetHome;
import de.chrissx.server.commands.Skin;
import de.chrissx.server.commands.Speed;
import de.chrissx.server.commands.Stats;
import de.chrissx.server.commands.TeleportWorld;
import de.chrissx.server.commands.Undo;
import de.chrissx.server.commands.VanillaBlocker;
import de.chrissx.server.commands.Vanish;
import de.chrissx.server.commands.Walls;
import de.chrissx.server.commands.WorldList;
import de.chrissx.server.events.AntiGriefEvents;
import de.chrissx.server.events.AxePosSetEvents;
import de.chrissx.server.events.Ev;
import de.chrissx.server.events.LabyDisabler;
import de.chrissx.server.events.PlayerDeathListener;
import de.chrissx.server.events.TeleportSignEvents;
import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.GameWriter;
import de.chrissx.server.shared.P;

public class Main extends JavaPlugin {
	
	List<CmdExec> execs = new ArrayList<CmdExec>();
	List<Ev> listeners = new ArrayList<Ev>();
	Reflector refl;
	
	public void onEnable() {
		refl = new Reflector();
		try {
			Checker.check(refl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void onDisable() {
			try {
				GameWriter.writeGames(P.RAGEMODE_GAMES, P.RAGEMODE_GAMES_EXTENTION, refl.getRageModeGames());
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				refl.getLogger().writeLog();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Saver.saveActions(refl, P.ACTION_FILE);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	private void init() throws IOException {
		refl.init();
		execs.add(new AntiGriefListener(refl));
		execs.add(new Biome(refl));
		execs.add(new Broadcast(refl));
		execs.add(new Color(refl));
		execs.add(new CommandSpy(refl));
		execs.add(new CreateWorld(refl));
		execs.add(new DeleteWorld(refl));
		execs.add(new Home(refl));
		execs.add(new HP(refl));
		execs.add(new Ip(refl));
		execs.add(new ItemGive(refl));
		execs.add(new Nick(refl));
		execs.add(new Ping(refl));
		execs.add(new Pos1(refl));
		execs.add(new Pos2(refl));
		execs.add(new RageMode(refl));
		execs.add(new RageModeList(refl));
		execs.add(new RageModeSetup(refl));
		execs.add(new RageModeStart(refl));
		execs.add(new RageModeStats(refl));
		execs.add(new ReGen(refl));
		execs.add(new SaveLog(refl));
		execs.add(new Set(refl));
		execs.add(new SetHome(refl));
		execs.add(new Skin(refl));
		execs.add(new Speed(refl));
		execs.add(new Stats(refl));
		execs.add(new TeleportWorld(refl));
		execs.add(new Undo(refl));
		execs.add(new VanillaBlocker(refl));
		execs.add(new Vanish(refl));
		execs.add(new Walls(refl));
		execs.add(new WorldList(refl));
		for(CmdExec exec : execs) {
			exec.init();
		}
		listeners.add(new AntiGriefEvents(refl));
		listeners.add(new AxePosSetEvents(refl));
		listeners.add(new de.chrissx.server.events.CommandSpy(refl));
		listeners.add(new LabyDisabler());
		listeners.add(new PlayerDeathListener());
		listeners.add(new TeleportSignEvents());
		for(Ev l : listeners) {
			l.init();
		}
	}
}