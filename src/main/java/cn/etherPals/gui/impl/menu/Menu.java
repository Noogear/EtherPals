package cn.etherPals.gui.impl.menu;

import cn.etherPals.gui.impl.display.MenuDisplay;
import cn.etherPals.gui.impl.icon.CommonIcon;
import cn.etherPals.gui.impl.icon.Icon;
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
    protected final Map<Integer, CommonIcon> slotMap;
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
        return null;
    }

    public Menu openMenu() {
        if (inventoryCache == null)
            this.inventoryCache = getInventory();
        playerOpt().ifPresent(player -> {
            player.openInventory(inventoryCache);
        });
        return this;
    }

    public Icon onClick(InventoryClickEvent event) {
        Inventory topInv = event.getView().getTopInventory();
        if (!topInv.equals(event.getClickedInventory())) {
            event.setCancelled(true);
            return null;
        }
        Icon icon = slotMap.get(event.getSlot());
        if (icon == null) {
            event.setCancelled(true);
            return null;
        }
        return icon.onClick(event);
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
