package io.github.lsf.gateway.outbound.httpclient4;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadNameFactory implements ThreadFactory {
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    private final String namePrefix;
    private final boolean daemon;

    public ThreadNameFactory(String namePrefix) {
        this(namePrefix, false);
    }

    public ThreadNameFactory(String namePrefix, boolean daemon) {
        this.namePrefix = namePrefix;
        this.daemon = daemon;
        SecurityManager securityManager = System.getSecurityManager();
        group = (securityManager != null) ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
    }


    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(group, runnable,
                namePrefix + "-thread-" + threadNumber.incrementAndGet(),
                0);
        thread.setDaemon(this.daemon);
        return thread;
    }
}
