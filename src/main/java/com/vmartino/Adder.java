package com.vmartino;

import java.util.stream.Stream;

public class Adder {

    public Integer getSum(Stream<Integer> numbers) {
        return numbers
                .filter(this::onlyUpTo1000)
                .reduce(0, Integer::sum);
    }

    private boolean onlyUpTo1000(Integer x) {
        return x <= 1000;
    }
}
