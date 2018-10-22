package com.ch06.partitioningBy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 测验：将数字按质数和非质数分区
 */
public class Demo02 {
    public static void main(String[] args) {
        Map<Boolean, List<Integer>> partitionPrimes = partitionPrimes(50);
        System.out.println("质素：" + partitionPrimes.get(true));
        System.out.println("非质素:" + partitionPrimes.get(false));
    }

    /**
     * 写一个方法，它接受参数int n，并将前n个自然数分为质数和非质数
     */
    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(Collectors.partitioningBy(candidate -> isPrime2(candidate)));
    }

    /**
     * 判断一个数是否是质素
     *
     * @param candidate
     * @return
     */
    public static boolean isPrime(int candidate) {
        return IntStream.range(2, candidate).boxed().noneMatch(n -> candidate % n == 0);
    }
    /**
     * 判断一个数是否是质素:优化
     * @param candidate
     * @return
     */
    public static boolean isPrime2(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.range(2, candidateRoot).boxed().noneMatch(n -> candidate % n == 0);
    }
}
