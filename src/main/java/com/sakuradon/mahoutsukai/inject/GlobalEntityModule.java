package com.sakuradon.mahoutsukai.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.sakuradon.mahoutsukai.entity.GlobalEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SakuraDon
 */
public class GlobalEntityModule extends AbstractModule {

    private final List<Class<? extends GlobalEntity>> classList = new ArrayList<>();

    public void addClass(Class<? extends GlobalEntity> clz) {
        classList.add(clz);
    }

    @Override
    protected void configure() {
        for (Class<? extends GlobalEntity> clz : classList) {
            bind(clz).in(Scopes.SINGLETON);
        }
    }

}