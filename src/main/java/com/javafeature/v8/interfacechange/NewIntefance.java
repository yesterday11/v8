package com.javafeature.v8.interfacechange;

/**
 * 函数式接口：只能有一个抽象方法，一般用@FunctionalInterface注解标识，
 */
@FunctionalInterface
public interface NewIntefance {
    void method1();

    /**
     * 默认方法：java8，接口可以定义默认方法，实现的此接口的类都可以调用此方法
     * 好处：接口新增功能后不必修改每个实现
     * 特殊：实现类实现了多个接口，但多个接口都包含某一默认方法，需要实现类重写该默认方法
     */
    default void method2(){

    }

    default void method3(){

    }

    /**
     * 静态方法
     */
    static void method4(){

    }

    static void method5(){

    }
}