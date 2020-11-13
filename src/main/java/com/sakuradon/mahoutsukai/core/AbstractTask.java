package com.sakuradon.mahoutsukai.core;

import com.sakuradon.mahoutsukai.annotation.EnableTask;
import com.sakuradon.mahoutsukai.exception.Exceptions;

/**
 * @author SakuraDon
 */
public abstract class AbstractTask implements Task {

    private String name;

    @Override
    public final String getName() {
        if (name != null) {
            return name;
        }
        Class<?> clz = this.getClass();
        EnableTask enableTask = clz.getAnnotation(EnableTask.class);
        if (enableTask == null) {
            throw Exceptions.ILLEGAL_TASK;
        }
        name = "".equals(enableTask.value()) ? clz.getSimpleName() : enableTask.value();
        return name;
    }

}
