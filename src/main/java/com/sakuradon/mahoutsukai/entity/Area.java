package com.sakuradon.mahoutsukai.entity;

/**
 * @author SakuraDon
 */
public class Area extends Entity {

    private int left;

    private int top;

    private int width;

    private int height;

    Area(int left, int top, int width, int height) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
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

    public int getX1() {
        return left;
    }

    public int getY1() {
        return top;
    }

    public int getX2() {
        return left + width - 1;
    }

    public int getY2() {
        return top + height - 1;
    }

}
