package com.ch02;

import com.entity.Apple;
import com.predicate.IApplePredicate;
import com.predicate.impl.AppleGreenColorPredicate;
import com.predicate.impl.AppleHeavyWeightPredicate;
import com.predicate.impl.AppleRedAndHeavyPredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 你需要filterApples方法接受
 * ApplePredicate对象，对Apple做条件测试。这就是行为参数化：让方法接受多种行为（或战
 * 略）作为参数，并在内部使用，来完成不同的行为;
 * 你需要一种比添加很多参数更好的方法来应对变化的需求。让
 * 我们后退一步来看看更高层次的抽象。一种可能的解决方案是对你的选择标准建模：你考虑的
 * 是苹果，需要根据Apple的某些属性（比如它是绿色的吗？重量超过150克吗？）来返回一个
 * boolean值。我们把它称为谓词（即一个返回boolean值的函数）。让我们定义一个接口来对选
 * 择标准建模：
 */
public class Demo02 {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple("green", 18), new Apple("green", 13), new Apple("yellow", 12));
        //筛选出所有的绿苹果
        IApplePredicate appleGreenColorPredicate=new AppleGreenColorPredicate();
        List<Apple> greenAppleList = filterApples(inventory, appleGreenColorPredicate);
        //筛选出所有的重的苹果
        IApplePredicate appleHeavyWeightPredicate=new AppleHeavyWeightPredicate();
        List<Apple> heavyWeightAppleList = filterApples(inventory, appleHeavyWeightPredicate);
        //筛选出红色的重苹果
        List<Apple> redAndHeavyApples =
                filterApples(inventory, new AppleRedAndHeavyPredicate());
    }

    public static List<Apple> filterApples(List<Apple> inventory, IApplePredicate predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple :
                inventory) {
            if (predicate.appleCheck(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
