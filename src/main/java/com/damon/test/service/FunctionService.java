package com.damon.test.service;

import com.damon.test.beans.bo.base.Artist;
import com.damon.test.util.collector.StringCollector;
import org.springframework.util.Assert;

import java.util.*;
import java.util.function.BinaryOperator;
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
//        testJoin();
        testBinaryOperator();
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

    public static void testBinaryOperator() {
        BinaryOperator<Integer> binaryOperator = (x, y) -> x + y;
        System.out.println(binaryOperator.apply(23, 56));

        List<String> strings = Stream.of("a", "bbc")
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        strings.forEach(System.out::println);

        Integer reduce = Stream.of(1, 2, 3)
                .reduce(0, (x, y) -> x + y);

        Stream.of(new Artist("甲壳虫乐队", "英格兰"), new Artist("青春", "china"))
                .flatMap(artist -> Stream.of(artist.getName(), artist.getOrigin()))
                .collect(Collectors.toList());

        long count = "fewfwebFEWFWEFSefsefeWEfSEfwefwer".chars()
                .filter(ch -> ch >= 'a' && ch <= 'z')
                .count();

        String a = Stream.of("abCE", "dfwefFWEF", "fwefwerwrSEfserFEwer", "fwerwerawerawefawe4taertoigjoer")
                .max(Comparator.comparing(str -> str.chars()
                        .filter(ch -> ch >= 'a' && ch <= 'z')
                        .count()))
                .get();
        System.out.println("小写字母最多的：" + a);

        String collect = Stream.of("甲壳虫乐队", "英格兰", "青春", "china")
                .collect(StringCollector.of(",", "[", "]"));
        System.out.println("my owen collector:" + collect);

        Map<String, Long> collectMap = Stream.of("John", "Paul", "George", "John", "Paul", "John")
                .collect(Collectors.groupingBy(Function.identity(), TreeMap::new, Collectors.counting()));
        collectMap.forEach((key, value) -> {
            System.out.print(key + " -> " + value);
        });
    }

    public static <T, R> List<R> map(Stream<T> stream, Function<T, R> operator) {
        return stream.reduce(new ArrayList<R>(), (List<R> list, T item) -> {
            List<R> result = new ArrayList<>(list);
            result.add(operator.apply(item));
            return result;
        }, (List<R> left, List<R> right) -> {
            List<R> result = new ArrayList<>(left);
            result.addAll(right);
            return result;
        });
    }


}
