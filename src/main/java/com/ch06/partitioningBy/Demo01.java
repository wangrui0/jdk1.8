package com.ch06.partitioningBy;

import com.entity.Dish;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.partitioningBy;

/**
 * 分区：
 * 分区是分组的特殊情况：由一个谓词（返回一个布尔值的函数）作为分类函数，它称分区函
 * 数。分区函数返回一个布尔值，这意味着得到的分组Map的键类型是Boolean，于是它最多可以
 * 分为两组——true是一组，false是一组
 */
public class Demo01 {
    public static void main(String[] args) {
        List<Dish> menues = Arrays.asList(new Dish("rice", true, 300, Dish.Type.FISH), new Dish("meate", false, 500, Dish.Type.MEAT), new Dish("dog", true, 30000, Dish.Type.FISH));
//        test01(menues);
//        test02(menues);
        test03(menues);
    }

    /**
     * 如果你是素食者或是请了一位素食的朋友来共
     * 进晚餐，可能会想要把菜单按照素食和非素食分开
     */
    public static void test01(List<Dish> menues) {
        //根据是否是素食主义进行分区
        Map<Boolean, List<Dish>> listMap = menues.stream().collect(Collectors.groupingBy(Dish::isVegetarian));
        //那么通过Map中键为true的值，就可以找出所有的素食菜肴了
        List<Dish> vegetarianPepole = listMap.get(true);
        if (vegetarianPepole != null) {
            System.out.println("素食菜肴是：" + vegetarianPepole);
        } else {
            System.out.println("没有素食菜");
        }
    }

    /**
     * 分区的优势:
     * 分区的好处在于保留了分区函数返回true或false的两套流元素列表。在上一个例子中，要
     * 得到非素食Dish的List，你可以使用两个筛选操作来访问partitionedMenu这个Map中false
     * 键的值：一个利用谓词，一个利用该谓词的非。而且就像你在分组中看到的，partitioningBy
     * 工厂方法有一个重载版本，可以像下面这样传递第二个收集器：
     * 先按照素食主义者进行分区，再在每个区中按照菜的类型进行分组
     */
    public static void test02(List<Dish> menues) {
        Map<Boolean, Map<Dish.Type, List<Dish>>> groupAndPartition = menues.stream().collect(partitioningBy(Dish::isVegetarian, Collectors.groupingBy(Dish::getType)));
        System.out.println(groupAndPartition);

    }

    /**
     * 再举一个例子，你可以重用前面的代码来找到素食和非素
     * 食中热量最高的菜：
     */
    public static void test03(List<Dish> menues) {
        Map<Boolean, Optional<Dish>> dishMap = menues.stream().collect(partitioningBy(Dish::isVegetarian, Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println(dishMap.toString());
        //优化
        Map<Boolean, Dish> dishMap2 = menues.stream().collect(partitioningBy(Dish::isVegetarian, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println(dishMap2.toString());
        //{false=Optional[Dish{name='meate', vegetarian=false, calories=500, type=MEAT}], true=Optional[Dish{name='dog', vegetarian=true, calories=30000, type=FISH}]}
        //{false=Dish{name='meate', vegetarian=false, calories=500, type=MEAT}, true=Dish{name='dog', vegetarian=true, calories=30000, type=FISH}}
    }

    /**
     * 我们在本节开始时说过，你可以把分区看作分组一种特殊情况。 groupingBy 和
     * partitioningBy收集器之间的相似之处并不止于此；你在下一个测验中会看到，还可以按照以往的分组类似的方式进行多级分区；
     * 看看几个简单的例子：
     * 答案:
     * (1) 这是一个有效的多级分区，产生以下二级Map：
     * { false={false=[chicken, prawns, salmon], true=[pork, beef]},
     *  true={false=[rice, season fruit], true=[french fries, pizza]}}
     * (2) 这无法编译，因为partitioningBy需要一个谓词，也就是返回一个布尔值的函数。
     * 方法引用Dish::getType不能用作谓词。
     * (3) 它会计算每个分区中项目的数目，得到以下Map：
     * {false=5, true=4}
     */
    public static void test04(List<Dish> menu) {
        Map<Boolean, Map<Boolean, List<Dish>>> map1 = menu.stream().collect(partitioningBy(Dish::isVegetarian,
                partitioningBy(d -> d.getCalories() > 500)));
        Map<Boolean, Long> map2 = menu.stream().collect(partitioningBy(Dish::isVegetarian,
                counting()));
//        menu.stream().collect(partitioningBy(Dish::isVegetarian,
//                partitioningBy (Dish::getType)));
    }
}
