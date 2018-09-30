package com.predicate.impl;

import com.entity.Apple;
import com.predicate.IPredicate;

public class AppleRedAndHeavyPredicate2 implements IPredicate<Apple> {
    @Override
    public boolean check(Apple apple) {
        return "red".equals(apple.getColor())
                && apple.getWeight() > 150;
    }
}
