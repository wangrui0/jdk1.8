package com.ch06.groupingBy;

import com.entity.Dish;
import com.enums.CaloricLevel;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * 一个常见的数据库操作是根据一个或多个属性对集合中的项目进行分组。就像前面讲到按货
 * 币对交易进行分组的例子一样，如果用指令式风格来实现的话，这个操作可能会很麻烦、啰嗦而
 * 且容易出错。但是，如果用Java 8所推崇的函数式风格来重写的话，就很容易转化为一个非常容
 * 易看懂的语句。我们来看看这个功能的第二个例子：假设你要把菜单中的菜按照类型进行分类，
 * 有肉的放一组，有鱼的放一组，其他的都放另一组。用Collectors.groupingBy工厂方法返回
 * 的收集器就可以轻松地完成这项任务，
 */
public class Demo01 {
    public static void main(String[] args) {
        List<Dish> menues = Arrays.asList(new Dish("rice", true, 300, Dish.Type.FISH), new Dish("meate", false, 500, Dish.Type.MEAT), new Dish("dog", true, 30000, Dish.Type.FISH));
//        test01(menues);
//        test02(menues);
//        test03(menues);
//        test04(menues);
//        test05(menues);
//        test06(menues);
//        test07(menues);
        test08(menues);
    }

    /**
     * 单级分组：按照菜的类型进行分组
     *
     * @param menues
     */
    public static void test01(List<Dish> menues) {
        Map<Dish.Type, List<Dish>> dishMap = menues.stream().collect(groupingBy(Dish::getType));
        System.out.println(dishMap.toString());
    }

