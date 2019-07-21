package com.javafeature.v8.streamDemo.transactions;

import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class TransactionDemo {
    private static List<Transaction> transactions = TransactionsData.transactions;

    public static void main(String[] args) {
        //test1();
        //test2();
//        test3();
//        test4();
//test5();
//        test6();
        test7();
        test8();
    }

    private static void test1() {
        final List<Transaction> collect = transactions.stream()
                .filter(d -> d.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());

        System.out.println(collect);
    }

    private static void test2() {
        final List<String> collect = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(toList());
        System.out.println(collect);
        System.out.println("================");
        final Set<String> collect1 = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(toSet());
        System.out.println(collect1);
    }

    private static void test3() {
        final List<Trader> cambridge = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());
        System.out.println("test3:" + cambridge);
    }

    private static void test4() {
        final String collect = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(reducing(" ", (a, b) -> a + b));
        System.out.println("test4:" + collect);
        final String collect1 = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(joining(","));
        System.out.println("join:"+collect1);
    }

    private static void test5() {
        final boolean milan = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println("test5:"+milan);
    }

    private static void test6() {
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getTrader)
                .distinct()
                .forEach(System.out::println);
    }

    private  static void test7() {
        final Optional<Integer> reduce = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        reduce.ifPresent(System.out::println);
    }

    private  static void test8() {
        final Optional<Integer> reduce = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);
        reduce.ifPresent(System.out::println);
        System.out.println("=============");
        final Optional<Transaction> min = transactions.stream()
                .min(comparing(Transaction::getValue));
        min.ifPresent(System.out::println);
    }

}
