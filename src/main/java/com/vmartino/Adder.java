package com.vmartino;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Adder {

    private NumbersValidator validator;

    public Adder(NumbersValidator validator) {
        this.validator = validator;
    }

    public String getSum(Stream<Integer> numbers) {

        if (validator.anyErrors()) return validator.getValidationMessage();

        return numbers.reduce(0, Integer::sum).toString();
    }
}