    /**
     * 单级分组： 但是，分类函数不一定像方法引用那样可用，因为你想用以分类的条件可能比简单的属性访
     * 问器要复杂。例如，你可能想把热量不到400卡路里的菜划分为“低热量”（diet），热量400到700
     * 卡路里的菜划为“普通”（normal），高于700卡路里的划为“高热量”（fat）。由于Dish类的作者
     * 没有把这个操作写成一个方法，你无法使用方法引用，但你可以把这个逻辑写成Lambda表达式：
     *
     * @param menues
     */
    public static void test02(List<Dish> menues) {
        Map<CaloricLevel, List<Dish>> caloricLevelMap = menues.stream().collect(groupingBy(dish -> {
            if (dish.getCalories() < 400) {
                return CaloricLevel.DIET;
            } else if (dish.getCalories() < 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        }));
        System.out.println(caloricLevelMap.toString());
    }

    /**
     * 多级分组：你已经看到了如何对菜单中的菜肴按照类型和热量进行分组，但要是想同时按照这两
     * 个标准分类怎么办呢？分组的强大之处就在于它可以有效地组合：
     * 要实现多级分组，我们可以使用一个由双参数版本的Collectors.groupingBy工厂方法创
     * 建的收集器，它除了普通的分类函数之外，还可以接受collector类型的第二个参数。那么要进
     * 行二级分组的话，我们可以把一个内层groupingBy传递给外层groupingBy，并定义一个为流
     * 中项目分类的二级标准，如代码
     *
     * @param menues
     */
    public static void test03(List<Dish> menues) {
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishTypeCaloricLevelMap = menues.stream().collect(Collectors.groupingBy(Dish::getType, groupingBy(dish -> {
            if (dish.getCalories() < 400) {
                System.out.println("DIET");
                return CaloricLevel.DIET;
            } else if (dish.getCalories() < 700) {
                System.out.println("NORMAL");
                return CaloricLevel.NORMAL;
            } else {
                System.out.println("FAT");
                return CaloricLevel.FAT;
            }
        })));
        System.out.println(dishTypeCaloricLevelMap);
    }

    /**
     * 多级分组:按子组收集数据中，我们看到可以把第二个groupingBy收集器传递给外层收集器来实现多级分
     * 组。但进一步说，传递给第一个groupingBy的第二个收集器可以是任何类型，而不一定是另一
     * 个groupingBy。例如，要数一数菜单中每类菜有多少个，可以传递counting收集器作为
     * groupingBy收集器的第二个参数:例如，要数一数菜单中每类菜有多少个，可以传递counting收集器作为
     * groupingBy收集器的第二个参数;
     * 还要注意，普通的单参数groupingBy(f)（其中f是分类函数）实际上是groupingBy(f,
     * toList())的简便写法。
     *
     * @param menues
     */
    public static void test04(List<Dish> menues) {
        Map<Dish.Type, Long> typeCountMap = menues.stream().collect(Collectors.groupingBy(Dish::getType, counting()));
        System.out.println(typeCountMap);
    }

    /**
     * 再举一个例子，你可以把前面用于查找菜单中热量最高的菜肴的收集器改一改，按照菜的类
     * 型分类：
     *
     * @param menues
     */
    public static void test05(List<Dish> menues) {
        Map<Dish.Type, Optional<Dish>> typeOptionalMap = menues.stream().collect(Collectors.groupingBy(Dish::getType, maxBy(comparingInt(Dish::getCalories))));
        System.out.println(typeOptionalMap.toString());

    }

    /**
     * 这个分组的结果显然是一个map，以Dish的类型作为键，以包装了该类型中热量最高的Dish
     * 的Optional<Dish>作为值：
     * 注意 这个Map中的值是Optional，因为这是maxBy工厂方法生成的收集器的类型，但实际上，
     * 如果菜单中没有某一类型的Dish，这个类型就不会对应一个Optional. empty()值，
     * 而且根本不会出现在Map的键中。groupingBy收集器只有在应用分组条件后，第一次在
     * 流中找到某个键对应的元素时才会把键加入分组Map中。这意味着Optional包装器在这
     * 里不是很有用，因为它不会仅仅因为它是归约收集器的返回类型而表达一个最终可能不
     * 存在却意外存在的值.
     * 把收集器的结果转换为另一种类型:
     * 因为分组操作的Map结果中的每个值上包装的Optional没什么用，所以你可能想要把它们
     * 去掉。要做到这一点，或者更一般地来说，把收集器返回的结果转换为另一种类型，你可以使用
     * Collectors.collectingAndThen工厂方法返回的收集器
     */
    public static void test06(List<Dish> menues) {
        Map<Dish.Type, Dish> typeDishlMap = menues.stream().collect(groupingBy(Dish::getType, collectingAndThen(maxBy(Comparator.comparing(Dish::getCalories))
                , Optional::get)));
        System.out.println(typeDishlMap.toString());


    }

    /**
     * 与groupingBy联合使用的其他收集器的例子:
     * 一般来说，通过groupingBy工厂方法的第二个参数传递的收集器将会对分到同一组中的所
     * 有流元素执行进一步归约操作。例如，你还重用求出所有菜肴热量总和的收集器，不过这次是对
     * 每一组Dish求和
     *
     * @param menues
     */
    public static void test07(List<Dish> menues) {
        Map<Dish.Type, Integer> dishMap = menues.stream().collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
        System.out.println(dishMap);

    }

    /**
     * 然而常常和groupingBy联合使用的另一个收集器是mapping方法生成的。这个方法接受两
     * 个参数：一个函数对流中的元素做变换，另一个则将变换的结果对象收集起来。其目的是在累加
     * 之前对每个输入元素应用一个映射函数，这样就可以让接受特定类型元素的收集器适应不同类型
     * 的对象。我们来看一个使用这个收集器的实际例子。比方说你想要知道，对于每种类型的Dish，
     * 菜单中都有哪些CaloricLevel。我们可以把groupingBy和mapping收集器结合起来
     *这里，就像我们前面见到过的，传递给映射方法的转换函数将Dish映射成了它的
     * CaloricLevel：生成的CaloricLevel流传递给一个toSet收集器，它和toList类似，不过是
     * 把流中的元素累积到一个Set而不是List中，以便仅保留各不相同的值。如先前的示例所示，这
     * 个映射收集器将会收集分组函数生成的各个子流中的元素，让你得到这样的Map结果：
     * @param menues
     */
    public static void test08(List<Dish> menues) {
        Map<Dish.Type, Set<CaloricLevel>> collectSet = menues.stream().collect(groupingBy(Dish::getType, mapping(dish -> {
            if (dish.getCalories() < 400) {
                return CaloricLevel.DIET;
            } else if (dish.getCalories() < 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        }, toSet())));
        System.out.println(collectSet);
    }
}
