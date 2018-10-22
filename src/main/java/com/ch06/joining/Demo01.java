package com.ch06.joining;

import com.entity.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

/**
 * 连接字符串
 */
public class Demo01 {
    public static void main(String[] args) {
        List<Dish> menues = Arrays.asList(new Dish("rice", true, 10000, Dish.Type.FISH), new Dish("meate", false, 20000, Dish.Type.FISH), new Dish("dog", true, 30000, Dish.Type.FISH));
        test01(menues);
    }

    /**
     * joining工厂方法返回的收集器会把对流中每一个对象应用toString方法得到的所有字符
     * 串连接成一个字符串。这意味着你把菜单中所有菜肴的名称连接起来，如下所示:
     * 请注意，joining在内部使用了StringBuilder来把生成的字符串逐个追加起来。此外还
     * 要注意，如果Dish类有一个toString方法来返回菜肴的名称，那你无需用提取每一道菜名称的
     * 函数来对原流做映射就能够得到相同的结果
     */
    public static void test01(List<Dish> menues) {
        String nameString = menues.stream().map(Dish::getName).collect(joining());
        System.out.println(nameString);
//        但该字符串的可读性并不好。幸好，joining工厂方法有一个重载版本可以接受元素之间的
//        分界符，这样你就可以得到一个逗号分隔的菜肴名称列表：
        String nameString2 = menues.stream().map(Dish::getName).collect(Collectors.joining(", "));
        System.out.println(nameString2);
    }
}
