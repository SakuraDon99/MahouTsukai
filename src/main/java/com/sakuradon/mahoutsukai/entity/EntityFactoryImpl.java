package com.sakuradon.mahoutsukai.entity;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sakuradon.mahoutsukai.config.Config;
import com.sakuradon.mahoutsukai.exception.Exceptions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author SakuraDon
 */
public class EntityFactoryImpl implements EntityFactory {

    @Inject
    private Config config;

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
        String base = config.getElementPath();
        try {
            BufferedImage image = ImageIO.read(new File(base + File.separator + path));
            return new Element(name, image);
        } catch (FileNotFoundException e) {
            throw Exceptions.ELEMENT_FILE_NOT_FOUND;
        } catch (Exception e) {
            throw Exceptions.ELEMENT_CREATE_FAILED;
        }
    }

}
