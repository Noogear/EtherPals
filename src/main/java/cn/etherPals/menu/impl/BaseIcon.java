package cn.etherPals.menu.impl;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public interface BaseIcon {
    BaseIcon onClick(InventoryClickEvent event,int slot);
    BaseIcon onClick(InventoryClickEvent event);
    ItemStack render();
    ItemStack render(int slot);
}
