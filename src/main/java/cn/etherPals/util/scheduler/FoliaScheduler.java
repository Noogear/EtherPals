package cn.etherPals.util.scheduler;

import cn.etherPals.Main;
import cn.etherPals.util.scheduler.task.FoliaTaskWrapper;
import cn.etherPals.util.scheduler.task.ITaskWrapper;
import io.papermc.paper.threadedregions.scheduler.AsyncScheduler;
import io.papermc.paper.threadedregions.scheduler.GlobalRegionScheduler;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class FoliaScheduler implements IScheduler {

    private final Main plugin;
    private final AsyncScheduler asyncScheduler;
    private final GlobalRegionScheduler syncScheduler;

    public FoliaScheduler(Main main) {
        this.plugin = main;
        this.asyncScheduler = Bukkit.getAsyncScheduler();
        this.syncScheduler = Bukkit.getGlobalRegionScheduler();
    }

    @Override
    public ITaskWrapper sync(@NotNull Runnable task) {
        return new FoliaTaskWrapper(syncScheduler.run(plugin, runnableToConsumer(task)));
    }

    @Override
    public ITaskWrapper async(@NotNull Runnable task) {
        return new FoliaTaskWrapper(asyncScheduler.runNow(plugin, runnableToConsumer(task)));
    }

    @Override
    public ITaskWrapper syncLater(@NotNull Runnable task, long delayTicks) {
        return new FoliaTaskWrapper(syncScheduler.runDelayed(plugin, runnableToConsumer(task), toSafeTick(delayTicks)));
    }

    @Override
    public ITaskWrapper asyncLater(@NotNull Runnable task, long delayTicks) {
        return new FoliaTaskWrapper(asyncScheduler.runDelayed(plugin, runnableToConsumer(task), toSafeTick(delayTicks) * 50, TimeUnit.MILLISECONDS));
    }

    @Override
    public ITaskWrapper syncTimer(@NotNull Runnable task, long delayTicks, long periodTicks) {
        return new FoliaTaskWrapper(syncScheduler.runAtFixedRate(plugin, runnableToConsumer(task), toSafeTick(delayTicks), toSafeTick(periodTicks)));
    }

    @Override
    public ITaskWrapper asyncTimer(@NotNull Runnable task, long delayTicks, long periodTicks) {
        return new FoliaTaskWrapper(asyncScheduler.runAtFixedRate(plugin, runnableToConsumer(task), toSafeTick(delayTicks) * 50, toSafeTick(periodTicks) * 50, TimeUnit.MILLISECONDS));
    }

    private long toSafeTick(long originTick) {
        return originTick > 0 ? originTick : 1;
    }

    @Override
    public void cancelTasks() {
        asyncScheduler.cancelTasks(plugin);
    }

    private Consumer<ScheduledTask> runnableToConsumer(Runnable runnable) {
        return (final ScheduledTask task) -> runnable.run();
    }

}
