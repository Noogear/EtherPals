package cn.etherPals.gui.impl.icon;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class DynamicIcon implements Icon {
    protected Consumer<InventoryClickEvent> clickAction;

    @Override
    public void onClick(InventoryClickEvent event) {
        if (clickAction == null) {
            event.setCancelled(true);
            return;
        }
        clickAction.accept(event);
    }

    @Override
    public ItemStack display() {
        return null;
    }
}
