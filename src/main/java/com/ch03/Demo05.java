package com.ch03;

import com.entity.Apple;
import com.function.TriFunction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 方法引用
 */
public class Demo05 {
    public static void main(String[] args) {
        //方法引用
        List<String> list = Arrays.asList("a", "b", "A", "B");
        list.sort((o1, o2) -> o1.compareToIgnoreCase(o2));
        System.out.println(list.toString());
        list.sort(String::compareToIgnoreCase);
        System.out.println(list.toString());
        List<Apple> inventory = Arrays.asList(new Apple("green", 18), new Apple("green", 13), new Apple("yellow", 12));
        Set<String> appleWeights = inventory.stream().map(Apple::getColor).collect(Collectors.toSet());
        Set<String> appleWeights2 = inventory.stream().map(apple->apple.getColor()).collect(Collectors.toSet());
        System.out.println(appleWeights.toString());
        //构造函数引用根据重量创建苹果
        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = map(weights, (Integer weight) -> new Apple(weight));
        System.out.println(apples.toString());
        List<Apple> apples2 = map(weights, Apple::new);
        System.out.println(apples2.toString());
        //构造函数根据颜色和重量创建苹果
        List<Apple> apples3 = mapByColorAndWeight(weights, Apple::new);
        List<Apple> apples4 = mapByColorAndWeight(weights, (String color, Integer weight) -> new Apple(color, weight));
        System.out.println(apples3.toString());
        System.out.println(apples4.toString());
        //构造函数引用的加强，自定义构造函数。通过三个参数创建构造函数
        List<Apple> apples5 = mapByColorAndWeightAndCountry(weights, Apple::new);
        List<Apple> apples6 = mapByColorAndWeightAndCountry(weights, (String color,String country, Integer weight) -> new Apple(color,country ,weight));
        System.out.println(apples5.toString());
        System.out.println(apples6.toString());

    }

    public static List<Apple> map(List<Integer> list, Function<Integer, Apple> function) {
        List<Apple> result = new ArrayList<>();
        for (Integer weight :
                list) {
            result.add(function.apply(weight));
        }
        return result;
    }

    /**
     * 创建所有的绿苹果
     *
     * @param list
     * @param function
     * @return
     */
    public static List<Apple> mapByColorAndWeight(List<Integer> list, BiFunction<String, Integer, Apple> function) {
        List<Apple> result = new ArrayList<>();
        for (Integer weight :
                list) {
            result.add(function.apply("green", weight));
        }
        return result;
    }

    /**
     * 创建所有的绿苹果
     *
     * @param list
     * @param function
     * @return
     */
    public static List<Apple> mapByColorAndWeightAndCountry(List<Integer> list, TriFunction<String, String, Integer, Apple> function) {
        List<Apple> result = new ArrayList<>();
        for (Integer weight :
                list) {
            result.add(function.apply("green", "china", weight));
        }
        return result;
    }

}
