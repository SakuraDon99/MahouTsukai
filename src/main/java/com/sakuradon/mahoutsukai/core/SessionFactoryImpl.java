package com.sakuradon.mahoutsukai.core;

import com.sakuradon.mahoutsukai.android.Adb;
import com.sakuradon.mahoutsukai.android.AndroidSession;

/**
 * @author SakuraDon
 */
public class SessionFactoryImpl implements SessionFactory {

    @Override
    public Session createAndroidSession(String name, Adb adb) {
        return new AndroidSession(name, adb);
    }

}
