package com.sakuradon.mahoutsukai.entity;

import java.awt.image.BufferedImage;

/**
 * @author SakuraDon
 */
public class Element extends Entity {

    private String name;

    private BufferedImage image;

    private int width;

    private int height;

    public Element(String name, BufferedImage image) {
        this.name = name;
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
