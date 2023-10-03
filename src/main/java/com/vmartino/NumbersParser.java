package com.vmartino;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class NumbersParser {
    private String normalizedInput;
    private String delimiter = ",";
    private NumbersValidator validator;

    public NumbersParser(NumbersValidator validator) {
        this.validator = validator;
    }

    public Stream<Integer> numbers(String input) {
        parse(input);

        if (normalizedInput.isEmpty())
            return Stream.of(0);

        String[] numbers;
        if (delimiter.length() > 1) {
            numbers = normalizedInput.split(delimiter);
        } else {
            numbers = normalizedInput.split("\\" + delimiter); // add \\ to escape the delimiter
        }
        
        validator.checkInvalidDelimiter(numbers, delimiter, normalizedInput);
        
        List<String> filterNegativeNumbers = validator.filterNegativeNumbers(numbers);
        validator.checkNegativeNumber(filterNegativeNumbers);

        return Arrays.stream(numbers).mapToInt(Integer::parseInt).boxed();
    }

    private void parse(String input) {
        if (input.startsWith("//")) {
            delimiter = input.substring(2, input.indexOf("\n"));
            input = input.substring(input.indexOf("\n") + 1);
        }
        normalizedInput = input.replace("\n", delimiter);
    }
/* 
    private void checkInvalidDelimiter(String[] numbers) {

        Arrays.stream(numbers)
                .filter(this::noNumbers)
                .findFirst()
                .ifPresent(this::throwInvalidDelimiterException);
    }

    private boolean noNumbers(String n) {
        return !n.matches("-?\\d+");
    }

    private int getPosition(String delimiter) {
        return normalizedInput.indexOf(delimiter);
    }

    private void throwInvalidDelimiterException(String itemWithInvalidDelimiter) {

        String invalidDelimiter = itemWithInvalidDelimiter.replaceAll("\\d", "");
        throw new IllegalArgumentException(
                String.format("'%s' expected but '%s' found at position %d",
                        delimiter,
                        invalidDelimiter,
                        getPosition(invalidDelimiter)));
    }
    */
}
