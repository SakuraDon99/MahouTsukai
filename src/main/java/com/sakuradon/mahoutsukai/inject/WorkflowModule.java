package com.sakuradon.mahoutsukai.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.sakuradon.mahoutsukai.core.Workflow;
import com.sakuradon.mahoutsukai.entity.GlobalEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SakuraDon
 */
public class WorkflowModule extends AbstractModule {

    private final List<Class<? extends Workflow>> classList = new ArrayList<>();

    public void addClass(Class<? extends Workflow> clz) {
        classList.add(clz);
    }

    @Override
    protected void configure() {
        for (Class<? extends Workflow> clz : classList) {
            bind(clz);
        }
    }

}
