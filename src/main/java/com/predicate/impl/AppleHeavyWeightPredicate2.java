package com.predicate.impl;

import com.entity.Apple;
import com.predicate.IPredicate;

/**
 * 以用ApplePredicate的多个实现代表不同的选择标准,检验某个平时是否是重的苹果
 */
public class AppleHeavyWeightPredicate2 implements IPredicate<Apple> {
    @Override
    public boolean check(Apple apple) {
        return apple.getWeight() > 150;
    }
}
