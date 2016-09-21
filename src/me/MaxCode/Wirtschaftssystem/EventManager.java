package me.MaxCode.Wirtschaftssystem;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

public class EventManager {

	public String str = ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "Wirtschaftssystem" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY;
	
	
	
	public void geldBeiKillEvent(PlayerDeathEvent e) {
		
		
		FileConfiguration cfg = FileManager.events;
		
		if (cfg.get("Events." + "Geld bei Kill." + "Status").equals("an")) {
		
		int betrag = cfg.getInt("Events." + "Geld bei Kill." + "Betrag");
		Player killer = e.getEntity().getKiller();
		Player p = e.getEntity().getPlayer();
		
		KontoManager km = new KontoManager();
		String waehrung = km.getWaehrung();
		
		if (killer instanceof Player && p instanceof Player) {
			
			km.gutschreiben(killer.getName(), betrag);
			
			if (cfg.get("Events." + "Geld bei Kill." + "Chat Nachricht").equals("an")) {
				killer.sendMessage(str + "Du hast " + betrag + " " + waehrung + " bekommen!");
			}
			
		}
		}
		
	}
	
	
	
	public void geldBeiTodEvent(PlayerDeathEvent e) {
		
		
		FileConfiguration cfg = FileManager.events;
		
		if (cfg.get("Events." + "Geld bei Tod." + "Status").equals("an")) {
		
		int betrag = cfg.getInt("Events." + "Geld bei Tod." + "Betrag");
		Player killer = e.getEntity().getKiller();
		Player p = e.getEntity().getPlayer();
		
		KontoManager km = new KontoManager();
		String waehrung = km.getWaehrung();
		
		if (killer instanceof Player && p instanceof Player) {
			
			km.abbuchen(p.getName(), betrag);
			
			if (cfg.get("Events." + "Geld bei Kill." + "Chat Nachricht").equals("an")) {
				p.sendMessage(str + "Du hast " + betrag + " " + waehrung + " verloren!");
			}
			
		}
		}
		
	}
	
	
	
	
	
	
	
	
	
}
