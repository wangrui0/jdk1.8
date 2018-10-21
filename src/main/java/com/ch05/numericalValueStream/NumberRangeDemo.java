package com.ch05.numericalValueStream;

import java.util.stream.IntStream;

/**
 * 数值范围 :
 */
public class NumberRangeDemo {
    public static void main(String[] args) {
//        test01();
//        test02();
        test03();
    }

    /**
     * 生产1-100内的所有的偶数（包括100）
     */
    public static void test01() {
        IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0).forEach(n -> System.out.println(n));
        System.out.println(IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0).count());

    }

    /**
     * 生产1-100内的所有的偶数（不包括100）
     */

    public static void test02() {
        IntStream.range(1, 100).filter(n -> n % 2 == 0).forEach(n -> System.out.println(n));
    }

    /**
     * 应用打印指定范围的勾过数：(100)
     * 数值流应用：勾股数
     */
    public static void test03() {
        //法一:
//        IntStream.rangeClosed(1, 100).boxed().flatMap(a -> IntStream.rangeClosed(a, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).boxed().map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})).forEach(num -> System.out.println("(" + num[0] + "," + num[1] + "," + num[2] + ")"));
        //法二：
//        IntStream.rangeClosed(1, 100).boxed().flatMap(a -> IntStream.rangeClosed(a, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})).forEach(num -> System.out.println("(" + num[0] + "," + num[1] + "," + num[2] + ")"));
        //法三:优化一下，目前的解决办法并不是优的，因为你要求两次平方根。让代码更为紧凑的一种可能的方法 是，先生成所有的三元数(a*a, b*b, a*a+b*b)，然后再筛选符合条件的：
        IntStream.rangeClosed(1, 100).boxed().flatMap(a -> IntStream.rangeClosed(a, 100).mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})).filter(num -> num[2] % 1 == 0).forEach(num -> System.out.println("(" + num[0] + "," + num[1] + "," + num[2] + ")"));

    }
}
