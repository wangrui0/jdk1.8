package com.ch03;


/**
 * lambda 表达式就是一个匿名内部类;
 * 可以把Lambda表达式理解为简洁地表示可传递的匿名函数的一种方式：它没有名称，但它
 * 有参数列表、函数主体、返回类型，可能还有一个可以抛出的异常列表。这个定义够大的，让我
 * 们慢慢道来。
 * 匿名——我们说匿名，是因为它不像普通的方法那样有一个明确的名称：写得少而想
 * 得多！
 *  函数——我们说它是函数，是因为Lambda函数不像方法那样属于某个特定的类。但和方
 * 法一样，Lambda有参数列表、函数主体、返回类型，还可能有可以抛出的异常列表。
 *  传递——Lambda表达式可以作为参数传递给方法或存储在变量中。
 *  简洁——无需像匿名类那样写很多模板代码。
 * Lambda没有return语句，因为已经隐含了return
 */
public class Demo01 {
    /**
     * lambda是针对函数式接口（只有一个抽象方法的接口）来说的，lambda其实就是函数接口的匿名内部类哦
     *Lambda表达式可以被赋给一个变量，或传递给一个接受函数式接口作为参数的方法就好了
     * 只有在需要函数式接口的时候才可以传递Lambda呢
     * @param args
     */
    public static void main(String[] args) {
        //jdk1.8以前
//        Runnable runnable=new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("I am lambda")
//            }
//        };
        //java8以后
        Runnable runnable = () -> System.out.println("I am lambda");
        process(runnable);
        process(() -> System.out.println("hello"));
        process(() -> {
            System.out.println("hello");
        });//业务函数式接口哦
    }

    public static void process(Runnable r) {
        r.run();
    }


}
