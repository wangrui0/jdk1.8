package com.ch02;

import com.entity.Apple;
import com.predicate.IAppleFormatter;
import com.predicate.IApplePredicate;
import com.predicate.IPredicate;
import com.predicate.impl.AppleHeavyWeightPredicate2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 接口进一步的抽象
 */
public class Demo05 {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple("green", 18), new Apple("green", 13), new Apple("yellow", 12));
        List<Apple> apples = filterApples(inventory, new AppleHeavyWeightPredicate2());
    }

    public static List<Apple> filterApples(List<Apple> inventory, IPredicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple :
                inventory) {
            if (predicate.check(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
