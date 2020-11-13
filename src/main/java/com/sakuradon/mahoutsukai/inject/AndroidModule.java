package com.sakuradon.mahoutsukai.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.sakuradon.mahoutsukai.android.Adb;
import com.sakuradon.mahoutsukai.android.AndroidSession;
import com.sakuradon.mahoutsukai.core.Session;

/**
 * @author SakuraDon
 */
public class AndroidModule extends AbstractModule {

    private Adb adb;

    public void setAdb(Adb adb) {
        this.adb = adb;
    }

    @Override
    protected void configure() {
        if (adb != null) {
            bind(Adb.class).toInstance(adb);
        }
        bind(Session.class).to(AndroidSession.class).in(Scopes.SINGLETON);
    }

}
