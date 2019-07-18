package com.javafeature.v8.dateDemo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OldDateInJava8 {
    public static void main(String[] args) {
        oldDateBadTest2();
    }

    //year 在1970的基础上进行累加
    private static void oldDateBadTest1() {
        final Date date = new Date(116, 2, 18);
        System.out.println(date);
    }

    private static void oldDateBadTest2() {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        for (int i=0;i<=30;i++) {
            new Thread(()->{
                for (int x=0;x<=1000;x++) {
                    final Date parse;
                    try {
                        parse = sdf.parse("20160714");
                        System.out.println(parse);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
