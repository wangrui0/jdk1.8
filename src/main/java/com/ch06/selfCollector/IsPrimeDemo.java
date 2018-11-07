package com.ch06.selfCollector;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 给出指定的数，输出该数以前的所有的基数和偶数
 */
public class IsPrimeDemo {
    public static void main(String[] args) {
        Map<Boolean, List<Integer>> map = partitionPrimes(50);
        System.out.println(map.toString());
    }

    private static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(Collectors.partitioningBy(candidate -> isPrime(candidate)));
    }

    /**
     * 判断一个数是基数还是偶数：
     * @param candidate
     * @return
     */
    private static boolean isPrime(int candidate) {
        //通过限制除数不超过被测试数的平方根
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(n -> n % 2 == 0);
    }
}
