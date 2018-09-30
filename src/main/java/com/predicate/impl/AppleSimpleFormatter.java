package com.predicate.impl;
import com.entity.Apple;
import com.predicate.IAppleFormatter;

public class AppleSimpleFormatter implements IAppleFormatter {
    public String accept(Apple apple) {
        return "An apple of " + apple.getWeight() + "g";
    }
}