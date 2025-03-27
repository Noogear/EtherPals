package cn.etherPals.gui.impl.icon;

import cn.etherPals.gui.impl.display.ItemParams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public class CommonIcon implements Icon {
    protected ItemParams params;
    protected ItemStack display;
    protected Consumer<InventoryClickEvent> clickAction;

    private @Nullable UUID parsePlayerId;

    @Override
    public Icon onClick(InventoryClickEvent event) {
        if (clickAction == null) {
            event.setCancelled(true);
            return this;
        }
        clickAction.accept(event);
        return this;
    }

    public void update() {
        params.update(display);
    }

    public CommonIcon setDisplay(@NotNull ItemStack display) {
        this.display = display;
        return this;
    }

    public ItemStack display() {
        return display;
    }

    public CommonIcon setParams(@NotNull ItemParams params) {
        this.params = params;
        return this;
    }

    public ItemParams params() {
        return params;
    }

    public CommonIcon setClickAction(@Nullable Consumer<InventoryClickEvent> clickAction) {
        this.clickAction = clickAction;
        return this;
    }

    @Nullable
    public Consumer<InventoryClickEvent> clickAction() {
        return clickAction;
    }

    @Override
    public @Nullable UUID parsePlayerId() {
        return parsePlayerId;
    }

    public CommonIcon setParsePlayerId(@Nullable UUID parsePlayerId) {
        this.parsePlayerId = parsePlayerId;
        return this;
    }

    public Optional<Player> parsePlayerOpt() {
        if (parsePlayerId == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(Bukkit.getPlayer(parsePlayerId));
    }

    @Deprecated
    public @Nullable Player parsePlayer() {
        return parsePlayerOpt().orElse(null);
    }
}
