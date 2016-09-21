package me.MaxCode.Wirtschaftssystem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class FileManager {

	
	public static FileConfiguration mysql;
	public static FileConfiguration allgemein;
	public static FileConfiguration preislisteGI;
	public static FileConfiguration preislisteCI;
	public static FileConfiguration events;
	public static FileConfiguration steuern;
	
	
	
	public void setupFiles() {
		
		File file1 = new File("plugins/Wirtschaftssystem/" + "MySQL.yml");
		mysql = YamlConfiguration.loadConfiguration(file1);
		
		File file2 = new File("plugins/Wirtschaftssystem/" + "Allgemein.yml");
		allgemein = YamlConfiguration.loadConfiguration(file2);
		
		File file3 = new File("plugins/Wirtschaftssystem/" + "Preisliste_Grunditems.yml");
		preislisteGI = YamlConfiguration.loadConfiguration(file3);
		
		File file4 = new File("plugins/Wirtschaftssystem/" + "Preisliste_Craftingitems.yml");
		preislisteCI = YamlConfiguration.loadConfiguration(file4);
		
		File file5 = new File("plugins/Wirtschaftssystem/" + "Events.yml");
		events = YamlConfiguration.loadConfiguration(file5);
		
		File file6 = new File("plugins/Wirtschaftssystem/" + "Steuern.yml");
		steuern = YamlConfiguration.loadConfiguration(file6);
		
		
		
	}
	
	public void reloadFiles() {
		setupFiles();
	}
	
	
	public void checkFiles() {
		
		ArrayList<String> daten = new ArrayList<String>();
		
		daten.add("MySQL.yml");
		daten.add("Allgemein.yml");
		daten.add("Preisliste_Grunditems.yml");
		daten.add("Preisliste_Craftingitems.yml");
		daten.add("Events.yml");
		daten.add("Steuern.yml");
		
		for (String s : daten) {
			
			
			File file = new File("plugins/Wirtschaftssystem/" + s);
			FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
			
			if (!file.exists()) {
				try {
					cfg.save(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				if (file.getName().equals("MySQL.yml")) {
					this.mySQLBeschreiben();
				}
				if (file.getName().equals("Allgemein.yml")) {
					this.allgemeinBeschreiben();
				}
				if (file.getName().equals("Preisliste_Grunditems.yml")) {
					this.preislisteGrunditemsBeschreiben();
				}
				if (file.getName().equals("Preisliste_Craftingitems.yml")) {
					this.preislisteCraftingitemsBeschreiben();
				}
				if (file.getName().equals("Events.yml")) {
					this.eventsBeschreiben();
				}
				if (file.getName().equals("Steuern.yml")) {
					this.steuernBeschreiben();
				}
				
				
				
			} 

		}
		
		
		
	}
	
	
	
	public void mySQLBeschreiben() {
		
		
		File file = new File("plugins/Wirtschaftssystem/" + "MySQL.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		
		cfg.set("MySQL." + "Host", "localhost");
		cfg.set("MySQL." + "Port", "3306");
		cfg.set("MySQL." + "Database", "wirtschaftssystem");
		cfg.set("MySQL." + "Username", "root");
		cfg.set("MySQL." + "Password", "");
		
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public void allgemeinBeschreiben() {
		
		
		File file = new File("plugins/Wirtschaftssystem/" + "Allgemein.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		
		cfg.set("Allgemein." + "Startkapital", "100");
		cfg.set("Allgemein." + "Waehrung", "Bux");
		cfg.set("Allgemein." + "Scoreboard-Info anzeigen", "an");
		
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public void preislisteGrunditemsBeschreiben() {
		
		File file = new File("plugins/Wirtschaftssystem/" + "Preisliste_Grunditems.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		cfg.set("Preisliste." + "0" + ".Item", "LOG");
		cfg.set("Preisliste." + "0" + ".Meta", 0);
		cfg.set("Preisliste." + "0" + ".Preis", 100);
		
		cfg.set("Preisliste." + "1" + ".Item", "LOG");
		cfg.set("Preisliste." + "1" + ".Meta", 1);
		cfg.set("Preisliste." + "1" + ".Preis", 100);
		
		cfg.set("Preisliste." + "2" + ".Item", "LOG");
		cfg.set("Preisliste." + "2" + ".Meta", 2);
		cfg.set("Preisliste." + "2" + ".Preis", 100);
		
		cfg.set("Preisliste." + "3" + ".Item", "LOG");
		cfg.set("Preisliste." + "3" + ".Meta", 3);
		cfg.set("Preisliste." + "3" + ".Preis", 100);
		
		cfg.set("Preisliste." + "4" + ".Item", "STICK");
		cfg.set("Preisliste." + "4" + ".Meta", 0);
		cfg.set("Preisliste." + "4" + ".Preis", 10);
		
		cfg.set("Preisliste." + "5" + ".Item", "COBBLESTONE");
		cfg.set("Preisliste." + "5" + ".Meta", 0);
		cfg.set("Preisliste." + "5" + ".Preis", 20);
		
		cfg.set("Preisliste." + "6" + ".Item", "IRON_INGOT");
		cfg.set("Preisliste." + "6" + ".Meta", 0);
		cfg.set("Preisliste." + "6" + ".Preis", 500);
		
		
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	public void preislisteCraftingitemsBeschreiben() {
		
		File file = new File("plugins/Wirtschaftssystem/" + "Preisliste_Craftingitems.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		
		cfg.set("Preisliste." + "0" + ".Item", "IRON_SWORD");
		cfg.set("Preisliste." + "0" + ".Meta", "0");
		cfg.set("Preisliste." + "0" + ".Bestandteile" + ".1", "STICK");
		cfg.set("Preisliste." + "0" + ".Bestandteile" + ".2", "IRON_INGOT");
		cfg.set("Preisliste." + "0" + ".Bestandteile" + ".3", "IRON_INGOT");
		
		cfg.set("Preisliste." + "1" + ".Item", "STONE_SWORD");
		cfg.set("Preisliste." + "1" + ".Meta", "0");
		cfg.set("Preisliste." + "1" + ".Bestandteile" + ".1", "STICK");
		cfg.set("Preisliste." + "1" + ".Bestandteile" + ".2", "COBBLESTONE");
		cfg.set("Preisliste." + "1" + ".Bestandteile" + ".3", "COBBLESTONE");
		
		
		
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public void eventsBeschreiben() {
		
		File file = new File("plugins/Wirtschaftssystem/" + "Events.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		
		cfg.set("Events." + "Geld bei Kill." + "Status", "an");
		cfg.set("Events." + "Geld bei Kill." + "Betrag", 500);
		cfg.set("Events." + "Geld bei Kill." + "Chat Nachricht", "an");
		
		cfg.set("Events." + "Geld bei Tod." + "Status", "an");
		cfg.set("Events." + "Geld bei Tod." + "Betrag", 500);
		cfg.set("Events." + "Geld bei Tod." + "Chat Nachricht", "an");
		
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	public void steuernBeschreiben() {
		
		File file = new File("plugins/Wirtschaftssystem/" + "Steuern.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		
		cfg.set("Steuern." + ".Status", "an");
		cfg.set("Steuern." + ".Betrag pro Monat", 100);
		cfg.set("Steuern." + ".Chat Nachricht", "an");
		
		cfg.set("Steuern." + ".Durchlauf", 720);
		
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	
	
	
}
