package me.MaxCode.Wirtschaftssystem;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class API {

	
	
	
	public int getPreisVonItem(ItemStack item) {
		
		PreisManager pm = new PreisManager();
		int preis = pm.getPreis(item);
		
		return preis;
	}
	
	
	public int getKontostand(Player p) {
		
		KontoManager km = new KontoManager();
		int kontostand = km.geldVonSpieler(p.getUniqueId());
		
		return kontostand;
	}
	
	
	public void spielerKontoAbbuchen(Player p, int betrag) {
		
		KontoManager km = new KontoManager();
		km.abbuchen(p.getName(), betrag);
		
	}
	
	
	public void spielerKontoGutschreiben(Player p, int betrag) {
		
		KontoManager km = new KontoManager();
		km.gutschreiben(p.getName(), betrag);
		
	}
	
	
	
	
	
	
}
