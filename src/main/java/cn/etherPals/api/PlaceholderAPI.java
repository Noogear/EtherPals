package cn.etherPals.api;

import cn.etherPals.Main;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlaceholderAPI extends PlaceholderExpansion {
    private final Main plugin;

    public PlaceholderAPI(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "etherpals";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Noggear";
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true; //
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        switch (params.toLowerCase()) {
            case "name":
                return null;
            case "id":
                return null;
            case "level":
                return null;
            case "exp":
                return null;
            case "type":
                return null;
        }
        return null;
    }
}
