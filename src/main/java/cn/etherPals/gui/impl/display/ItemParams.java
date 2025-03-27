package cn.etherPals.gui.impl.display;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public record ItemParams(
        @Nullable String name,
        @Nullable List<String> lore
) {

    public ItemStack toItemStack(Material material) {
        ItemStack newItem = new ItemStack(material);
        return update(newItem);
    }

    public ItemStack update(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (name != null) {
            meta.displayName(MiniMessage.miniMessage().deserialize(name));
        }
        if (lore != null) {
            List<Component> loreComponents = new ArrayList<>();
            for (String l : lore) {
                loreComponents.add(MiniMessage.miniMessage().deserialize(l));
            }
            meta.lore(loreComponents);
        }
        item.setItemMeta(meta);
        return item;
    }
}
