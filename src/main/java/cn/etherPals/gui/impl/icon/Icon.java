package cn.etherPals.gui.impl.icon;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public interface Icon {

    Icon onClick(InventoryClickEvent event, int slot);

    ItemStack render(int slot);

}
