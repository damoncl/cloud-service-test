package com.damon.test.service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionService {
    public static void main(String[] args) {
        Consumer<String> fun1 = n -> System.out.print(n + "--1--");
        Consumer<String> fun2 = n -> System.out.print(n + "--2--");
        Consumer<String> fun3 = n -> System.out.print(n + "--3--");

//        fun1.andThen(fun2).andThen(fun3).accept("damon");
//        testFunction();
//        testStreamIte();
//        testFlatMap();
        testJoin();
    }

    public static void testFunction() {
        Function<Integer, Integer> fun1 = in -> {
            System.out.println("fun1 in arg: " + Function.identity().apply(in));
            return in + 2;
        };
        Function<Integer, Integer> fun2 = in -> {
            System.out.println("fun2 in arg: " + Function.identity().apply(in));
            return in * in;
        };
        Integer apply = fun1.compose(fun2).apply(3);
        System.out.println("apply:" + apply);
    }

    public static void testStreamIte() {
        Object[] objects = Stream.iterate(2, n -> n * n).limit(6).toArray();
        System.out.println("ite: " + Arrays.toString(objects));

        Object[] objects1 = Stream.generate(() -> (int)(Math.random() * 100)).limit(10).toArray();
        System.out.println("gene: " + Arrays.toString(objects1));
    }

    public static void testFlatMap() {
        List<String> stringsA = Arrays.asList("hello", "world");
        Object[] collect = stringsA.stream()
                .peek(System.out::println)
                .map(str -> str.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .toArray();
        System.out.println(Arrays.toString(collect));

    }

    public static void testJoin() {
        List<String> stringsA = Arrays.asList("hello", "world");
        String collect = stringsA.stream()
                .collect(Collectors.joining(",", "before", "after"));
        System.out.println(collect);

    }
}
