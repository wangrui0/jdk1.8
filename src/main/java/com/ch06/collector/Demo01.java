package com.ch06.collector;

import com.entity.Dish;

import java.util.Arrays;
import java.util.List;

/**
 * Collector接口包含了一系列方法，为实现具体的归约操作（即收集器）提供了范本;
 */
public class Demo01 {
    public static void main(String[] args) {
        List<Dish> menues = Arrays.asList(new Dish("rice", true, 300, Dish.Type.FISH), new Dish("meate", false, 500, Dish.Type.MEAT), new Dish("dog", true, 30000, Dish.Type.FISH));
    }

    public static void test01(List<Dish> menue) {
    }
}
