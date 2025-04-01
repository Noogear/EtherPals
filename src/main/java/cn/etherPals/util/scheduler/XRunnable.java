package cn.etherPals.util.scheduler;


import cn.etherPals.Main;
import cn.etherPals.util.scheduler.task.ITaskWrapper;

public abstract class XRunnable implements Runnable {

    private static IScheduler scheduler;
    protected ITaskWrapper taskWrapper;

    public static void init(Main plugin, boolean isFolia) {
        scheduler = isFolia ? new FoliaScheduler(plugin) : new BukkitScheduler(plugin);
    }

    public static IScheduler getScheduler() {
        return scheduler;
    }

    @Override
    public abstract void run();

    public ITaskWrapper async() {
        checkTaskNotNull();
        return setTaskWrapper(scheduler.async(this));
    }

    public ITaskWrapper async(long delayTicks) {
        checkTaskNotNull();
        return setTaskWrapper(scheduler.asyncLater(this, delayTicks));
    }

    public ITaskWrapper async(long delayTicks, long periodTicks) {
        checkTaskNotNull();
        return setTaskWrapper(scheduler.asyncTimer(this, delayTicks, periodTicks));
    }


    public ITaskWrapper sync() {
        checkTaskNotNull();
        return setTaskWrapper(scheduler.sync(this));
    }

    public ITaskWrapper sync(long delayTicks) {
        checkTaskNotNull();
        return setTaskWrapper(scheduler.syncLater(this, delayTicks));
    }

    public ITaskWrapper sync(long delayTicks, long periodTicks) {
        checkTaskNotNull();
        return setTaskWrapper(scheduler.syncTimer(this, delayTicks, periodTicks));
    }

    public void cancel() {
        if (this.taskWrapper == null) return;
        this.taskWrapper.cancel();
    }

    public boolean isCancelled() {
        if (this.taskWrapper == null) {
            return true;
        }
        return this.taskWrapper.isCancelled();
    }

    protected ITaskWrapper setTaskWrapper(ITaskWrapper taskWrapper) {
        this.taskWrapper = taskWrapper;
        return this.taskWrapper;
    }

    protected void checkTaskNotNull() {
        if (this.taskWrapper != null) {
            throw new IllegalArgumentException("Runnable is null");
        }
    }

    protected void checkTaskNull() {
        if (this.taskWrapper == null) {
            throw new IllegalArgumentException("Task is null");
        }
    }

}
