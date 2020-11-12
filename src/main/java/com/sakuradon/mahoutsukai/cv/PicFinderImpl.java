package com.sakuradon.mahoutsukai.cv;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sakuradon.mahoutsukai.entity.Point;
import com.sakuradon.mahoutsukai.entity.EntityFactory;
import com.sakuradon.mahoutsukai.log.LoggerFactory;
import jdk.internal.instrumentation.Logger;

import java.awt.image.BufferedImage;

/**
 * @author SakuraDon
 */
public class PicFinderImpl implements PicFinder {

    private static final Logger LOGGER = LoggerFactory.createLogger(PicFinderImpl.class);

    @Inject
    private EntityFactory entityFactory;

    @Override
    public Point findPic(BufferedImage source, int x1, int y1, int x2, int y2, BufferedImage target) {
        Point point;
        for (int left = x1; left <= x2 - target.getWidth() + 1; left++) {
            for (int top = y1; top <= y2 - target.getHeight() + 1; top++) {
                point = findPic(source, target, left, top);
                if (point != null) {
                    return point;
                }
            }
        }
        return null;
    }

    private Point findPic(BufferedImage source, BufferedImage target, int left, int top) {
        int[][] keyPoints = {
                {0, 0},
                {target.getWidth() - 1, 0},
                {0, target.getHeight() - 1},
                {target.getWidth() - 1, 0, target.getHeight() - 1},
                {target.getWidth() / 2, target.getHeight() / 2}
        };

        for (int[] keyPoint : keyPoints) {
            int x = keyPoint[0];
            int y = keyPoint[1];
            if (!compareRgb(source, x + left, y + top, target, x, y)) {
                return null;
            }
        }

        for (int x = 0; x < target.getWidth(); x++) {
            for (int y = 0; y < target.getHeight(); y++) {
                if (!compareRgb(source, x + left, y + top, target, x, y)) {
                    return null;
                }
            }
        }
        return entityFactory.createPoint(left, top);
    }

    private boolean compareRgb(BufferedImage source, int x1, int y1, BufferedImage target, int x2, int y2) {
        int rgb1 = source.getRGB(x1, y1);
        int rgb2 = target.getRGB(x2, y2);
        if (rgb1 != rgb2) {
            LOGGER.trace(String.format("rgb %d (%d, %d) and %d (%d, %d) not same", rgb1, x1, y1, rgb2, x2, y2));
            return false;
        }
        return true;
    }

}
