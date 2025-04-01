package cn.etherPals.gui.impl.icon;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MultiIcon implements Icon {
    protected List<Consumer<InventoryClickEvent>> clickActions;
    protected List<Supplier<ItemStack>> icons;
    protected Map<Integer, Consumer<InventoryClickEvent>> clickHandler;
    private int index;

    public MultiIcon(List<Consumer<InventoryClickEvent>> clickActions, List<Supplier<ItemStack>> icons) {
        this.clickActions = clickActions;
        this.icons = icons;
        clickHandler = new HashMap<>();
        index = 0;
    }

    public MultiIcon() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    @Override
    public Icon onClick(InventoryClickEvent event, int slot) {
        Consumer<InventoryClickEvent> clickAction = clickHandler.get(slot);
        if (clickAction == null) {
            event.setCancelled(true);
            return this;
        }
        clickAction.accept(event);
        return this;
    }

    @Override
    public @Nullable ItemStack render(int slot) {
        if (index <= icons.size()) {
            clickHandler.put(slot, clickActions.get(index));
            index++;
            return icons.get(slot).get();
        } else {
            index++;
            return null;
        }
    }

    public MultiIcon init() {
        clickActions.clear();
        index = 0;
        return this;
    }

    public int addIndex(int amount) {
        index = Math.max(index + amount, 0);
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public MultiIcon add(ItemStack itemStack, Consumer<InventoryClickEvent> clickAction) {
        icons.add(() -> itemStack);
        clickActions.add(clickAction);
        return this;
    }

}
