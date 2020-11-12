package com.sakuradon.mahoutsukai.android;

import com.google.inject.Inject;
import com.sakuradon.mahoutsukai.config.Config;
import com.sakuradon.mahoutsukai.entity.Area;
import com.sakuradon.mahoutsukai.entity.Point;
import com.sakuradon.mahoutsukai.exception.Exceptions;
import com.sakuradon.mahoutsukai.core.Session;
import com.sakuradon.mahoutsukai.cv.PicFinder;
import com.sakuradon.mahoutsukai.entity.Element;
import com.sakuradon.mahoutsukai.entity.EntityFactory;
import com.sakuradon.mahoutsukai.log.LoggerFactory;
import jdk.internal.instrumentation.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author SakuraDon
 */
public class AndroidSession implements Session {

    private static final Logger LOGGER = LoggerFactory.createLogger(AndroidSession.class);

    @Inject
    private Adb adb;

    @Inject
    private Config config;

    @Inject
    private PicFinder picFinder;

    @Inject
    private EntityFactory entityFactory;

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
    public Area findElement(Element element, Area area) {
        LOGGER.debug("find element " + element.getName());
        String androidScreen = "/dev/screencap.png";
        String localScreen = config.getScreenPath() + File.separator + "screencap.png";
        adb.screenCap(androidScreen);
        adb.pull(androidScreen, localScreen);
        int x1 = 0;
        int y1 = 0;
        int x2 = x1 + element.getWidth() - 1;
        int y2 = y1 + element.getHeight() - 1;
        if (area != null) {
            x1 = area.getX1();
            y1 = area.getY1();
            x2 = area.getX2();
            y2 = area.getY2();
        }
        try {
            BufferedImage screen = ImageIO.read(new File(localScreen));
            Point point = picFinder.findPic(screen, x1, y1, x2, y2, element.getImage());
            if (point == null) {
                return null;
            }
            return entityFactory.createArea(point.getX(), point.getY(), element.getWidth(), element.getHeight());
        } catch (IOException e) {
            throw Exceptions.LOCAL_SCREEN_READ_FAILED;
        }
    }

}
