package com.ch05.match;

import com.entity.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 查找和匹配:(filter 和map的加强)
 * 另一个常见的数据处理套路是看看数据集中的某些元素是否匹配一个给定的属性。Stream
 * API通过allMatch、anyMatch、noneMatch、findFirst和findAny方法提供了这样的工具。
 */
public class Demo03Match {
    public static void main(String[] args) {
        List<Dish> menues = Arrays.asList(new Dish("rice", true, 10000, Dish.Type.FISH), new Dish("meat", false, 20000, Dish.Type.FISH), new Dish("dog", true, 30000, Dish.Type.FISH));
        test01(menues);
        test02(menues);
        test03(menues);
        test04();
    }

    /**
     * 检查谓词是否至少匹配一个元素:
     * anyMatch方法可以回答“流中是否有一个元素能匹配给定的谓词”。比如，你可以用它来看
     * 看菜单里面是否有素食可选择：
     */
    public static void test01(List<Dish> menues) {
        if (menues.stream().anyMatch(Dish::isVegetarian)) {//是不是list.contains()的加强呢？
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        } else {
            System.out.println("The menu is not (somewhat) vegetarian friendly!!");
        }
    }

    /**
     * 检查谓词是否匹配所有元素:
     * allMatch方法的工作原理和anyMatch类似，但它会看看流中的元素是否都能匹配给定的谓;
     * 和allMatch相对的是noneMatch。它可以确保流中没有任何元素与给定的谓词匹配
     * 举例：
     * 你可以用它来看看菜品是否有利健康（即所有菜的热量都低于1000卡路里）：
     */
    public static void test02(List<Dish> menues) {
        if (menues.stream().anyMatch(dish -> dish.getCalories() < 1000)) {
            System.out.println("The menu is (somewhat) healthy friendly!!");
        } else {
            System.out.println("The menu is not (somewhat) healthy friendly!!");
        }
        //或者使用noneMath
        if (menues.stream().noneMatch(dish -> dish.getCalories() >= 1000)) {
            System.out.println("The menu is (somewhat) healthy friendly!!");
        } else {
            System.out.println("The menu is not (somewhat) healthy friendly!!");
        }
    }

    /**
     * findAny方法将返回当前流中的任意元素。它可以与其他流操作结合使用。比如，你可能想（针对无序的比较好；）
     * 找到一道素食菜肴;
     * 方案：
     * 你可以结合使用filter和findAny方法来实现这个查询：
     * 流水线将在后台进行优化使其只需走一遍，并在利用短路找到结果时立即结束；ifPresent会在值存在的时候执行给定的代码块
     */
    public static void test03(List<Dish> menues) {
        menues.stream().filter(Dish::isVegetarian).findAny().ifPresent(dish -> System.out.println(dish.getName()));
    }

    /**
     * 有些流有一个出现顺序（encounter order）来指定流中项目出现的逻辑顺序（比如由List或
     * 排序好的数据列生成的流）。对于这种流，你可能想要找到第一个元素。为此有一个findFirst
     * 方法，它的工作方式类似于findany。例如，给定一个数字列表，下面的代码能找出第一个平方
     * 能被3整除的数：
     * 何时使用findFirst和findAny
     * 你可能会想，为什么会同时有findFirst和findAny呢？答案是并行。找到第一个元素
     * 在并行上限制更多。如果你不关心返回的元素是哪个，请使用findAny，因为它在使用并行流
     * 时限制较少。
     */
    public static void test04() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        numbers.stream().map(n -> n * n).filter(n -> n % 3 == 0).findFirst().ifPresent(n -> System.out.println("第一个能被三整除的元素的平方是:" + n));
    }
}
