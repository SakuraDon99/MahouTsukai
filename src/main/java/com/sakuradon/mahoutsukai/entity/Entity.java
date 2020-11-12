package com.sakuradon.mahoutsukai.entity;

import com.google.gson.Gson;

/**
 * @author SakuraDon
 */
public class Entity {

    private static final Gson GSON = new Gson();

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + toJsonString();
    }

    public String toJsonString() {
        return GSON.toJson(this);
    }

}
