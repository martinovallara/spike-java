package com.vmartino;

import java.util.stream.Stream;

public class Adder {

    private Adder() {}

    public static Integer getSum(Stream<Integer> numbers) {
        return numbers.reduce(0, Integer::sum);
    }
}
