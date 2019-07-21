package com.javafeature.v8.streamDemo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class StreamInAction {

    private static List<MyDish> menu = Arrays.asList(
            new MyDish("pork", false, 800, MyDish.Type.MEAT),
            new MyDish("beef", false, 700, MyDish.Type.MEAT),
            new MyDish("chicken", false, 400, MyDish.Type.MEAT),
            new MyDish("french fries", true, 530, MyDish.Type.OTHER),
            new MyDish("rice", true, 350, MyDish.Type.OTHER),
            new MyDish("season fruit", true, 120, MyDish.Type.OTHER),
            new MyDish("pizza", true, 550, MyDish.Type.OTHER),
            new MyDish("prawns", false, 300, MyDish.Type.FISH),
            new MyDish("salmon", false, 450, MyDish.Type.FISH) );

    public static void main(String[] args) {
        tasteStream();
//        streamNotice();
    }


    private static void tasteStream() {
        final List<String> collect = menu.stream()
                .filter(d->{
                    System.out.println("filtering:"+d.getName());
                    return d.getCalories()>300;
                })
                .map(d->{
                    System.out.println("mapping:"+d.getName());
                    return d.getName();
                })
                .limit(3)
                .collect(toList());
        System.out.println(collect);
    }

    //java.lang.IllegalStateException: stream has already been operated upon or closed
    private static void streamNotice() {
        final List<String> list = Arrays.asList("java8", "in", "action");
        final Stream<String> stream = list.stream();
        //流只能消费一次
        stream.forEach(System.out::println);
        stream.forEach(System.out::println);

    }
}
