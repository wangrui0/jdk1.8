package com.ch05.generateStream;

import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 构建流
 * Stream API提供了两个静态方法来从函数生成流：Stream.iterate和Stream.generate。
 * 这两个操作可以创建所谓的无限流：不像从固定集合创建的流那样有固定大小的流。
 * 由iterate 和generate产生的流会用给定的函数按需创建值，因此可以无穷无尽地计算下去！一般来说，
 * 应该使用limit(n)来对这种流加以限制，以避免打印无穷多个值。
 */
public class GenerateStreamDemo {
    public static void main(String[] args) {
        test01();
        test02();
        test03();
        test04();
        test05();
        test06();
        test07();
    }

    /**
     * 由值创建流:
     * 你可以使用静态方法Stream.of，通过显式值创建一个流。它可以接受任意数量的参数。例
     * 如，以下代码直接使用Stream.of创建了一个字符串流。然后，你可以将字符串转换为大写，再
     * 一个个打印出来：
     */
    public static void test01() {
        Stream<String> stream = Stream.of("java 8 ", " Lambdas ", " in ", " action ");
        stream.map(String::toUpperCase).forEach(System.out::print);
    }

    /**
     * 由数组创建流:
     * 你可以使用静态方法Arrays.stream从数组创建一个流。它接受一个数组作为参数。例如，
     * 你可以将一个原始类型int的数组转换成一个IntStream
     */
    public static void test02() {
        int[] arr = {1, 2, 3, 4, 5};
        int sum = Arrays.stream(arr).sum();
        System.out.println(sum);

    }

    /**
     * 由文件生成流:
     * Java中用于处理文件等I/O操作的NIO API（非阻塞 I/O）已更新，以便利用Stream API。
     * java.nio.file.Files中的很多静态方法都会返回一个流。例如，一个很有用的方法是
     * Files.lines，它会返回一个由指定文件中的各行构成的字符串流。使用你迄今所学的内容，
     * 你可以用这个方法看看一个文件中有多少各不相同的词：
     */
    public static void test03() {
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())
        ) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();
            System.out.println(uniqueWords);
        } catch (Exception e) {

        }
    }

    /**
     * 由函数生成流：创建无限流---迭代：
     * iterate方法接受一个初始值（在这里是0），还有一个依次应用在每个产生的新值上的 Lambda（UnaryOperator<t>类型）。
     * 这里，我们使用Lambda n -> n + 2，返回的是前一个元 素加上2。因此，iterate方法生成了一个所有正偶数的流：
     * 流的第一个元素是初始值0。然后加 上2来生成新的值2，再加上2来得到新的值4，以此类推。这种iterate操作基本上是顺序的，
     * 因为结果取决于前一次应用。请注意，此操作将生成一个无限流——这个流没有结尾，因为值是 按需计算的，可以永远计算下去。我们说这个流是无界的。
     * 正如我们前面所讨论的，这是流和集 合之间的一个关键区别。我们使用limit方法来显式限制流的大小。这里只选择了前10个偶数。
     * 然后可以调用forEach终端操作来消费流，并分别打印每个元素
     */
    public static void test04() {
        /**
         * 打印大于0的10个偶数
         */
        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);
    }

    /**
     * 由函数生成流：创建无限流---生成：
     * 与iterate方法类似，generate方法也可让你按需生成一个无限流。
     * 但generate不是依次 对每个新生成的值应用函数的。它接受一个Supplier<T>类型的Lambda提供新的值。
     * 我们先来 看一个简单的用法：
     */
    public static void test05() {
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

    /**
     * 练习:
     * 斐波纳契元组序列与此类似，是数列中数字和其后续数字组成的元组构成的序列：(0, 1), (1, 1), (1, 2), (2, 3), (3, 5), (5, 8), (8, 13), (13, 21) …
     */
    public static void test06() {
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).limit(10).forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));
    }

    /**
     * 练习:
     * 斐斐波纳契数列是著名的经典编程练习。下面这个数列就是斐波纳契数列的一部分：0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55…数列中开始的两个数字是0和1，
     * 后续的每个数字都是前两个数字之和
     */
    public static void test07() {
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).limit(10).map(t -> t[0]).forEach(System.out::println);
    }
}
