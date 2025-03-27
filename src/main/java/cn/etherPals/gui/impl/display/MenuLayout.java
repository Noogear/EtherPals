package cn.etherPals.gui.impl.display;

import cn.etherPals.gui.impl.icon.CommonIcon;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class MenuLayout {
    private List<String> layout;
    private Map<Character, Supplier<CommonIcon>> layoutMap;

    public MenuLayout() {
        this(new ArrayList<>(), new HashMap<>());
    }

    public MenuLayout(@NotNull List<String> layout, @NotNull Supplier<Map<Character, Supplier<CommonIcon>>> layoutMapSupplier) {
        this(layout, layoutMapSupplier.get());
    }

    public MenuLayout(@NotNull Supplier<List<String>> layoutSupplier, @NotNull Supplier<Map<Character, Supplier<CommonIcon>>> layoutMapSupplier) {
        this(layoutSupplier.get(), layoutMapSupplier.get());
    }

    public MenuLayout(@NotNull List<String> layout, @NotNull Map<Character, Supplier<CommonIcon>> layoutMap) {
        this.layout = layout;
        this.layoutMap = layoutMap;
    }

    @NotNull
    public List<String> layout() {
        return layout;
    }

    @NotNull
    public MenuLayout setLayout(List<String> layout) {
        this.layout = layout;
        return this;
    }

    public Map<Character, Supplier<CommonIcon>> layoutMap() {
        return layoutMap;
    }

    public MenuLayout setLayoutMap(@NotNull Map<Character, Supplier<CommonIcon>> layoutMap) {
        this.layoutMap = layoutMap;
        return this;
    }
}
