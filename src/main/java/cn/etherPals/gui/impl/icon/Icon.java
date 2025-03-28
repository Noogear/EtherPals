package cn.etherPals.gui.impl.icon;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface Icon {
    Icon onClick(InventoryClickEvent event);

    ItemStack display();

    @Nullable UUID parsePlayerId();

}
