package com.vmartino;

import java.util.stream.Stream;

public class Adder {

    private NumbersValidator validator;

    public Adder(NumbersValidator validator) {
        this.validator = validator;
    }

    public String getSum(Stream<Integer> numbers) {

        if (validator.anyErrors())
            return validator.getValidationMessage();

        return numbers
                .filter(this::onlyUpTo1000)
                .reduce(0, Integer::sum)
                .toString();
    }

    private boolean onlyUpTo1000(Integer x) {
        return x <= 1000;
    }
}
