package cn.etherPals.gui.impl.icon.display;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public record IconParams(
        @NotNull Material material,
        @Nullable String name,
        @Nullable List<String> lore,
        @Nullable NamespacedKey itemModel,
        @Nullable Integer customModelData,
        @Nullable ItemFlag[] itemFlags
) {
    public ItemStack toItemStack() {
        ItemStack item = new ItemStack(material);
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
        if (itemModel != null) {
            meta.setItemModel(itemModel);
        }
        if (customModelData != null) {
            meta.setCustomModelData(customModelData);
        }
        if (itemFlags != null) {
            meta.addItemFlags(itemFlags);
        }
        item.setItemMeta(meta);
        return item;
    }
}
