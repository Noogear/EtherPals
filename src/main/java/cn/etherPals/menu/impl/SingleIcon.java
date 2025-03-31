package cn.etherPals.menu.impl;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class SingleIcon implements BaseIcon {
    protected Consumer<InventoryClickEvent> clickAction;
    protected ItemStack display;

    public SingleIcon(ItemStack display, Consumer<InventoryClickEvent> clickAction) {
        this.display = display;
        this.clickAction = clickAction;
    }

    public SingleIcon(ItemStack display) {
        this(display, null);
    }

    @Override
    public BaseIcon onClick(InventoryClickEvent event, int slot) {
        return onClick(event);
    }

    @Override
    public BaseIcon onClick(InventoryClickEvent event) {
        if (clickAction == null) {
            event.setCancelled(true);
            return this;
        }
        clickAction.accept(event);
        return this;
    }

    @Override
    public ItemStack render() {
        return display;
    }

    @Override
    public ItemStack render(int slot) {
        return display;
    }
}
