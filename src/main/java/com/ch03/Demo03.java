package com.ch03;

import com.entity.Apple;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 使用函数式接口:
 * Java 8的库设计师帮你在java.util.function包中引入了几个新的函数式接口。我们接下
 * 来会介绍Predicate、Consumer和Function,更完整的列表，请查看api。
 */
public class Demo03 {
    public static void main(String[] args) {
//        testPredicate();
//        testConsumer();
        testFunction();
    }

    /**
     * java.util.function.Predicate<T>接口定义了一个名叫test的抽象方法，它接受泛型
     * T对象，并返回一个boolean。这恰恰和你先前创建的一样，现在就可以直接使用了。在你需要
     * 表示一个涉及类型T的布尔表达式时，就可以使用这个接口。比如，你可以定义一个接受String
     * 对象的Lambda表达式，如下所示。
     */
    public static void testPredicate() {
        List<Apple> inventory = Arrays.asList(new Apple("green", 18), new Apple("green", 13), new Apple("yellow", 12));
        List<Apple> apples = inventory.stream().filter((Apple a) -> a.getColor().equals("green")).collect(Collectors.toList());
        inventory.stream().forEach((Apple apple) -> System.out.println(apple.toString()));

    }

    /**
     * java.util.function.Consumer<T>定义了一个名叫accept的抽象方法，它接受泛型T
     * 的对象，没有返回（void）。你如果需要访问类型T的对象，并对其执行某些操作，就可以使用
     * 这个接口。比如，你可以用它来创建一个forEach方法，接受一个Integers的列表，并对其中
     * 每个元素执行操作
     */
    public static void testConsumer() {
        List<Apple> inventory = Arrays.asList(new Apple("green", 18), new Apple("green", 13), new Apple("yellow", 12));
        inventory.stream().forEach((Apple apple) -> System.out.println(apple.getColor()));
    }

    /**
     * java.util.function.Function<T, R>接口定义了一个叫作apply的方法，它接受一个
     * 泛型T的对象，并返回一个泛型R的对象。如果你需要定义一个Lambda，将输入对象的信息映射
     * 到输出，就可以使用这个接口（比如提取苹果的重量，或把字符串映射为它的长度）。在下面的
     * 代码中，我们向你展示如何利用它来创建一个map方法，以将一个苹果的颜色进行输出。
     */
    public static void testFunction(){
        //求苹果的颜色
        List<Apple> inventory = Arrays.asList(new Apple("green", 18), new Apple("green", 13), new Apple("yellow", 12));
        Set<String> colorSet = inventory.stream().map((Apple apple)->apple.getColor()).collect(Collectors.toSet());
        System.out.println(colorSet.toString());
    }
}
