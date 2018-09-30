package com.ch02;

import com.entity.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1：应对不断变化的需求：
 */
public class Demo01 {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple("green", 12), new Apple("green", 13), new Apple("yellow", 15));
//        List<Apple> apples = filterGreenApples(inventory);
        List<Apple> apples = filterApplesByColours(inventory, "green");
    }

    /**
     * 筛选绿色的苹果:解决一：
     */
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple :
                inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 筛选指定颜色的苹果:解决二：
     */
    public static List<Apple> filterApplesByColours(List<Apple> inventory, String color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple :
                inventory) {
            if (apple.getColor().equals(color)) {
                result.add(apple);
            }
        }
        return result;
    }
    /**
     * 第三次尝试：对你能想到的每个属性做筛选;(优点复杂啦)，这俩flag是啥意识呢？如果属性更多呢？该咋办呢？
     */
    public static List<Apple> filterApples(List<Apple> inventory, String color,
                                           int weight, boolean flag) {
        List<Apple> result = new ArrayList<Apple>();
        for (Apple apple: inventory){
            if ( (flag && apple.getColor().equals(color)) ||
                    (!flag && apple.getWeight() > weight) ){
                result.add(apple);
            }
        }
        return result;
    }
}
