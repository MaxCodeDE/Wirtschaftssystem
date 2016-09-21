package me.MaxCode.Wirtschaftssystem;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class Events implements Listener {

	
	
	public String str = ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "Wirtschaftssystem" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY;
	
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		UUID uuid = p.getUniqueId();
		
		if (!MySQLManager.spielerInDB(uuid)) {
			
			MySQLManager.erstellen(uuid);
			System.out.println("[Wirtschaftssystem] Der Spieler " + p.getName() + " hat noch kein Konto und es wurde eins erstellt.");
			
		}
		
	}
	
	
	
	@EventHandler
	public void onPreisMenuClick(InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked();
		
		if (e.getInventory().getTitle().equals(str)) {
			e.setCancelled(true);
		if (e.getSlotType() != SlotType.OUTSIDE) {
			ItemStack info = new ItemStack(Material.BOOK);
		if (e.getCurrentItem() != null && e.getCurrentItem() != info) {
			
			ItemStack item = e.getCurrentItem();
			PreisManager pm = new PreisManager();
			
			item = pm.convertItemStackToOne(item);
			
			if (pm.isInFile(item)) {
			
				int preis = pm.getPreis(item);
				p.sendMessage(str + "Das Item " + item.getType().toString() + " kostet " + preis);
			
			} else {
				p.sendMessage(str + "Das Item ist dem System nicht bekannt!");
			}
			
		}
		}
		}
		
		
	}
	
	
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		
		EventManager em = new EventManager();
		em.geldBeiKillEvent(e);
		em.geldBeiTodEvent(e);
		
	}
	
	
	
}
