package com.damon.test.util.collector;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class StringCollector implements Collector<String, StringCombiner, String> {

    private String deli;
    private String prefix;
    private String suffix;

    private StringCollector(String deli, String prefix, String suffix) {
        this.deli = deli;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public static StringCollector of(String deli, String prefix, String suffix) {
        return new StringCollector(deli, prefix, suffix);
    }



    @Override
    public Supplier<StringCombiner> supplier() {
        return () -> new StringCombiner(deli, prefix, suffix);
    }

    @Override
    public BiConsumer<StringCombiner, String> accumulator() {
        return StringCombiner::add;
    }

    @Override
    public BinaryOperator<StringCombiner> combiner() {
        return StringCombiner::merge;
    }

    @Override
    public Function<StringCombiner, String> finisher() {
        return StringCombiner::toString;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Characteristics.UNORDERED);
    }
}
