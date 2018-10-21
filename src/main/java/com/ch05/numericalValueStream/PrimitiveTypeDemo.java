package com.ch05.numericalValueStream;

import com.entity.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Java 8引入了三个原始类型特化流接口来解决这个问题：IntStream、DoubleStream和
 * LongStream，分别将流中的元素特化为int、long和double，从而避免了暗含的装箱成本。每
 * 个接口都带来了进行常用数值归约的新方法，比如对数值流求和的sum，找到最大元素的max。
 * 此外还有在必要时再把它们转换回对象流的方法。要记住的是，这些特化的原因并不在于流的复
 * 杂性，而是装箱造成的复杂性——即类似int和Integer之间的效率差异
 */
public class PrimitiveTypeDemo {
    public static void main(String[] args) {
        List<Dish> menues = Arrays.asList(new Dish("rice", true, 10000, Dish.Type.FISH), new Dish("meat", false, 20000, Dish.Type.FISH), new Dish("dog", true, 30000, Dish.Type.FISH));
        test01(menues);
    }

    /**
     * 1. 映射到数值流:
     * 将流转换为特化版本的常用方法是mapToInt、mapToDouble和mapToLong。这些方法和前
     * 面说的map方法的工作方式一样，只是它们返回的是一个特化流，而不是Stream<T>。例如，你
     * 可以像下面这样用mapToInt对menu中的卡路里求和：
     * 这里，mapToInt会从每道菜中提取热量（用一个Integer表示），并返回一个IntStream
     * （而不是一个Stream<Integer>）。然后你就可以调用IntStream接口中定义的sum方法，对卡
     * 路里求和了！请注意，如果流是空的，sum默认返回0。IntStream还支持其他的方便方法，如
     * max、min、average等。
     */
    public static void test01(List<Dish> menues) {
        int sum = menues.stream().mapToInt(Dish::getCalories).sum();
        System.out.println(sum);
    }

    /**
     * 转换回对象流:
     * 同样，一旦有了数值流，你可能会想把它转换回非特化流。例如，IntStream上的操作只能
     * 产生原始整数： IntStream 的 map 操作接受的 Lambda 必须接受 int 并返回 int （一个
     * IntUnaryOperator）。但是你可能想要生成另一类值，比如Dish。为此，你需要访问Stream
     * 接口中定义的那些更广义的操作。要把原始流转换成一般流（每个int都会装箱成一个
     * Integer），可以使用boxed方法，如下所示：
     *
     * @param menues
     */

    public static void test02(List<Dish> menues) {
        IntStream intStream = menues.stream().mapToInt(Dish::getCalories);
        Stream<Integer> boxed = intStream.boxed();

    }

    /**
     * 默认值OptionalInt:
     * 求和的那个例子很容易，因为它有一个默认值：0。但是，如果你要计算IntStream中的最
     * 大元素，就得换个法子了，因为0是错误的结果。如何区分没有元素的流和最大值真的是0的流呢？
     * 前面我们介绍了Optional类，这是一个可以表示值存在或不存在的容器。Optional可以用
     * Integer、String等参考类型来参数化。对于三种原始流特化，也分别有一个Optional原始类
     * 型特化版本：OptionalInt、OptionalDouble和OptionalLong;
     * 例如，要找到IntStream中的最大元素，可以调用max方法，它会返回一个OptionalInt：
     *
     * @param menues
     */
    public static void test03(List<Dish> menues) {
        OptionalInt max = menues.stream().mapToInt(Dish::getCalories).max();
        if (max.isPresent()) {
            System.out.println("有最大值" + max.getAsInt());
        } else {
            int defaultValue = max.orElse(1);
            System.out.println("没有最大值，采用默认的最大值" + defaultValue);
        }
    }
}
