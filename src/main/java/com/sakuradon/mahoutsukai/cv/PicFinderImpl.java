package com.sakuradon.mahoutsukai.cv;

import com.google.inject.Inject;
import com.sakuradon.mahoutsukai.entity.EntityFactory;
import com.sakuradon.mahoutsukai.entity.Point;

import java.awt.image.BufferedImage;

/**
 * @author SakuraDon
 */
public class PicFinderImpl implements PicFinder {

    @Inject
    private EntityFactory entityFactory;

    @Override
    public Point findPic(BufferedImage source, BufferedImage target) {
        Point point;
        for (int left = 0; left <= source.getWidth() - target.getWidth(); left++) {
            for (int top = 0; top <= source.getHeight() - target.getHeight(); top++) {
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
            if (!compareRgb(source.getRGB(x + left, y + top), target.getRGB(x, y))) {
                return null;
            }
        }

        for (int x = 0; x < target.getWidth(); x++) {
            for (int y = 0; y < target.getHeight(); y++) {
                if (!compareRgb(source.getRGB(x + left, y + top), target.getRGB(x, y))) {
                    return null;
                }
            }
        }
        return entityFactory.createPoint(left, top);
    }

    private boolean compareRgb(int rgb1, int rgb2) {
        return rgb1 == rgb2;
    }

}
