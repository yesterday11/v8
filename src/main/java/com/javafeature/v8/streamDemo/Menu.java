package com.javafeature.v8.streamDemo;

import java.util.Arrays;
import java.util.List;

public class Menu {
    public static List<MyDish> menu = Arrays.asList(
            new MyDish("pork", false, 800, MyDish.Type.MEAT),
            new MyDish("beef", false, 700, MyDish.Type.MEAT),
            new MyDish("chicken", false, 400, MyDish.Type.MEAT),
            new MyDish("french fries", true, 530, MyDish.Type.OTHER),
            new MyDish("rice", true, 350, MyDish.Type.OTHER),
            new MyDish("season fruit", true, 120, MyDish.Type.OTHER),
            new MyDish("pizza", true, 550, MyDish.Type.OTHER),
            new MyDish("prawns", false, 300, MyDish.Type.FISH),
            new MyDish("salmon", false, 450, MyDish.Type.FISH) );


  public   enum CaloricLevel { DIET, NORMAL, FAT };
}
