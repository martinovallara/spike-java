package com.vmartino;

import java.util.stream.Stream;

public class StringCalculator {

    private NumbersParser parser;
    private Adder adder;

    public StringCalculator(Adder adder, NumbersParser parser) {
        this.parser = parser;
        this.adder = adder;
    }

    public String add(String input) {
        Stream<Integer> numbers = parser.numbers(input);
        return adder.getSum(numbers);
    }
}
