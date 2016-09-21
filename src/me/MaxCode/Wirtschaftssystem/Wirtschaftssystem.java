package me.MaxCode.Wirtschaftssystem;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Wirtschaftssystem extends JavaPlugin {

	
	
	public void onEnable () {
		
		FileManager fm = new FileManager();
		fm.checkFiles();
		fm.setupFiles();
		
		setupMySQL();
		
		Steuern s = new Steuern(this);
		s.runWait();
		
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new Events(), this);
		
		this.getCommand("ws").setExecutor(new Befehle());
		
		System.out.println("[Wirtschaftssystem] wurde erfolgreich geladen!");
		
	}
	
	
	
	public void onDisable() {	
		MySQL.disconnect();
	}
	
	
	
	public void setupMySQL() {
		
		
		MySQL.setupFile();
		
		MySQL.connect();
		
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Konten (UUID VARCHAR(100), Spielername VARCHAR(100), Kontostand INT(100))");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}
	
	
}
