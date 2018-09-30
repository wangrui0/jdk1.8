package com.ch02;

import com.entity.Apple;
import com.predicate.IAppleFormatter;
import com.predicate.impl.AppleFancyFormatter;
import com.predicate.impl.AppleSimpleFormatter;

import java.util.Arrays;
import java.util.List;

/**
 * 编写一个prettyPrintApple方法，它接受一个Apple的List，并可以对它参数化，以
 * 多种方式根据苹果生成一个String输出（有点儿像多个可定制的toString方法）
 */
public class Demo03 {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple("green", 18), new Apple("green", 13), new Apple("yellow", 12));
        prettyPrintApple(inventory, new AppleFancyFormatter());
        prettyPrintApple(inventory, new AppleSimpleFormatter());
    }

    public static void prettyPrintApple(List<Apple> inventory,
                                        IAppleFormatter formatter) {
        for (Apple apple : inventory) {
            String output = formatter.accept(apple);
            System.out.println(output);
        }
    }
}
