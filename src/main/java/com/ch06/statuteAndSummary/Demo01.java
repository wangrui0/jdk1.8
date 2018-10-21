package com.ch06.statuteAndSummary;

import com.entity.Dish;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * 归约和汇总:
 * 为了说明从Collectors工厂类中能创建出多少种收集器实例，我们重用一下前面的例
 * 子：包含一张佳肴列表的菜单！
 * 就像你刚刚看到的，在需要将流项目重组成集合时，一般会使用收集器（Stream方法collect
 * 的参数）。再宽泛一点来说，但凡要把流中所有的项目合并成一个结果时就可以用。这个结果可以
 * 是任何类型，可以复杂如代表一棵树的多级映射，或是简单如一个整数——也许代表了菜单的热
 * 量总和。这两种结果类型我们都会讨论
 */
public class Demo01 {
    public static void main(String[] args) {
        List<Dish> menues = Arrays.asList(new Dish("rice", true, 10000, Dish.Type.FISH), new Dish("meate", false, 20000, Dish.Type.FISH), new Dish("dog", true, 30000, Dish.Type.FISH));
//        test01(menues);
//        test02(menues);
//        test03(menues);
//        test04(menues);
//        test05(menues);
        test06(menues);
    }


    /**
     * 把流中所有的元素收集到一个List中
     */
    public static void test01(List<Dish> menues) {
        List<Dish> dishes = menues.stream().collect(toList());

    }

    /**
     * 利用counting工厂方法返回的收集器，数一数菜单里有多少
     * 种菜
     *
     * @param menues
     */
    public static void test02(List<Dish> menues) {
        Long count = menues.stream().collect(counting());
        System.out.println(count);
        long count2 = menues.stream().count();
        System.out.println(count2);
    }

    /**
     * 查找流中的最大值和最小值:
     * 假设你想要找出菜单中热量最高的菜
     * 你可能在想Optional<Dish>是怎么回事。要回答这个问题，我们需要问“要是menu为空
     * 怎么办”。那就没有要返回的菜了！Java 8引入了Optional，它是一个容器，可以包含也可以不
     * 包含值。这里它完美地代表了可能也可能不返回菜肴的情况
     *
     * @param menues
     */
    public static void test03(List<Dish> menues) {
        Optional<Dish> optionalDish = menues.stream().collect(maxBy(comparing(Dish::getCalories)));
        if (optionalDish.isPresent()) {
            System.out.println("热量最大值为：" + optionalDish.get().getCalories());
        } else {
            System.out.println("没有菜啊，比啥比！");
        }
        Optional<Dish> optionalDish2 = menues.stream().collect(maxBy(comparingInt(Dish::getCalories)));
        Optional<Dish> optionalDish3 = menues.stream().max(comparing(Dish::getCalories));
        if (optionalDish3.isPresent()) {
            System.out.println("热量最大值为：" + optionalDish.get().getCalories());
        } else {
            System.out.println("没有菜啊，比啥比！");
        }
        Optional<Dish> minCalory = menues.stream().min(comparing(Dish::getCalories));
        if (minCalory.isPresent()) {
            System.out.println("热量最小值为：" + optionalDish.get().getCalories());

        } else {
            System.out.println("没有菜啊，比啥比！");
        }
    }

    /**
     * 汇总:
     * Collectors类专门为汇总提供了一个工厂方法：Collectors.summingInt。它可接受一
     * 个把对象映射为求和所需int的函数，并返回一个收集器；该收集器在传递给普通的collect方
     * 法后即执行我们需要的汇总操作。举个例子来说，你可以这样求出菜单列表的总热量
     * 这里的收集过程如图6-2所示。在遍历流时，会把每一道菜都映射为其热量，然后把这个数
     * 字累加到一个累加器（这里的初始值0）。
     * Collectors.summingLong和Collectors.summingDouble方法的作用完全一样，可以用
     * 于求和字段为long或double的情况。
     *
     * @param menues
     */
    public static void test04(List<Dish> menues) {
        Integer sumCalory = menues.stream().collect(summingInt(Dish::getCalories));
        System.out.println("总热量为：" + sumCalory);
        int sum = menues.stream().mapToInt(Dish::getCalories).sum();
        System.out.println("总热量为：" + sum);

    }

    /**
     * 汇总:求平均数
     *
     * @param menues
     */
    public static void test05(List<Dish> menues) {
        Double avgCalory = menues.stream().collect(averagingInt(Dish::getCalories));
        System.out.println(avgCalory);
    }

    /**
     * 汇总:
     * 到目前为止，你已经看到了如何使用收集器来给流中的元素计数，找到这些元素数值属性的
     * 最大值和最小值，以及计算其总和和平均值。不过很多时候，你可能想要得到两个或更多这样的
     * 结果，而且你希望只需一次操作就可以完成。在这种情况下，你可以使用summarizingInt工厂
     * 方法返回的收集器。例如，通过一次summarizing操作你可以就数出菜单中元素的个数，并得
     * 到菜肴热量总和、平均值、最大值和最小值
     *
     * @param menues
     */
    public static void test06(List<Dish> menues) {
        IntSummaryStatistics collectCalory = menues.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(collectCalory.toString());
        System.out.println("max:" + collectCalory.getMax() + ";min:" + collectCalory.getMin() + ";avg:" + collectCalory.getAverage() + "；sum" + collectCalory.getSum() + ";count" + collectCalory.getCount());
    }
}
