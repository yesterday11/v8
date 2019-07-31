package com.javafeature.v8.optional;

import java.util.Optional;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class OptionalDemo {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("a", "5");
        props.setProperty("b", "true");
        props.setProperty("c", "-3");


        assertEquals(5, readDurationImperative(props, "a"));
        assertEquals(0, readDurationImperative(props, "b"));
        assertEquals(0, readDurationImperative(props, "c"));
        assertEquals(0, readDurationImperative(props, "d"));

        assertEquals(5, readDurationWithOptional(props, "a"));
        assertEquals(0, readDurationWithOptional(props, "b"));
        assertEquals(0, readDurationWithOptional(props, "c"));
        assertEquals(0, readDurationWithOptional(props, "d"));
    }

    public static int readDurationImperative(Properties props, String name) {
        final String result = (String) props.get(name);
        if (result != null) {
            try {
                final int i = Integer.parseInt(result);
                if (i>0) {
                    return i;
                }
            } catch (NumberFormatException e) {
                System.out.println("解析出错");
            }
        }
        return 0;
    }

    public static int readDurationWithOptional(Properties props, String name) {
        final Integer integer = Optional.ofNullable(props.getProperty(name))
                .flatMap(OptionalDemo::String2Int)
                .filter(i -> i > 0)
                .orElse(0);
        return integer;

    }

    private static Optional<Integer> String2Int(String value) {
        try {
           return Optional.ofNullable(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            System.out.println("解析出错");
        }
        return Optional.empty();
    }

}
