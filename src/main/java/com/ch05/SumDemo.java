package com.ch05;

import com.entity.Trader;
import com.entity.Transaction;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 总结练习：
 * (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
 * (2) 交易员都在哪些不同的城市工作过？
 * (3) 查找所有来自于剑桥的交易员，并按姓名排序。
 * (4) 返回所有交易员的姓名字符串，按字母顺序排序。
 * (5) 有没有交易员是在米兰工作的？
 * (6) 打印生活在剑桥的交易员的所有交易额。
 * (7) 所有交易中，最高的交易额是多少？
 * (8) 找到交易额最小的交易。
 */
public class SumDemo {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)

        );
//        test01(transactions);
//        test02(transactions);
//        test03(transactions);
//        test04(transactions);
//        test05(transactions);
//        test06(transactions);
        test07(transactions);
        test08(transactions);
    }

    /**
     * 找出2011年发生的所有交易，并按交易额排序（从低到高）
     *
     * @param transactions
     */
    public static void test01(List<Transaction> transactions) {
        List<Transaction> transactionList = transactions.stream().filter(transaction -> 2011 == transaction.getYear()).sorted(Comparator.comparing(Transaction::getValue)).
                collect(Collectors.toList());
        System.out.println(transactionList.toString());
    }

    /**
     * 交易员都在哪些不同的城市工作过？
     */
    public static void test02(List<Transaction> transactions) {
        Set<String> citySet = transactions.stream().map(transaction -> transaction.getTrader()).map(Trader::getCity).collect(Collectors.toSet());
        System.out.println(citySet.toString());
    }

    /**
     * 查找所有来自于剑桥的交易员，并按姓名排序
     */
    public static void test03(List<Transaction> transactions) {
        List<Trader> traderList = transactions.stream().map(Transaction::getTrader).filter(trader -> trader.getCity().equals("Cambridge")).sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());
        System.out.println(traderList.toString());
    }

    /**
     * 返回所有交易员的姓名字符串，按字母顺序排序
     */
    public static void test04(List<Transaction> transactions) {
        List<String> nameList = transactions.stream().map(Transaction::getTrader).map(Trader::getName).sorted(String::compareTo).collect(Collectors.toList());
        System.out.println(nameList.toString());
    }

    /**
     * 有没有交易员是在米兰工作的
     */
    public static void test05(List<Transaction> transactions) {
        if (transactions.stream().map(Transaction::getTrader).anyMatch(trade -> trade.getCity().equals("Milan"))) {
            System.out.println("有在米兰工作");
        } else {
            System.out.println("没有在米兰工作");
        }
    }

    /**
     * 打印生活在剑桥的交易员的所有交易额
     */
    public static void test06(List<Transaction> transactions) {
        Optional<Integer> sumValue = transactions.stream().filter(transaction -> transaction.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue).reduce(Integer::sum);
        if (sumValue.isPresent()) {
            System.out.println(sumValue.get());
        }
    }

    /**
     * 所有交易中，最高的交易额是多少？
     */
    public static void test07(List<Transaction> transactions) {
        Optional<Integer> maxValue = transactions.stream().map(Transaction::getValue).reduce(Integer::max);
        if (maxValue.isPresent()) {
            System.out.println(maxValue.get());
        }
    }

    /**
     * 找到交易额最小的交易。
     */
    public static void test08(List<Transaction> transactions) {
        Optional<Integer> minValue = transactions.stream().map(Transaction::getValue).reduce(Integer::min);
        if (minValue.isPresent()) {
            System.out.println(minValue.get());
        }
        Optional<Transaction> minValue2 = transactions.stream().min(Comparator.comparing(Transaction::getValue));

    }
}