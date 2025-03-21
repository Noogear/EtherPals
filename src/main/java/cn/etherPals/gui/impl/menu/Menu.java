package cn.etherPals.gui.impl.menu;

import cn.etherPals.gui.impl.icon.Icon;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;

public class Menu implements InventoryHolder {
    protected Inventory inventoryCache;
    protected final Map<Integer, Icon> slotMap;
    protected final UUID playerId;

    @Override
    public @NotNull Inventory getInventory() {
        return null;
    }

    public void onClick(InventoryClickEvent event) {
        Inventory topInv = event.getView().getTopInventory();
        if (!topInv.equals(event.getClickedInventory())) {
            event.setCancelled(true);
            return;
        }
        Icon icon = slotMap.get(event.getSlot());
        if(icon == null) {
            event.setCancelled(true);
            return;
        }
        icon.onClick(event);
    }

    public void onDrag(InventoryDragEvent event) {
    }

    public void onOpen(InventoryOpenEvent event) {
    }

    public void onClose(InventoryCloseEvent event) {
    }

    public void onQuit(PlayerQuitEvent event) {
    }
}
