package me.MaxCode.Wirtschaftssystem;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PreisManager {

	
	public String str = ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "Wirtschaftssystem" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY;
	
	
	
	
	public int getGrundpreis(ItemStack item) {
		
		int preis = 0;
		
		FileConfiguration cfg = FileManager.preislisteGI;
		
		for (String s : cfg.getConfigurationSection("Preisliste.").getKeys(false)) {
			
			Material m = Material.getMaterial(cfg.getString("Preisliste." + s + ".Item"));
			int meta = cfg.getInt("Preisliste." + s + ".Meta");
			
			ItemStack craftableItem = new ItemStack(m, 1, (short) meta);
			
			if (item.equals(craftableItem)) {			
				preis = cfg.getInt("Preisliste." + s + ".Preis");
			}
		}
		
		return preis;
		
	}
	
	
	
	
	
	
	public int getPreis(ItemStack item) {
		
		int preis = 0;
		
		if (this.isCraftable(item)) {
			
			FileConfiguration cfg = FileManager.preislisteCI;
			
			for (String s : cfg.getConfigurationSection("Preisliste.").getKeys(false)) {
				
				Material m = Material.getMaterial(cfg.getString("Preisliste." + s + ".Item"));
				int meta = cfg.getInt("Preisliste." + s + ".Meta");
				
				ItemStack craftableItem = new ItemStack(m, 1, (short) meta);
				
				if (item.equals(craftableItem)) {
					
					for (String s2 : cfg.getConfigurationSection("Preisliste." + s + ".Bestandteile").getKeys(false)) {
						
						Material m2 = Material.getMaterial(cfg.getString("Preisliste." + s + ".Bestandteile" + "." + s2));
						ItemStack item2 = new ItemStack(m2, 1, (short) 0);
						
						int itemPreis = this.getGrundpreis(item2);
						
						preis = preis + itemPreis;
						
					}
					
					
				}
				
				
				
			}
			
			
		} else {
			
			preis = this.getGrundpreis(item);
			
		}
		
		

		
		
		return preis;
		
	}
	
	
	
	public boolean isCraftable(ItemStack item) {
		
		boolean isCraftableItem = false;
		
		FileConfiguration cfg = FileManager.preislisteCI;
		
		
		for (String s : cfg.getConfigurationSection("Preisliste.").getKeys(false)) {
			
			Material m = Material.getMaterial(cfg.getString("Preisliste." + s + ".Item"));
			int meta = cfg.getInt("Preisliste." + s + ".Meta");
			
			ItemStack craftableItem = new ItemStack(m, 1, (short) meta);
			
			if (item.equals(craftableItem)) {
				isCraftableItem = true;
			}
			
			
		}
		
		
		return isCraftableItem;
	}
		
	
	
	
	
	public boolean isInFile(ItemStack item) {
		
		boolean ItemIsInFile = false;
		
		FileConfiguration pg = FileManager.preislisteGI;
		FileConfiguration pc = FileManager.preislisteCI;
		
		if (this.isCraftable(item)) {
			
			
			for (String s : pc.getConfigurationSection("Preisliste.").getKeys(false)) {
				
				Material m = Material.getMaterial(pc.getString("Preisliste." + s + ".Item"));
				int meta = pc.getInt("Preisliste." + s + ".Meta");
				
				ItemStack fileItem = new ItemStack(m, 1, (short) meta);
				
				if (item.equals(fileItem)) {				
					ItemIsInFile = true;
				}
			
			}
			
			
			
		} else {
			
			
			for (String s : pg.getConfigurationSection("Preisliste.").getKeys(false)) {
				
				Material m = Material.getMaterial(pg.getString("Preisliste." + s + ".Item"));
				int meta = pg.getInt("Preisliste." + s + ".Meta");
				
				ItemStack fileItem = new ItemStack(m, 1, (short) meta);
				
				if (item.equals(fileItem)) {				
					ItemIsInFile = true;
				}
			
			}
			
			
		}

		
		

		
		return ItemIsInFile;
		
	}
	
	
	
	public void openPreisMenu(Player p) {
		
		
		Inventory inv = Bukkit.createInventory(p, 9, str);
		
		ItemStack info = new ItemStack(Material.BOOK);
		ItemMeta meta = info.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Info");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "Klicke auf das Item ueber welches");
		lore.add(ChatColor.GRAY + "du den Preis erfahren willst.");
		meta.setLore(lore);
		info.setItemMeta(meta);
		
		inv.setItem(4, info);
		
		
		
		
		
		p.openInventory(inv);
		
		
	}
	
	
	
	@SuppressWarnings("deprecation")
	public ItemStack convertItemStackToOne(ItemStack item) {
		
		item = new ItemStack(item.getType(), 1 , item.getData().getData());
		
		return item;
		
	}
		
		
		
		
	}
