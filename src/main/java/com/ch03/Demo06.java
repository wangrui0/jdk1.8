package com.ch03;

import com.entity.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

/**
 * Lambda 和方法引用实战:
 * 用不同的排序策略给一个Apple列表排序
 */
public class Demo06 {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple("green", 18), new Apple("green", 13), new Apple("yellow", 12));
        //法一：
        inventory.sort(new AppleComparator());
        //法二：匿名内部类
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
        //法三：Lambda
        inventory.sort((Apple o1,Apple o2)->o1.getWeight().compareTo(o2.getWeight()));
        //法四：lambda 但是忽略类型
        inventory.sort((o1,o2)->o1.getWeight().compareTo(o2.getWeight()));
        //法五：方法引用
        inventory.sort(comparing(Apple::getWeight));
        //法六：最终的版本
        inventory.sort(comparing(Apple::getWeight).thenComparing(Apple::getColor));

    }
}
