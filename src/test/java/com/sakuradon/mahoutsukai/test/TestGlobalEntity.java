package com.sakuradon.mahoutsukai.test;

import com.google.inject.Inject;
import com.sakuradon.mahoutsukai.annotation.EntityRepository;
import com.sakuradon.mahoutsukai.entity.EntityFactory;
import com.sakuradon.mahoutsukai.entity.Point;
import com.sakuradon.mahoutsukai.entity.GlobalEntity;

@EntityRepository
public class TestGlobalEntity implements GlobalEntity {

    @Inject
    private EntityFactory entityFactory;

    private Point demoPoint;

    @Override
    public void initialize() {
        demoPoint = entityFactory.createPoint(6, 6);
    }

    public Point getDemoPoint() {
        return demoPoint;
    }

}
