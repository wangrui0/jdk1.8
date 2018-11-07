package com.ch06.others;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.IntStream;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

/**
 * 获取质素和非质素的优化；
 * 其中T、A和R分别是流中元素的类型、用于累积部分结果的对象类型，以及collect操作最
 * 终结果的类型。这里应该收集Integer流，而累加器和结果类型则都是Map<Boolean,
 * List<Integer>>
 */
public class PrimeNumbersCollector implements Collector<Integer,
        Map<Boolean, List<Integer>>,
        Map<Boolean, List<Integer>>> {
    /**
     * supplier方法会返回一个在调用时创建累加器的函数。
     * 这里不但创建了用作累加器的Map，还为true和false两个键下面初始化了对应的空列表。
     * 在收集过程中会把质数和非质数分别添加到这里。
     * @return
     */
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>() {{
            put(true, new ArrayList<Integer>());
            put(false, new ArrayList<Integer>());
        }};
    }

    /**
     * 收集器中最重要的方法是accumulator，因
     * 为它定义了如何收集流中元素的逻辑。这里它也是实现前面所讲的优化的关键。现在在任何一次
     * 迭代中，都可以访问收集过程的部分结果，也就是包含迄今找到的质数的累加器.在这个方法中，你调用了isPrime方法，将待测试是否为质数的数以及迄今找到的质数列表
     * （也就是累积Map中true键对应的值）传递给它。这次调用的结果随后被用作获取质数或非质数
     * 列表的键，这样就可以把新的被测数添加到恰当的列表中
     * @return
     */
    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
            acc.get(isPrime(acc.get(true),
                    candidate))
                    .add(candidate);
        };
    }

    /**
     * 要在并行收集时把两个部分累加器合并起来，这里，它只需要合并两个Map，即
     * 将第二个Map中质数和非质数列表中的所有数字合并到第一个Map的对应列表中就行了
     * @return
     */
    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> map1,
                Map<Boolean, List<Integer>> map2) -> {
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return map1;
        };
    }

    /**
     * 最后两个方法的实现都很简单。前面说过，accumulator正好就是收集器的结果，用不着
     * 进一步转换，那么finisher方法就返回identity函数
     * @return
     */
    @Override
    public Function<Map<Boolean, List<Integer>>,
            Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH));
    }

    public static boolean isPrime(List<Integer> primes, int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return takeWhile(primes, i -> i <= candidateRoot)
                .stream()
                .noneMatch(p -> candidate % p == 0);
    }

    public static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
        int i = 0;
        for (A item : list) {
            if (!p.test(item)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }

    public static void main(String[] args) {
        Map<Boolean, List<Integer>> map = new PrimeNumbersCollector().partitionPrimesWithCustomCollector(20);
        System.out.println(map.toString());
    }

    public Map<Boolean, List<Integer>>
    partitionPrimesWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(new PrimeNumbersCollector());
    }
}
