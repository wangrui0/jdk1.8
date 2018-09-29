package main.java.com.ch01;

import main.java.com.ch01.entity.Apple;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * jdk1.8体验
 */
public class Demo1 {
    public static void main(String[] args) {
        //打印文件夹下的隐藏的文件
//        outPutHiddenFiles("D:\\mnt");
        //
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("green", 2.5));
        apples.add(new Apple("green", 3.5));
        apples.add(new Apple("yellow", 4.5));
        List<Apple> result = filterGreenApples(apples);
        for (Apple apple :
                result) {
            System.out.println(apple);
        }
    }

    /**
     *
     */
    public static void outPutHiddenFiles(String file) {
        //1.8之前：
//        File[] hiddenFiles = new File(file).listFiles(new FileFilter() {
//            public boolean accept(File file) {
//                return file.isHidden();
//            }
//        });
        //1.8输出文件下的所有的隐藏文件
        File[] files = new File(file).listFiles(File::isHidden);
        for (File fileTemp :
                files) {
            System.out.println(fileTemp.getName());
        }
    }

    /**
     * 假设你有一个Apple类，它
     * 有一个getColor方法，还有一个变量inventory保存着一个Apples的列表。你可能想要选出所
     * 有的绿苹果，并返回一个列表
     */
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        //before
    /*    List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            //            if ("green".equals(apple.getColor())) {
//                result.add(apple);
//            }
            if (isGreenApple(apple)) {
                result.add(apple);
            }
        }*/
        //afterin
        // Java 8会把条件代码作为参数传递进去，这样可以避免filter方法
        //出现重复的代码。
        if (inventory == null || inventory.size() == 0) {
            return null;
        }
        //法一：将方法引用isGreenApple传递给filter进行过滤
//        List<Apple> result = inventory.stream().filter(Apple::isGreenApple).collect(Collectors.toList());
        //法一：可以直接将方法提上去，更灵活
        List<Apple> result = inventory.stream().filter((Apple a) -> "green".equals(a.getColor())).collect(Collectors.toList());
//        List<Apple> result = inventory.stream().filter((Apple a) -> "green".equals(a.getColor())||a.getWeight()>50).collect(Collectors.toList());
        return result;
    }

    /**
     * 很多的底层源码的都是这样写的，这里也有java8的影子
     *
     * @param apple
     * @return
     */
    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }
}
