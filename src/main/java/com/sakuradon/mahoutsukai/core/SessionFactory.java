package com.sakuradon.mahoutsukai.core;

import com.sakuradon.mahoutsukai.android.Adb;

/**
 * @author SakuraDon
 */
public interface SessionFactory {

    /**
     * 创建android session
     *
     * @param name name
     * @param adb  adb
     * @return session
     */
    Session createAndroidSession(String name, Adb adb);

}
