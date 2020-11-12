package com.sakuradon.mahoutsukai.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.sakuradon.mahoutsukai.config.Config;
import com.sakuradon.mahoutsukai.core.Executor;
import com.sakuradon.mahoutsukai.core.ExecutorImpl;
import com.sakuradon.mahoutsukai.cv.PicFinder;
import com.sakuradon.mahoutsukai.cv.PicFinderImpl;
import com.sakuradon.mahoutsukai.entity.EntityFactory;
import com.sakuradon.mahoutsukai.entity.EntityFactoryImpl;

/**
 * @author SakurDon
 */
public class BasicModule extends AbstractModule {

    private Config config;

    public void setConfig(Config config) {
        this.config = config;
    }

    @Override
    protected void configure() {
        bind(PicFinder.class).to(PicFinderImpl.class).in(Scopes.SINGLETON);
        bind(EntityFactory.class).to(EntityFactoryImpl.class).in(Scopes.SINGLETON);
        bind(Executor.class).to(ExecutorImpl.class).in(Scopes.SINGLETON);
        if (config != null) {
            bind(Config.class).toInstance(config);
        }
    }

}
