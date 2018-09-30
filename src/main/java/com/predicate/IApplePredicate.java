package com.predicate;

import com.entity.Apple;

/**
 * 苹果的断言,和策略模式非常的类似欧
 */
public interface IApplePredicate {
    /**
     * 判断苹果
     * @param apple
     * @return
     */
    boolean appleCheck(Apple apple);
}
