package de.chrissx.server.shared;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RageModeGame extends Game {
	
	public static final ItemStack BOW() {
		ItemStack i = new ItemStack(Material.BOW, 1);
		i.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		i.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
		i.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 1);
		return i;
	}
	
	public static final ItemStack KNIFE() {
		ItemStack i = new ItemStack(Material.IRON_SWORD, 1);
		i.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		i.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 1);
		return i;
	}
	
	public static final ItemStack AXE() {
		ItemStack i = new ItemStack(Material.IRON_AXE, 1);
		i.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		i.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 1);
		return i;
	}
	
	public static final ItemStack ARROW = new ItemStack(Material.ARROW, 1);

	public RageModeGame(String name, Location lobby, Location arena, Location back) {
		super(name, lobby, arena, back);
		Inventory inv = Bukkit.createInventory(null, InventoryType.PLAYER);
		inv.setItem(0, BOW());
		inv.setItem(1, KNIFE());
		inv.setItem(2, AXE());
		inv.setItem(9, ARROW);
		setStartItems(inv);
	}
}