package com.vmartino;

import java.util.Arrays;
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
        String escape = delimiter.length() == 1 ? "\\" : "";

        numbers = normalizedInput.split(escape + delimiter); // add \\ to escape the delimiter

        validator.checkInvalidDelimiter(numbers, delimiter, normalizedInput);
        validator.checkNegativeNumber(numbers);

        return Arrays.stream(numbers).mapToInt(Integer::parseInt).boxed();
    }

    private void parse(String input) {
        if (input.startsWith("//")) {
            delimiter = input.substring(2, input.indexOf("\n"));
            input = input.substring(input.indexOf("\n") + 1);
        }
        normalizedInput = input.replace("\n", delimiter);
    }
}
