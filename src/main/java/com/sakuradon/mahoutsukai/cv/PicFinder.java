package com.sakuradon.mahoutsukai.cv;

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
     * @param target 目标图片
     * @return 位置
     */
    Point findPic(BufferedImage source, BufferedImage target);

}
