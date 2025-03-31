package cn.etherPals.menu.impl;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class MultiIcon implements BaseIcon {
    protected List<Function<InventoryClickEvent, Boolean>> clickActions;
    protected List<Supplier<ItemStack>> icons;
    protected Map<Integer, Function<InventoryClickEvent, Boolean>> clickHandler;
    private int index;

    @Override
    public BaseIcon onClick(InventoryClickEvent event, int slot) {
        Function<InventoryClickEvent, Boolean> clickAction = clickHandler.get(slot);
        if (clickAction == null) {
            event.setCancelled(true);
            return this;
        }
        clickAction.apply(event);
        return this;
    }

    @Override
    public BaseIcon onClick(InventoryClickEvent event) {
        return onClick(event, event.getSlot());
    }

    @Override
    public ItemStack render() {
        return icons.get(index++).get();
    }

    @Override
    public ItemStack render(int slot) {
        clickHandler.put(slot, clickActions.get(index));
        index++;
        return icons.get(slot).get();
    }

    public MultiIcon initActions() {
        clickActions.clear();
        return this;
    }

    public int addIndex(int amount) {
        return index += amount;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
