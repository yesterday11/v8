package com.javafeature.v8.dateDemo;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class NewDateInJava8 {
    public static void main(String[] args) throws InterruptedException {
        //localDateTest();
        //localTimeTest();
        //combineLocalDateAndTime();
//        instantTest()
//        dateFormatTest();
        dataParseTest();
    }

    private static void localDateTest() {
        final LocalDate localDate = LocalDate.of(2019, 7, 14);
        System.out.println("year:" + localDate.getYear());
        System.out.println("month:" + localDate.getMonth());
        System.out.println("monthValue:" + localDate.getMonthValue());
        System.out.println("dayOfMonth:" + localDate.getDayOfMonth());
        System.out.println("dayOfYear:" + localDate.getDayOfYear());
        System.out.println("dayOfWeek:" + localDate.getDayOfWeek());
        System.out.println(LocalDate.now());
    }

    private static void localTimeTest() {
        final LocalTime localTime = LocalTime.now();
        System.out.println("hour:" + localTime.getHour());
        System.out.println("minute:" + localTime.getMinute());
        System.out.println("second:" + localTime.getSecond());

    }

    private static void combineLocalDateAndTime() {
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime of = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        System.out.println("now:" + now);
        System.out.println("of:" + of);
    }

    private static void instantTest() throws InterruptedException {
        final Instant now = Instant.now();
        System.out.println("now"+now);
        Thread.sleep(1000L);
        final Instant end = Instant.now();
        System.out.println("end:"+end);
        System.out.println(Duration.between(now,end).toMillis());
    }

    private static void dateFormatTest() {
        final LocalDateTime now = LocalDateTime.now();
        final String format = now.format(DateTimeFormatter.BASIC_ISO_DATE);
        final String format1 = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(format);
        System.out.println(format1);
    }

    private static void dataParseTest() {
        String dateStr="2019-0714";
        final LocalDate parse = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MMdd"));
        System.out.println(parse);
    }

}
