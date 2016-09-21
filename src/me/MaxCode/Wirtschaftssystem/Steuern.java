package me.MaxCode.Wirtschaftssystem;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Steuern {

	public Wirtschaftssystem ws;
	
	
	public Steuern(Wirtschaftssystem class1) {
		ws = class1;
	}
	
	
	public void runSteuern() {
		
		//Nicht am Ende, falls der rest Fehler hat
		runWait();
		
		//Nicht aus FileManager klasse, da wert aktuell sein muss
		File file = new File("plugins/Wirtschaftssystem/" + "Steuern.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		int durchlauf = cfg.getInt("Steuern." + ".Durchlauf");
		durchlauf--;
		
		cfg.set("Steuern." + ".Durchlauf", durchlauf);
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (durchlauf == 0) {
			
			//Steuern abrechnen von jedem Spieler
			
			
		}
		
		
		
	}
	
	
	
	
	public void runWait() {
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(ws, new Runnable() {
				
			public void run() {
			
				runSteuern();
				
			}
				
		}, 72000); //72000 ticks = 1 Stunde
		
	}
	
	
	
	
	
	
	
	
}
