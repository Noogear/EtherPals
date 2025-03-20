package cn.etherPals;

import cn.etherPals.util.scheduler.XRunnable;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer");
            XRunnable.init(this, true);
        } catch (ClassNotFoundException e) {
            XRunnable.init(this, false);
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
