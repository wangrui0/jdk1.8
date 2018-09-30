package com.ch02;

import com.entity.Apple;
import com.predicate.IAppleFormatter;

import java.util.Arrays;
import java.util.List;

/**
 * 你已经看到，可以把行为抽象出来，让你的代码适应需求的变化，但这个过程很啰嗦，因为
 * 你需要声明很多只要实例化一次的类。让我们来看看可以怎样改进。
 * 匿名类和你熟悉的Java局部类（块中定义的类）差不多，但匿名类没有名字。它允许你同时
 * 声明并实例化一个类。换句话说，它允许你随用随建。
 */
public class Demo04 {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple("green", 18), new Apple("green", 13), new Apple("yellow", 12));
        prettyPrintApple(inventory, new IAppleFormatter() {
            @Override
            public String accept(Apple apple) {
                String characteristic = apple.getWeight() > 150 ? "heavy" :
                        "light";
                return "A " + characteristic +
                        " " + apple.getColor() + " apple";
            }
        });
        prettyPrintApple(inventory, new IAppleFormatter() {
            @Override
            public String accept(Apple apple) {
                return "An apple of " + apple.getWeight() + "g";
            }
        });
    }

    public static void prettyPrintApple(List<Apple> inventory,
                                        IAppleFormatter formatter) {
        for (Apple apple : inventory) {
            String output = formatter.accept(apple);
            System.out.println(output);
        }
    }
}
