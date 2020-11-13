package com.sakuradon.mahoutsukai.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.sakuradon.mahoutsukai.android.Adb;
import com.sakuradon.mahoutsukai.config.Config;
import com.sakuradon.mahoutsukai.core.*;
import com.sakuradon.mahoutsukai.entity.GlobalEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author SakuraDon
 */
public class TaskModule extends AbstractModule {

    private final List<Class<?>> classList = new ArrayList<>();

    public void addClass(Class<?> clz) {
        classList.add(clz);
    }

    @Override
    protected void configure() {
        for (Class<?> clz : classList) {
            bind(clz);
        }
    }

}
