package com.javafeature.v8.streamDemo;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class CreateStreamDemo {
    public static void main(String[] args) {
       /* int[] numbers = {2, 3, 5, 7, 11, 13};
        System.out.println(Arrays.stream(numbers).sum());*/

//        test();

        fileCreateStreamTest();
    }

    private static void fileCreateStreamTest() {
        try (final Stream<String> lines = Files.lines(Paths.get("src/main/resources","streamData/data.txt"), Charset.defaultCharset())){
            lines.forEach(System.out::println);
    } catch (IOException e) {
            e.printStackTrace();
        }



    }

    // Stream.iterate
    private static void test() {
        Stream.iterate(0, n -> n + 2)
               // .limit(10)
                .forEach(System.out::println);
    }
}
