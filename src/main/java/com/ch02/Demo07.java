package com.ch02;

import com.entity.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 用一个Comparator排序，用Runnable执行一个代码块，以及GUI事件处理。
 * 来体会java8
 */
public class Demo07 {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple("green", 12), new Apple("green", 13), new Apple("yellow", 15));
        appleSort(inventory);
    }

    /**
     * 排序
     */
    public static void appleSort(List<Apple> inventory) {
        //1.8前
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
        //1.8以后
        inventory.sort((Apple o1,Apple o2)->o1.getWeight().compareTo(o2.getWeight()));
        //1.8以后，是不是越来越强大啦啊
        inventory.sort(Comparator.comparing(Apple::getWeight).thenComparing(Apple::getColor));
    }
    /**
     * Runnable
     */

}
