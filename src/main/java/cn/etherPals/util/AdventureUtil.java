package cn.etherPals.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdventureUtil {

    public static Component toComponent(String text) {
        return MiniMessage.miniMessage().deserialize(text);
    }

    public static @NotNull List<Component> toComponent(@NotNull List<String> textList) {
        return textList.stream().map(AdventureUtil::toComponent).toList();
    }

}
