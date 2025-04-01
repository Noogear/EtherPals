package cn.etherPals.gui.impl.menu;

import cn.etherPals.gui.impl.display.MenuDisplay;
import cn.etherPals.gui.impl.icon.Icon;
import cn.etherPals.util.AdventureUtil;
import cn.etherPals.util.scheduler.XRunnable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Supplier;

public class Menu implements InventoryHolder {
    protected final Map<Integer, Icon> slotMap;
    protected final UUID playerId;
    protected final Map<Character, List<Integer>> layoutSlotMap;
    protected MenuDisplay display;
    protected Inventory inventoryCache;

    public Menu(@NotNull Player player) {
        this(player, new MenuDisplay());
    }

    public Menu(@NotNull Player player, @NotNull Supplier<MenuDisplay> displaySupplier) {
        this(player, displaySupplier.get());
    }

    public Menu(@NotNull Player player, @NotNull MenuDisplay display) {
        this.playerId = player.getUniqueId();
        this.display = display;
        this.slotMap = new HashMap<>();
        this.layoutSlotMap = new HashMap<>();
    }

    @Override
    public @NotNull Inventory getInventory() {
        int size = Math.min(display.layout().layout().size() * 9, 54);
        Inventory inventory;
        if (inventoryCache == null) {
            inventory = Bukkit.createInventory(this, size, AdventureUtil.toComponent(display.title()));
        } else {
            inventory = inventoryCache;
        }
        return inventory;
    }

    public void openMenuAsync() {
        XRunnable.getScheduler().async(() -> {
            if (inventoryCache == null) {
                this.inventoryCache = getInventory();
            }
            XRunnable.getScheduler().sync(() -> {
                playerOpt().ifPresent(player -> {
                    player.openInventory(inventoryCache);
                });
            });
        });
    }

    public Icon onClick(InventoryClickEvent event) {
        Inventory topInv = event.getView().getTopInventory();
        if (!topInv.equals(event.getClickedInventory())) {
            event.setCancelled(true);
            return null;
        }
        int slot = event.getSlot();
        Icon icon = slotMap.get(slot);
        if (icon == null) {
            event.setCancelled(true);
            return null;
        }
        return icon.onClick(event, slot);
    }

    public void onDrag(InventoryDragEvent event) {
    }

    public void onOpen(InventoryOpenEvent event) {
    }

    public void onClose(InventoryCloseEvent event) {
    }

    public void onQuit(PlayerQuitEvent event) {
    }

    /**
     * 获取打开该页面的玩家,除非玩家离线,否则不会为null
     */
    public Optional<@Nullable Player> playerOpt() {
        return Optional.ofNullable(Bukkit.getPlayer(playerId));
    }
}
