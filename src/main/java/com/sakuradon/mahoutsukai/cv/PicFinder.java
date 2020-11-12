package com.sakuradon.mahoutsukai.cv;

import com.google.inject.ImplementedBy;
import com.sakuradon.mahoutsukai.entity.Point;

import java.awt.image.BufferedImage;

/**
 * @author SakuraDon
 */
public interface PicFinder {

    /**
     * 查找target在source中的位置
     *
     * @param source 源图片
     * @param x1     x1
     * @param y1     y1
     * @param x2     x2
     * @param y2     y2
     * @param target 目标图片
     * @return 位置
     */
    Point findPic(BufferedImage source, int x1, int y1, int x2, int y2, BufferedImage target);

}
