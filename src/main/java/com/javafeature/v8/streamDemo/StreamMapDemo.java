package com.javafeature.v8.streamDemo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class StreamMapDemo {
    public static void main(String[] args) {
//        mapTest();
        example();
    }

    private static void mapTest() {
        String[] array={"hello","world"};
        final List<String[]> collect = Arrays.stream(array)
                .map(d -> d.split(""))
                .distinct()
                .collect(toList());
        System.out.println("collect："+collect);
        System.out.println("===============================");
        final List<Stream<String>> collect1 = Arrays.stream(array)
                .map(d -> d.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(toList());

        System.out.println("collect1："+collect1);
        System.out.println("=============================");
        final List<String> collect2 = Arrays.stream(array)
                .map(d -> d.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());

        System.out.println("collect2："+collect2);
    }

    private static void example() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs =
                numbers1.stream()
                        .flatMap(i -> numbers2.stream()
                                .map(j -> new int[]{i, j})
                        )
                        .collect(toList());

        pairs.stream().forEach(d-> {
            System.out.println("=======");
            Arrays.stream(d).forEach(System.out::println);
        });
    }
}
