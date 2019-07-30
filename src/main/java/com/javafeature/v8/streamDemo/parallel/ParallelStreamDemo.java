package com.javafeature.v8.streamDemo.parallel;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelStreamDemo {
    public static void main(String[] args) {
        long n = 1_00_000_000;
        System.out.println("Sequential sum done in:" + measureSumPerf(ParallelStreamDemo::sequentialSum, n) + " msecs");
        System.out.println("Sequential sum with range done in:" + measureSumPerf(ParallelStreamDemo::sequentialSumWithRange, n) + " msecs");
        System.out.println("Iterative sum done in:" + measureSumPerf(ParallelStreamDemo::iterativeSum, n) + " msecs");
        System.out.println("Parallel sum done in: " + measureSumPerf(ParallelStreamDemo::parallelSum, n) + " msecs");
        System.out.println("Parallel sum with range done in: " + measureSumPerf(ParallelStreamDemo::parallelSumWithRange, n) + " msecs");
    }

    public static long measureSumPerf(Function<Long, Long> adderFunction, long n) {
        long fastest = Long.MAX_VALUE;
        //10次取最快的一次
        for (int i = 0; i < 10; i++) {
            //纳秒
            final long start = System.nanoTime();
            final Long sum = adderFunction.apply(n);
            final long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("result:" + sum);
            fastest = duration < fastest ? duration : fastest;
        }
        return fastest;
    }

    //串行
    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1) //产生装箱的对象
                .limit(n)
                .reduce(0L, Long::sum);//计算时需要拆箱
    }

    public static long sequentialSumWithRange(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(0L, Long::sum);
    }

    //传统方式
    public static long iterativeSum(long n) {
        long sum = 0;
        for (long i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    //并行
    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    //并行，
    public static long parallelSumWithRange(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }
}
