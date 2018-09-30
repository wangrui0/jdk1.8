package com.predicate.impl;

import com.entity.Apple;
import com.predicate.IApplePredicate;

public class AppleRedAndHeavyPredicate implements IApplePredicate {
    public boolean appleCheck(Apple apple) {
        return "red".equals(apple.getColor())
                && apple.getWeight() > 150;
    }
}