package com.ch05.stipulationsAgreement;

import com.entity.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 规约
 */
public class Demo04 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        test01(numbers);
        test02(numbers);
        test03(numbers);
        test04(numbers);
        test05();
    }

    /**
     * 求和
     */
    public static void test01(List<Integer> numbers) {
//        Integer sum = numbers.stream().reduce(0, (a, b) -> a + b);
//        int sum = Integer.sum(1, 2);
//        Integer sum = numbers.stream().reduce(0, Integer::sum);//Integer类现在有了一个静态的sum方法来对两个数求和，这恰好是我们想要的，用不着反复用Lambda写同一段代码了
        //为什么它返回一个Optional<Integer>呢？考虑流中没有任何元素的情况。reduce操作无
        //法返回其和，因为它没有初始值。这就是为什么结果被包裹在一个Optional对象里，以表明和
        //可能不存在。现在看看用reduce还能做什么。
        Optional<Integer> sumOptional = numbers.stream().reduce(Integer::sum);
        if (sumOptional.isPresent()) {
            System.out.println(sumOptional.get());
        }
    }

    /**
     * 求积
     */
    public static void test02(List<Integer> numbers) {
//        Integer accumulate = numbers.stream().reduce(1, (a, b) -> a * b);
        Optional<Integer> accumulateOptional = numbers.stream().reduce((a, b) -> a * b);
        if (accumulateOptional.isPresent()) {
            System.out.println(accumulateOptional.get());
        }
    }

    /**
     * 求最大值
     */
    public static void test03(List<Integer> numbers) {
//        Optional<Integer> optionalMaxValue = numbers.stream().reduce(Integer::max);
        Optional<Integer> optionalMaxValue = numbers.stream().reduce((a, b) -> a > b ? a : b);
        if (optionalMaxValue.isPresent()) {
            System.out.println("最大值为：" + optionalMaxValue.get());
        }
    }

    /**
     * 求最小值
     */
    public static void test04(List<Integer> numbers) {
//        Optional<Integer> optionalMinValue = numbers.stream().reduce(Integer::min);
        Optional<Integer> optionalMinValue = numbers.stream().reduce((a, b) -> a > b ? b : a);
        if (optionalMinValue.isPresent()) {
            System.out.println("最小值：" + optionalMinValue.get());
        }
    }

    /**
     * 数一数有多少个菜肴:
     * 要解决这个问题，你可以把流中每个元素都映射成数字1，然后用reduce求和。这
     * 相当于按顺序数流中的元素个数仅仅是为了练习啊
     */
    public static void test05() {
        List<Dish> menues = Arrays.asList(new Dish("rice", true, 10000, Dish.Type.FISH), new Dish("meat", false, 20000, Dish.Type.FISH), new Dish("rice", true, 30000, Dish.Type.FISH));
//        Optional<Integer> menuesSum = menues.stream().map(dish -> 1).reduce(Integer::sum);
//        if(menuesSum.isPresent()){
//            System.out.println(menuesSum.get());
//        }
        long count = menues.stream().count();
        System.out.println(count);
    }
}
