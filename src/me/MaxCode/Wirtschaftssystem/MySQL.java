package me.MaxCode.Wirtschaftssystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.configuration.file.FileConfiguration;

public class MySQL {

	public static String host;
	public static String port;
	public static String database;
	public static String username;
	public static String password;
	public static Connection con;
	
	
	
	public static void connect() {
		
		if (!isConnected()) {
			
			try {
				con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
				System.out.println("[Wirtschaftssystem] Verbindng zur MySQL aufgebaut!");
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}
	
	
	public static void disconnect() {
		
		if (isConnected()) {
			
			try {
				con.close();
				System.out.println("[Wirtschaftssystem] Verbindng zur MySQL geschlossen!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	
	
	public static boolean isConnected() {
		return (con == null ? false : true);
	
	}
	
	
	public static Connection getConnection() {
		return con;
	}
	
	
	
	public static void setupFile() {
		
		FileConfiguration cfg = FileManager.mysql;
		
		String hostcfg = cfg.getString("MySQL." + "Host");
		String portcfg = cfg.getString("MySQL." + "Port");
		String databasecfg = cfg.getString("MySQL." + "Database");
		String usernamecfg = cfg.getString("MySQL." + "Username");
		String passwordcfg = cfg.getString("MySQL." + "Password");
		
		host = hostcfg;
		port = portcfg;
		database = databasecfg;
		username = usernamecfg;
		password = passwordcfg;
		
		
	}
	
	
	
	
	
}
