package com.javafeature.v8;

import com.javafeature.v8.streamDemo.Menu;
import com.javafeature.v8.streamDemo.MyDish;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.javafeature.v8.streamDemo.Menu.menu;
import static java.util.stream.Collectors.*;

public class collectorsDemo {
    public static void main(String[] args) {
//        summarizingTest();
//        joiningTest();
        groupByTest();
    }

    private static void summarizingTest() {
        final IntSummaryStatistics collect = menu.stream().collect(summarizingInt(MyDish::getCalories));
        System.out.println(collect);
    }

    private static void joiningTest() {
        System.out.println(menu.stream().map(MyDish::getName).collect(joining()));
        System.out.println("=========");
        //System.out.println(menu.stream().collect(joining()));
    }

    private static void groupByTest() {
        final Map<MyDish.Type, Map<Menu.CaloricLevel, List<MyDish>>> collect = menu.stream()
                .collect(groupingBy(MyDish::getType, groupingBy(myDish -> {
                    if (myDish.getCalories() <= 400) {
                        return Menu.CaloricLevel.DIET;
                    } else if (myDish.getCalories() <= 700) {
                        return Menu.CaloricLevel.NORMAL;
                    }
                    return Menu.CaloricLevel.FAT;
                })));
        System.out.println(collect);
        System.out.println("===================");
        final Map<MyDish.Type, Set<Menu.CaloricLevel>> collect1 = menu.stream()
                .collect(groupingBy(MyDish::getType, mapping(myDish -> {
                    if (myDish.getCalories() <= 400) {
                        return Menu.CaloricLevel.DIET;
                    } else if (myDish.getCalories() <= 700) {
                        return Menu.CaloricLevel.NORMAL;
                    }
                    return Menu.CaloricLevel.FAT;
                }, toSet())));
        System.out.println(collect1);
        System.out.println("==============");
        final Map<Boolean, List<MyDish>> collect2 = menu.stream()
                .collect(partitioningBy(MyDish::isVegetarian));

        System.out.println(collect2);

    }
}
