package com.ch04;

import com.entity.Dish;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * 举例：
 * 返回低热量的菜肴名称的，
 * 并按照卡路里排序
 */
public class Demo1 {
    public static void main(String[] args) {
        List<Dish> lowCaloricDishes = new ArrayList<>();
        List<String> dishName = lowCaloricDishes.stream().filter(dish -> dish.getCalories() < 140).sorted(comparing(Dish::getCalories)).map(Dish::getName).collect(toList());
        System.out.println(dishName.toString());
    }
}
