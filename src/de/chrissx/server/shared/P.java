package de.chrissx.server.shared;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class P {
	
	//
	//GENERAL
	//
	public static final Path SERVER_FOLDER = Paths.get(new File("").getAbsoluteFile().getAbsolutePath().toString());
	public static final String CUSTOM_PLUGINS_FOLDER = Paths.get(SERVER_FOLDER.toString(), "chrissx-plugins").toString();
	public static final Path LOG_FOLDER = Paths.get(CUSTOM_PLUGINS_FOLDER, "logs");
	public static final String LOG_EXTENTION = ".log";
	public static final Path CONFIG_PATH = Paths.get(CUSTOM_PLUGINS_FOLDER, "config");
	public static final String CONFIG_EXTENTION = ".config";
	
	//
	//RAGEMODE
	//
	public static final String RAGEMODE_BASE_PATH = Paths.get(CUSTOM_PLUGINS_FOLDER, "RageMode").toString();
	public static final Path RAGEMODE_GAMES = Paths.get(RAGEMODE_BASE_PATH, "games");
	public static final String RAGEMODE_GAMES_EXTENTION = ".rmg";
	public static final Path RAGEMODE_STATS = Paths.get(RAGEMODE_BASE_PATH, "stats");
	public static final String RAGEMODE_STATS_EXTENTION = ".stats";
	
	//
	//ANTIGRIEF
	//
	public static final Path ANTIGRIEF_PLAYER_PATH = Paths.get(CUSTOM_PLUGINS_FOLDER, "ag-listeners");
	public static final String ANTIGRIEF_PLAYER_EXTENTION = ".agp";
	public static final Path ANTIGRIEF_TNT_OUT_FILE = Paths.get(CONFIG_PATH.toString(), "tnt_chat_out" + CONFIG_EXTENTION);
	public static final Path ANTIGRIEF_TNT_KICK_FILE = Paths.get(CONFIG_PATH.toString(), "tnt_auto_kick" + CONFIG_EXTENTION);
	
	//
	//COMMANDSPY
	//
	public static final Path CMDSPY_LISTENERS_PATH = Paths.get(CUSTOM_PLUGINS_FOLDER, "cmdspy-listeners");
	public static final String CMDSPY_LISTENERS_EXTENTION = ".cmdspy";
	
	//
	//RANDOMCOMMANDS
	//
	public static final Path PLAYER_HOMES = Paths.get(CUSTOM_PLUGINS_FOLDER, "player_homes");
	public static final String PLAYER_HOMES_EXTENTION = ".home";
	public static final Path TP_SIGN_LOCATIONS = Paths.get(CUSTOM_PLUGINS_FOLDER, "tp-signs");
	public static final String TP_SIGN_EXTENTION = ".tploc";
	
	public static final String ACTION_EXTENTION = ".actions";
	public static final File ACTION_FILE = Paths.get(CUSTOM_PLUGINS_FOLDER, "last_actions" + ACTION_EXTENTION).toFile();
}
