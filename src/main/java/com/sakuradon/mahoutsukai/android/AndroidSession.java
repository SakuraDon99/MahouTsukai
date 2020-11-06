package com.sakuradon.mahoutsukai.android;

import com.sakuradon.mahoutsukai.core.Session;
import com.sakuradon.mahoutsukai.entity.Area;
import com.sakuradon.mahoutsukai.entity.Element;
import com.sakuradon.mahoutsukai.log.LoggerFactory;
import com.sakuradon.mahoutsukai.log.LoggerRole;
import jdk.internal.instrumentation.Logger;

/**
 * @author SakuraDon
 */
public class AndroidSession implements Session {

    private static final Logger LOGGER = LoggerFactory.createLogger(LoggerRole.SYSTEM);

    private final String name;

    private final Adb adb;

    public AndroidSession(String name, Adb adb) {
        this.name = name;
        this.adb = adb;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void click(int x, int y) {
        LOGGER.debug("click " + x + " " + y);
        adb.tap(x, y);
    }

    @Override
    public void hold(int x, int y, int ms) {
        LOGGER.debug("hold " + x + " " + y + " " + ms + "ms");
        adb.swipe(x, y, x, y, ms);
    }

    @Override
    public void move(int x1, int y1, int x2, int y2, int ms) {
        LOGGER.debug("move " + x1 + " " + y1 + " " + x2 + " " + y2 + " " + ms + "ms");
        adb.swipe(x1, y1, x2, y2, ms);
    }

    @Override
    public Area findElement(Element element) {
        LOGGER.debug("find element " + element.getName());
        adb.screenCap("/dev/test.png");
        adb.pull("/dev/test.png", "./a.txt");
        return null;
    }

}
