package com.predicate.impl;

import com.entity.Apple;
import com.predicate.IApplePredicate;

/**
 * 以用ApplePredicate的多个实现代表不同的选择标准,检验某个平时是否是青苹果
 */
public class AppleGreenColorPredicate implements IApplePredicate {
    @Override
    public boolean appleCheck(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
