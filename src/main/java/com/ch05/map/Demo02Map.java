package com.ch05.map;

import com.entity.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 映射:
 * 一个非常常见的数据处理套路就是从某些对象中选择信息。比如在SQL里，你可以从表中选
 * 择一列。Stream API也通过map和flatMap方法提供了类似的工具。
 */
public class Demo02Map {
    public static void main(String[] args) {
//        methord01();
//        methord02();
//        methord03();
//        methord04();
        methord05();
    }

    /**
     * 对流中每一个元素应用函数:
     * 流支持map方法，它会接受一个函数作为参数。这个函数会被应用到每个元素上，并将其映
     * 射成一个新的元素:
     */
    public static void methord01() {
        List<Dish> menues = Arrays.asList(new Dish("rice", true, 12, Dish.Type.FISH), new Dish("meat", false, 12, Dish.Type.FISH), new Dish("dog", true, 12, Dish.Type.FISH));
        //提取流中菜肴的名称;因为getName方法返回一个String，所以map方法输出的流的类型就是Stream<String>
        Set<String> nameSet = menues.stream().map(Dish::getName).collect(Collectors.toSet());
        System.out.println(nameSet.toString());
        //给定一个单词列表，你想要返回另一个列表，显示每个单词中有几个字母
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordsLengthList = words.stream().map(String::length).collect(Collectors.toList());
        System.out.println(wordsLengthList.toString());
        //如果你要找出每道菜的名称有多长
        List<Integer> nameLengthSet = menues.stream().map(Dish::getName).map(String::length).collect(Collectors.toList());
        System.out.println(nameLengthSet.toString());


    }

    /**
     * 流的扁平化:
     * 你已经看到如何使用map方法返回列表中每个单词的长度了。让我们拓展一下：对于一张单
     * 词表， 如何返回一张列表， 列出里面各不相同的字符呢？ 例如， 给定单词列表
     * ["Hello","World"]，你想要返回列表["H","e","l", "o","W","r","d"]。
     */
    public static void methord02() {
        String[] arrayOfWords = {"Goodbye", "World"};
        List<String> wordsList = Arrays.asList(arrayOfWords);
        //错误一：word -> word.split("") 返回值是一个Stream<String[]>  故结果集为List<String[]> words;有问题吧？哈哈
        List<String[]> words = wordsList.stream().map(word -> word.split("")).distinct().collect(Collectors.toList());
        //错误二：我们需要想让map返回一个Stream<String>;List<String>-->Stream<String>-->Stream<String[]>->Stream<Stream<String[]>>--->List<Stream<String>>
        Stream<String> wordsStream = Arrays.stream(arrayOfWords);//流的整合
        List<Stream<String>> collect = wordsList.stream().map(word -> word.split("")).map(Arrays::stream).distinct().collect(Collectors.toList());
        //正确:使用flatMap方法的效果是，各个数组并不是分别映射成一个流，而是映射成流的内容。所
        //有使用map(Arrays::stream)时生成的单个流都被合并起来，即扁平化为一个流;Stream<String>;List<String>-->Stream<String>-->Stream<String[]>->Stream<String>--->List<String>
        List<String> wordsSet = wordsList.stream().map(word -> word.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        System.out.println(wordsSet);

    }

    /**
     * 测试:
     * 给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？例如，给定[1, 2, 3, 4,
     * 5]，应该返回[1, 4, 9, 16, 25]。
     */
    public static void methord03() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> result = numbers.stream().map(number -> number * number).collect(Collectors.toList());
        System.out.println(result.toString());
    }

    /**
     * 给定两个数字列表，如何返回所有的数对呢？例如，给定列表[1, 2, 3]和列表[3, 4]，应
     * 该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。
     */
    public static void methord04() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<Integer[]> collect = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new Integer[]{i, j})).collect(Collectors.toList());
        for (Integer[] numerr :
                collect) {
            System.out.println(Arrays.asList(numerr).toString());

        }
    }

    /**
     * 如何扩展前一个例子，只返回总和能被3整除的数对呢？例如(2, 4)和(3, 3)是可以的。
     */
    public static void methord05() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<Integer[]> collect = numbers1.stream().flatMap(i -> numbers2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new Integer[]{i, j})).collect(Collectors.toList());
        for (Integer[] numerr :
                collect) {
            System.out.println(Arrays.asList(numerr).toString());

        }
    }
}
