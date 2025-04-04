package cn.etherPals.util.scheduler;

import cn.etherPals.Main;
import cn.etherPals.util.scheduler.task.BukkitTaskWrapper;
import cn.etherPals.util.scheduler.task.ITaskWrapper;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class BukkitScheduler implements IScheduler {
    private final Main plugin;
    private final org.bukkit.scheduler.BukkitScheduler scheduler;

    public BukkitScheduler(Main main) {
        this.plugin = main;
        this.scheduler = Bukkit.getScheduler();
    }

    @Override
    public ITaskWrapper sync(@NotNull Runnable task) {
        return new BukkitTaskWrapper(scheduler.runTask(plugin, task));
    }

    @Override
    public ITaskWrapper async(@NotNull Runnable task) {
        return new BukkitTaskWrapper(scheduler.runTaskAsynchronously(plugin, task));
    }

    @Override
    public ITaskWrapper syncLater(@NotNull Runnable task, long delayTicks) {
        return new BukkitTaskWrapper(scheduler.runTaskLater(plugin, task, delayTicks));
    }

    @Override
    public ITaskWrapper asyncLater(@NotNull Runnable task, long delayTicks) {
        return new BukkitTaskWrapper(scheduler.runTaskLaterAsynchronously(plugin, task, delayTicks));
    }

    @Override
    public ITaskWrapper syncTimer(@NotNull Runnable task, long delayTicks, long periodTicks) {
        return new BukkitTaskWrapper(scheduler.runTaskTimer(plugin, task, delayTicks, periodTicks));
    }

    @Override
    public ITaskWrapper asyncTimer(@NotNull Runnable task, long delayTicks, long periodTicks) {
        return new BukkitTaskWrapper(scheduler.runTaskTimerAsynchronously(plugin, task, delayTicks, periodTicks));
    }

    @Override
    public void cancelTasks() {
        scheduler.cancelTasks(plugin);
    }

}
