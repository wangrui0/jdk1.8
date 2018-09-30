package com.ch02;

import com.entity.Apple;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 使用更简单的方法，lambda
 */
public class Demo06 {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple("green", 18), new Apple("green", 13), new Apple("yellow", 12));
        List<Apple> greenApples = inventory.stream().filter((Apple apple) -> apple.getColor().equals("green")).collect(Collectors.toList());
        System.out.println(greenApples.toString());
        List<Apple> weightApples = inventory.stream().filter((Apple apple) -> apple.getWeight() > 50).collect(Collectors.toList());
        System.out.println(weightApples.toString());
        List<Apple> redWeightApples = inventory.stream().filter((Apple apple) -> apple.getColor().equals("red") && apple.getWeight() > 50).collect(Collectors.toList());
        System.out.println(redWeightApples.toString());
    }
}
