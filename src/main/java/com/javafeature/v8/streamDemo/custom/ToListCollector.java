package com.javafeature.v8.streamDemo.custom;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {
    //创建结果集合
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    //将遍历的元素添加到结果集合
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    //并行时将第二个字累加器合并到第一个中
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    //处理返回结果，
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    //为收集器添加 IDENTITY_FINISH 和 CONCURRENT 标志
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH,CONCURRENT));
    }
}
