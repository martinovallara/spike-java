package com.vmartino;

import java.util.stream.Stream;

public class StringCalculator {

    public String add(String input) {
        NumbersValidator validator = new NumbersValidator();

        NumbersParser parser = new NumbersParser(validator);

        Stream<Integer> numbers = parser.numbers(input);
        return new Adder(validator).getSum(numbers);
    }
}
