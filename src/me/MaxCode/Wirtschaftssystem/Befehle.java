package me.MaxCode.Wirtschaftssystem;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Befehle implements CommandExecutor  {

	
	public String str = ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "Wirtschaftssystem" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY;
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		
		
		Player p = (Player) sender;
		
		if (cmd.getName().equalsIgnoreCase("ws")) {
			
			
			if (args.length == 0) {
				
				p.sendMessage(str + "Wirtschaftssystem Befehle:");
				p.sendMessage("");
				p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Spielerbefehle:");
				p.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "/ws geld -> " + ChatColor.GRAY + "Zeigt deinen Kontostand an.");
				p.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "/ws ueberweisen <name> <betrag> -> " + ChatColor.GRAY + "Ueberweist Geld an einen anderen Spieler.");
				p.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "/ws preis -> " + ChatColor.GRAY + "Oeffnet das Preis-Info-Menu.");
				p.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "/ws info an -> " + ChatColor.GRAY + "Zeigt dauerhafte Infos an.");
				p.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "/ws info aus -> " + ChatColor.GRAY + "Schlieﬂt die dauerhaften Infos.");
				
				if (p.isOp()) {		//Permission!!
					
				p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Adminbefehle:");
				p.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "/ws geld <name> -> " + ChatColor.GRAY + "Zeigt deinen Kontostand von anderen an.");
				p.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "/ws geld geben <name> <betrag> -> " + ChatColor.GRAY + "Gibt Spielern Geld.");
				p.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "/ws geld entfernen <name> <betrag> -> " + ChatColor.GRAY + "Entfernt Geld von Spielern.");
				p.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "/ws konto loeschen <name> -> " + ChatColor.GRAY + "Loescht ein Konto vom Spieler aus der DB.");
				p.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "/ws konto erstellen <name> -> " + ChatColor.GRAY + "Erstellt ein Konto von einem Spieler in der DB.");
				p.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "/ws reload -> " + ChatColor.GRAY + "Laed alle YML Daten neu.");
					
				}
				
				
			}
			
			
			
			
			
			///ws geld - Eigener Kontostand wird angezeigt
			if (args.length == 1) {
				
				if (args[0].equalsIgnoreCase("geld")) {
					
					KontoManager km = new KontoManager();
					int betrag = km.geldVonSpieler(p.getUniqueId());
					String waehrung = km.getWaehrung();
					
					p.sendMessage(str + "Du hast " + betrag + " " + waehrung + " auf deinem Konto.");
					
				}
				
			}
			
			
			
			
			//ws geld <name> - Kontostand von <name> wird angezeigt
			if (args.length == 2) {
				
				if (args[0].equalsIgnoreCase("geld")) {
				String spielername = args[1].toLowerCase();
				if (args[1].equalsIgnoreCase(spielername)) {
					
					UUID uuid = Bukkit.getPlayer(spielername).getUniqueId();
					
					KontoManager km = new KontoManager();
					int betrag = km.geldVonSpieler(uuid);
					String waehrung = km.getWaehrung();
					
					p.sendMessage(str + spielername + " hat " + betrag + " " + waehrung + " Bux auf seinem Konto.");
					
				}
				}
				
			}
			
			
			//ws geld geben <name> <betrag> - Zum "Cheaten"/geben von Geld
			if (args.length == 4) {
				
				if (args[0].equalsIgnoreCase("geld")) {
				if (args[1].equalsIgnoreCase("geben")) {
				String spielername = args[2].toLowerCase();
				if (args[2].equalsIgnoreCase(spielername)) {
				String betrag = args[3].toLowerCase();
				if (args[3].equalsIgnoreCase(betrag)) {
				if (p.isOp()) {	 //Permission!!
					
					int betragInt = Integer.parseInt(betrag);
					
					KontoManager km = new KontoManager();
					km.gutschreiben(spielername, betragInt);
					String waehrung = km.getWaehrung();
					
					p.sendMessage(str + "Du hast " + betrag + " " + waehrung + " auf das Konto von " + spielername + " gepackt!");
					
				}
				}
				}
				}
				}
				
			}
			
			
			//ws geld entfernen <name> <betrag> - Zum "Cheaten"/geben von Geld
			if (args.length == 4) {
				
				if (args[0].equalsIgnoreCase("geld")) {
				if (args[1].equalsIgnoreCase("entfernen")) {
				String spielername = args[2].toLowerCase();
				if (args[2].equalsIgnoreCase(spielername)) {
				String betrag = args[3].toLowerCase();
				if (args[3].equalsIgnoreCase(betrag)) {
				if (p.isOp()) {	 //Permission!!
					
					int betragInt = Integer.parseInt(betrag);
					
					KontoManager km = new KontoManager();
					km.abbuchen(spielername, betragInt);
					String waehrung = km.getWaehrung();
					
					p.sendMessage(str + "Du hast " + betrag + " " + waehrung +  " von dem Konto von " + spielername + " entfernt!");
					
				}
				}
				}
				}
				}
				
			}
			
			//ws ueberweisen <name> <betrag> - Damit kann Geld ueberweisen werden
			if (args.length == 3) {
				
				if (args[0].equalsIgnoreCase("ueberweisen")) {
				String spielername = args[1].toLowerCase();
				if (args[1].equalsIgnoreCase(spielername)) {
				String betrag = args[2].toLowerCase();
				if (args[2].equalsIgnoreCase(betrag)) {
				
					int betragint = Integer.parseInt(betrag);
					
					KontoManager km = new KontoManager();
					
					if (km.genugGuthaben(p.getName(), betragint) == true) {
					
						km.abbuchen(p.getName(), betragint);
						km.gutschreiben(spielername, betragint);
						
						p.sendMessage(str + "Du hast " + betrag + " an " + spielername + " ueberwiesen.");
					
					} else {
						p.sendMessage(str + "Du hast nicht genug Geld!");
					}
					
				}
				}
				}
				
				
			}
			
			
			
			//ws konto loeschen <name> - Konto eines Spielers wird geloescht
			if (args.length == 3) {
				
				if (args[0].equalsIgnoreCase("konto")) {
				if (args[1].equalsIgnoreCase("loeschen")) {
				String spielername = args[2].toLowerCase();
				if (args[2].equalsIgnoreCase(spielername)) {
				
					UUID uuid = Bukkit.getPlayer(spielername).getUniqueId();
					
					MySQLManager.loeschen(uuid);
					
					p.sendMessage(str + "Konto von " + spielername + " wurde geloscht!");
					
				}
				}
				}
				
				
			}
			
			
			//ws konto erstellen <name> - Konto eines Spielers wird erstellt
			if (args.length == 3) {
				
				if (args[0].equalsIgnoreCase("konto")) {
				if (args[1].equalsIgnoreCase("erstellen")) {
				String spielername = args[2].toLowerCase();
				if (args[2].equalsIgnoreCase(spielername)) {
				
					UUID uuid = Bukkit.getPlayer(spielername).getUniqueId();
					
					MySQLManager.erstellen(uuid);
					
					p.sendMessage(str + "Konto von " + spielername + " wurde erstellt!");
					
				}
				}
				}
				
				
			}
			
			
			
			//ws info an - Zeigt Scoreboardinfo an
			if (args.length == 2) {
				
				if (args[0].equalsIgnoreCase("info")) {
				if (args[1].equalsIgnoreCase("an")) {
				
					InfoManager im = new InfoManager();
					im.zeigeInfo(p);
					
					//Nachricht wird in InfoManager Klasse ausgegeben
					
				}
				}
				
				
			}
			
			
			//ws info an - Schlieﬂt Scoreboardinfo
			if (args.length == 2) {
				
				if (args[0].equalsIgnoreCase("info")) {
				if (args[1].equalsIgnoreCase("aus")) {
				
					InfoManager im = new InfoManager();
					im.schlieﬂeInfo(p);
					
					//Nachricht wird in InfoManager Klasse ausgegeben
					
				}
				}
				
				
			}
			
			
			
			//ws preis - Oeffnet das Preismenu
			if (args.length == 1) {
				
				if (args[0].equalsIgnoreCase("preis")) {

					PreisManager pm = new PreisManager();
					pm.openPreisMenu(p);
					
					
				}	
				
				
			}
			
			
			
			
			//ws reload - Laed YML Files neu
			if (args.length == 1) {
				
				if (args[0].equalsIgnoreCase("reload")) {

					FileManager fm = new FileManager();
					fm.reloadFiles();
					
					p.sendMessage(str + "Du hast alle YML Daten erfolgreich neu geladen!");
					
					
				}	
				
				
			}
			
			
			
			
			
		}
		
		
		
		
		
		
		return false;
	}

}
