package com.damon.test.util.collector;

public class StringCombiner {

    private String deli;
    private String prefix;
    private String suffix;

    private StringBuilder stringBuilder = new StringBuilder();

    public StringCombiner(String deli, String prefix, String suffix) {
        this.deli = deli;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public StringCombiner add(String element) {
        if (areAtStart()) {
            stringBuilder.append(prefix);
        } else {
            stringBuilder.append(deli);
        }
        stringBuilder.append(element);
        return this;
    }

    public StringCombiner merge(StringCombiner other) {
        this.stringBuilder.append(other.stringBuilder);
        return this;
    }

    private boolean areAtStart() {
        return stringBuilder.length() < 1;
    }

    @Override
    public String toString() {
        return stringBuilder.append(suffix).toString();
    }
}
