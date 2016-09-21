package me.MaxCode.Wirtschaftssystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MySQLManager {

	
	public static boolean spielerInDB(UUID uuid) {
		
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT Kontostand FROM Konten WHERE UUID = ?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	public static void update(UUID uuid, int betrag, boolean hinzu) {
		
		int kontostand = getKontostand(uuid);
		
		int betragNeu = 0;
		
		if (hinzu) {
			betragNeu = kontostand + betrag;
		} else {
			betragNeu = kontostand - betrag;
		}
		
		
		if (spielerInDB(uuid)) {
			
			try {
				PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE Konten SET Kontostand = ? WHERE UUID = ?");
				ps.setInt(1, betragNeu);
				ps.setString(2, uuid.toString());
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		} else {
			
			
			erstellen(uuid);
			
		}
		
		
		
	}
	
	
	public static void loeschen(UUID uuid) {
		
		if (spielerInDB(uuid)) {
			
			try {
				PreparedStatement ps = MySQL.getConnection().prepareStatement("DELETE Konten FROM Konten WHERE UUID = ?");
				ps.setString(1, uuid.toString());
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		} else {
			
		}
		
	}
	
	public static void erstellen(UUID uuid) {
		
		Player p = Bukkit.getPlayer(uuid);
		
		KontoManager km = new KontoManager();
		
		int startkapital = km.getStartkapital();
		
		if (spielerInDB(uuid)) {
			
			
			
			
		} else {
			
			try {
				PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO Konten (UUID, Spielername, Kontostand) VALUES (?, ?, ?)");
				ps.setString(1, uuid.toString());
				ps.setString(2, p.getName());
				ps.setInt(3, startkapital);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
		
		
	}
	
	public static int getKontostand(UUID uuid) {
	
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT Kontostand FROM Konten WHERE UUID = ?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				return rs.getInt("Kontostand");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
		
	}
	
}
