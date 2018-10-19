package com.ch05.filter;

import com.entity.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 筛选和切片
 */
public class Demo01Filter {
    public static void main(String[] args) {
        List<Dish> menues = Arrays.asList(new Dish("rice", true, 12, Dish.Type.FISH), new Dish("meat", false, 12, Dish.Type.FISH), new Dish("dog", true, 12, Dish.Type.FISH));
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 1, 2, 4);
        List<Dish> dishes = filter01(menues);
        List<Integer> eventNumbers = filter02(numbers);
        List<Dish> dishes2 = filter03(menues);
        List<Dish> dishes3 = filter04(menues);
        List<Dish> meatDish = filterMeat(menues);

    }

    /**
     * 用谓词筛选:
     * 筛选出所有素菜
     */
    public static List<Dish> filter01(List<Dish> menues) {
        return menues.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
    }

    /**
     * 筛选各异的元素:
     * 以下代码会筛选出列表中所有的偶数，并确保没有
     * 重复
     */
    public static List<Integer> filter02(List<Integer> numbers) {
//        Set<Integer> collect = numbers.stream().filter(number -> number % 2 == 0).collect(Collectors.toSet());
        return numbers.stream().filter(number -> number % 2 == 0).distinct().collect(Collectors.toList());
    }

    /**
     * 截短流:
     * 流支持limit(n)方法，该方法会返回一个不超过给定长度的流。所需的长度作为参数传递
     * 给limit。如果流是有序的，则最多会返回前n个元素。比如，你可以建立一个List，选出热量
     * 超过300卡路里的头三道菜：
     */
    public static List<Dish> filter03(List<Dish> numbers) {
        return numbers.stream().filter(dish -> dish.getCalories() > 300).limit(3).collect(Collectors.toList());
    }

    /**
     * 跳过元素:
     * 流还支持skip(n)方法，返回一个扔掉了前n个元素的流。如果流中元素不足n个，则返回一
     * 个空流。请注意，limit(n)和skip(n)是互补的！例如，下面的代码将跳过超过300卡路里的头
     * 两道菜，并返回剩下的
     */
    public static List<Dish> filter04(List<Dish> numbers) {
        return numbers.stream().filter(dish -> dish.getCalories() > 300).skip(2).collect(Collectors.toList());
    }

    /**
     * 筛选前两个荤菜
     */
    public static List<Dish> filterMeat(List<Dish> numbers) {
        return numbers.stream().filter(dish -> Dish.Type.MEAT.equals(dish.getType())).limit(2).collect(Collectors.toList());
    }
}
