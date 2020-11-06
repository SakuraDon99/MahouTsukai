package com.sakuradon.mahoutsukai.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.sakuradon.mahoutsukai.android.Adb;
import com.sakuradon.mahoutsukai.config.Config;
import com.sakuradon.mahoutsukai.core.Executor;
import com.sakuradon.mahoutsukai.core.ExecutorImpl;
import com.sakuradon.mahoutsukai.core.Session;
import com.sakuradon.mahoutsukai.core.Task;
import com.sakuradon.mahoutsukai.cv.PicFinder;
import com.sakuradon.mahoutsukai.cv.PicFinderImpl;
import com.sakuradon.mahoutsukai.entity.EntityFactory;
import com.sakuradon.mahoutsukai.entity.EntityFactoryImpl;
import com.sakuradon.mahoutsukai.entity.GlobalEntity;

import java.util.List;
import java.util.Queue;

/**
 * @author SakuraDon
 */
public class BasicModule extends AbstractModule {

    private Config config;

    private Queue<Class<? extends Task>> taskQueue;

    private List<Class<? extends GlobalEntity>> globalEntityList;

    private Adb adb;

    private Session session;

    public void setConfig(Config config) {
        this.config = config;
    }

    public void setTaskQueue(Queue<Class<? extends Task>> taskQueue) {
        this.taskQueue = taskQueue;
    }

    public void setGlobalEntityList(List<Class<? extends GlobalEntity>> globalEntityList) {
        this.globalEntityList = globalEntityList;
    }

    public void setAdb(Adb adb) {
        this.adb = adb;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    protected void configure() {
        bind(EntityFactory.class).to(EntityFactoryImpl.class);
        bind(PicFinder.class).to(PicFinderImpl.class);
        bind(Executor.class).to(ExecutorImpl.class);
        for (Class<? extends Task> clz : taskQueue) {
            bind(clz).in(Scopes.SINGLETON);
        }
        for (Class<? extends GlobalEntity> clz : globalEntityList) {
            bind(clz).in(Scopes.SINGLETON);
        }
        if (config != null) {
            bind(Config.class).toInstance(config);
        }
        if (adb != null) {
            bind(Adb.class).toInstance(adb);
        }
        if (session != null) {
            bind(Session.class).toInstance(session);
        }
    }

}
