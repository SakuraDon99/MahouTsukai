package com.sakuradon.mahoutsukai.entity;

import com.sakuradon.mahoutsukai.exception.Exceptions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author SakuraDon
 */
public class EntityFactoryImpl implements EntityFactory {

    @Override
    public Point createPoint(int x, int y) {
        return new Point(x, y);
    }

    @Override
    public Area createArea(int left, int top, int width, int height) {
        return new Area(left, top, width, height);
    }

    @Override
    public Element createElement(String name, String path) {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            return new Element(name, image);
        } catch (Exception e) {
            throw Exceptions.ELEMENT_CREATE_FAILED;
        }
    }

}
