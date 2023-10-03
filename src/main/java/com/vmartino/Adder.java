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

        List<Integer> numberList = toList(numbers);
        return aStream(numberList).reduce(0, Integer::sum).toString();
    }

    private static Stream<Integer> aStream(List<Integer> numbers) {
        return numbers.stream();     
    }
    
    private static List<Integer> toList(Stream<Integer> numbers) {
        return numbers.collect(Collectors.toList());
    }
}
