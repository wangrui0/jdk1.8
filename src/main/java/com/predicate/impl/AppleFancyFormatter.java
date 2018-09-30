package com.predicate.impl;

import com.entity.Apple;
import com.predicate.IAppleFormatter;

public class AppleFancyFormatter implements IAppleFormatter {
    @Override
    public String accept(Apple apple) {
        String characteristic = apple.getWeight() > 150 ? "heavy" :
                "light";
        return "A " + characteristic +
                " " + apple.getColor() + " apple";
    }
}
