package com.vmartino;

import java.util.stream.Stream;

public class StringCalculator {

    private NumbersParser parser;
    private Adder adder;
    private NumbersValidator validator;

    public StringCalculator(Adder adder, NumbersParser parser, NumbersValidator validator) {
        this.parser = parser;
        this.adder = adder;
        this.validator = validator;
    }

    public String add(String input) {
        Stream<Integer> numbers = parser.numbers(input);

        if (validator.anyErrors())
            return validator.getValidationMessage();

        return adder.getSum(numbers).toString();
    }
}
