package me.MaxCode.Wirtschaftssystem;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class KontoManager {
	


	public void gutschreiben(String spielername, int betrag) {
		
		UUID uuid = Bukkit.getPlayer(spielername).getUniqueId();
		
		MySQLManager.update(uuid, betrag, true);
		
		
	}
	
	
	public void abbuchen(String spielername, int betrag) {
		
		UUID uuid = Bukkit.getPlayer(spielername).getUniqueId();
		
		MySQLManager.update(uuid, betrag, false);
		
		
	}
	
	
	
	public boolean genugGuthaben(String spielername, int betrag) {
		
		boolean spielerGenugGuthaben = false;
		
		UUID uuid = Bukkit.getPlayer(spielername).getUniqueId();
		
		int guthabenVonSpieler = MySQLManager.getKontostand(uuid);
		
		if (betrag <= guthabenVonSpieler) {
			
			spielerGenugGuthaben = true;
			
			
		}
		
		
		return spielerGenugGuthaben;
		
	}
	
	
	public int geldVonSpieler(UUID uuid) {
		
		int betrag = MySQLManager.getKontostand(uuid);; 
		
		
		return betrag;
		
	}
	
	
	
	public int getStartkapital() {
		
		FileConfiguration cfg = FileManager.allgemein;
		
		int startkapital = Integer.parseInt(cfg.getString("Allgemein." + "Startkapital"));
		
		return startkapital;
		
	}
	
	
	public String getWaehrung() {
		
		FileConfiguration cfg = FileManager.allgemein;
		
		String waehrung = cfg.getString("Allgemein." + "Waehrung");
		
		return waehrung;
	}
	
	
}
