package com.javafeature.v8.streamDemo.custom;

import com.javafeature.v8.streamDemo.Menu;
import com.javafeature.v8.streamDemo.MyDish;

import java.util.List;

public class ToListCollectorTest {
    public static void main(String[] args) {
        final List<MyDish> collect = Menu.menu.stream()
                .collect(new ToListCollector<MyDish>());
        System.out.println(collect);
    }
}
