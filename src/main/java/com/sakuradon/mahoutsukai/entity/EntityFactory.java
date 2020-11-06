package com.sakuradon.mahoutsukai.entity;

/**
 * @author SakuraDon
 */
public interface EntityFactory {

    /**
     * create point
     *
     * @param x x
     * @param y y
     * @return point
     */
    Point createPoint(int x, int y);

    /**
     * create area
     *
     * @param left   left
     * @param top    top
     * @param width  width
     * @param height height
     * @return area
     */
    Area createArea(int left, int top, int width, int height);

    /**
     * create element
     *
     * @param name name
     * @param path path
     * @return element
     */
    Element createElement(String name, String path);

}
