package me.MaxCode.Wirtschaftssystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class InfoManager {

	public String str = ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "Wirtschaftssystem" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY;
	
	
	public void zeigeInfo(Player p) {
		
		
		FileConfiguration cfg = FileManager.allgemein;
		if (cfg.get("Allgemein." + "Scoreboard-Info anzeigen").equals("an")) {
			
			
		
		ScoreboardManager m = Bukkit.getScoreboardManager();
		
		Scoreboard b = m.getNewScoreboard();
		
		Objective o = b.registerNewObjective("test", "test2");
		
		o.setDisplayName(str);
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		//Freie zeile
		Score s0 = o.getScore("");
		s0.setScore(4);
		
		
		//Kontostand
		KontoManager km = new KontoManager();
		int kontostand = km.geldVonSpieler(p.getUniqueId());
		Score s1 = o.getScore(ChatColor.GRAY + "Geld: " + ChatColor.RED + kontostand);
		s1.setScore(3);
		
		//Waehrungsname
		String waehrung = km.getWaehrung();
		Score s2 = o.getScore(ChatColor.GRAY + "Waehrung: " + ChatColor.RED + waehrung);
		s2.setScore(2);
		
		//System - Geht leider nicht, da Verbundung zur DB beim Start aufgebaut wird und deswegen immer true zurueckt gibt. Sieht aber gut aus!
		String isOnline = "";
		if (MySQL.isConnected()) {
			isOnline = "Online";
		} else {
			isOnline = "Offline";
		}
		Score s3 = o.getScore(ChatColor.GRAY + "System: " + ChatColor.RED + isOnline);
		s3.setScore(1);
		
		
		
		
		
		
		
		
		p.setScoreboard(b);
		p.sendMessage(str + "Info wird jetzt angezeigt!");
		
		} else {
			p.sendMessage(str + "Die Info ist zurzeit deaktiviert!");
		}
		
	}
	
	
	
	public void schlieﬂeInfo(Player p) {
		
		p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		p.sendMessage(str + "Info wird jetzt nichtmehr angezeigt!");
		
	}
	
	
	
}
