package cn.etherPals.gui.impl.icon;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class SingleIcon implements Icon {
    protected Consumer<InventoryClickEvent> clickAction;
    protected Supplier<ItemStack> display;

    public SingleIcon(ItemStack display, Consumer<InventoryClickEvent> clickAction) {
        this.display = () -> display;
        this.clickAction = clickAction;
    }

    public SingleIcon(ItemStack display) {
        this(display, null);
    }

    public SingleIcon() {
        this(null, null);
    }

    @Override
    public Icon onClick(InventoryClickEvent event, int slot) {
        if (clickAction == null) {
            event.setCancelled(true);
            return this;
        }
        clickAction.accept(event);
        return this;
    }

    @Override
    public @Nullable ItemStack render(int slot) {
        return display.get();
    }
}
