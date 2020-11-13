package com.sakuradon.mahoutsukai.core;

import com.sakuradon.mahoutsukai.entity.Area;
import com.sakuradon.mahoutsukai.entity.Point;
import com.sakuradon.mahoutsukai.exception.TimeoutException;
import com.sakuradon.mahoutsukai.entity.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

/**
 * @author SakuraDon
 */
public interface Session {

    /**
     * 点击
     *
     * @param x x
     * @param y y
     */
    void click(int x, int y);

    /**
     * 点击
     *
     * @param point point
     */
    default void click(Point point) {
        click(point.getX(), point.getY());
    }

    /**
     * 点击
     *
     * @param area area
     */
    default void click(Area area) {
        Random random = new Random();
        click(area.getLeft() + random.nextInt(area.getWidth()), area.getTop()
                + random.nextInt(area.getHeight()));
    }

    /**
     * 长按
     *
     * @param x  x
     * @param y  y
     * @param ms ms
     */
    void hold(int x, int y, int ms);

    /**
     * 长按
     *
     * @param point point
     * @param ms    ms
     */
    default void hold(Point point, int ms) {
        hold(point.getX(), point.getY(), ms);
    }

    /**
     * 长按
     *
     * @param area area
     * @param ms   ms
     */
    default void hold(Area area, int ms) {
        Random random = new Random();
        hold(area.getLeft() + random.nextInt(area.getWidth()), area.getTop()
                + random.nextInt(area.getHeight()), ms);
    }

    /**
     * 拖动
     *
     * @param x1 x1
     * @param y1 y1
     * @param x2 x2
     * @param y2 y2
     * @param ms m2
     */
    void move(int x1, int y1, int x2, int y2, int ms);

    /**
     * 拖动
     *
     * @param source 源位置
     * @param target 目标位置
     * @param ms     ms
     */
    default void move(Point source, Point target, int ms) {
        move(source.getX(), source.getY(), target.getX(), target.getY(), ms);
    }

    /**
     * 查找元素
     *
     * @param element 元素
     * @return 元素所占区域 | Null
     */
    default Area findElement(Element element) {
        return findElement(element, null);
    }

    /**
     * 查找元素
     *
     * @param element 元素
     * @param area    限定区域
     * @return 元素所占区域 | Null
     */
    Area findElement(Element element, Area area);

    /**
     * 查找元素组，返回name对应的结果map
     *
     * @param elementList 元素组
     * @return map
     */
    default Map<String, Area> findElement(List<Element> elementList) {
        return findElement(elementList, null);
    }

    /**
     * 查找元素组，返回name对应的结果map
     *
     * @param elementList 元素组
     * @param area        限定区域
     * @return map
     */
    default Map<String, Area> findElement(List<Element> elementList, Area area) {
        Map<String, Area> map = new HashMap<>(elementList.size());
        for (Element element : elementList) {
            if (element == null) {
                continue;
            }
            if (element.getName() == null) {
                continue;
            }
            map.put(element.getName(), findElement(element, area));
        }
        return map;
    }

    /**
     * 点击元素
     *
     * @param element 元素
     * @return 查找元素的结果
     */
    default Area clickElement(Element element) {
        return clickElement(element, null);
    }

    /**
     * 点击元素
     *
     * @param element 元素
     * @param area    限定区域
     * @return 查找元素的结果
     */
    default Area clickElement(Element element, Area area) {
        Area find = findElement(element, area);
        if (find == null) {
            return null;
        }
        click(find);
        return find;
    }

    /**
     * 长按元素
     *
     * @param element 元素
     * @param ms      ms
     * @return 查找元素的结果
     */
    default Area holdElement(Element element, int ms) {
        return holdElement(element, null, ms);
    }

    /**
     * 长按元素
     *
     * @param element 元素
     * @param area    限定区域
     * @param ms      ms
     * @return 查找元素的结果
     */
    default Area holdElement(Element element, Area area, int ms) {
        Area find = findElement(element, area);
        if (find == null) {
            return null;
        }
        hold(find, ms);
        return find;
    }

    /**
     * 延时
     *
     * @param ms ms
     */
    default void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }

    /**
     * time out
     *
     * @param function function
     * @param ms       ms
     * @return T
     */
    default <T> T timeout(Supplier<T> function, int ms) {
        function.get();
        long ts = System.currentTimeMillis();
        while (true) {
            long realTime = System.currentTimeMillis() - ts;
            if (realTime > ms) {
                throw new TimeoutException(ms, realTime);
            }
            T t = function.get();
            if (t != null) {
                return t;
            }
        }
    }

}
