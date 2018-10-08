package com.ch03;

import com.entity.Apple;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
/**
 *类型推断
 */
public class Demo04 {
    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("green", 2));
        apples.add(new Apple("green", 3));
        apples.add(new Apple("yellow", 4));
        //过滤
        apples.stream().filter((Apple a) -> "green".equals(a.getColor())).collect(Collectors.toList());
        apples.stream().filter(a -> "green".equals(a.getColor())).collect(Collectors.toList());
        //排序
        List<Apple> collect = apples.stream().sorted((Apple o1, Apple o2) -> o1.getWeight().compareTo(o2.getWeight())).collect(Collectors.toList());
        List<Apple> collect2 = apples.stream().sorted((o1, o2) -> o1.getWeight().compareTo(o2.getWeight())).collect(Collectors.toList());
        List<Apple> collect3 = apples.stream().sorted(Comparator.comparing(Apple::getWeight).thenComparing(Apple::getColor)).collect(Collectors.toList());
    }
}
