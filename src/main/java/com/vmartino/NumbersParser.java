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

        String[] numbers = getNumbers();

        validator.checkInvalidDelimiter( delimiter, normalizedInput);
        validator.checkNegativeNumber(numbers);

        return Arrays.stream(numbers).mapToInt(Integer::parseInt).boxed();
    }

    public static String[] getNumbers(String normalizedInput, String delimiter) {
        String escape = delimiter.length() == 1 ? "\\" : "";
        return normalizedInput.split(escape + delimiter); // add \\ to escape the delimiter
    }

    private String[] getNumbers() {
        return NumbersParser.getNumbers(normalizedInput, delimiter);
    }

    private void parse(String input) {
        if (input.startsWith("//")) {
            delimiter = input.substring(2, input.indexOf("\n"));
            input = input.substring(input.indexOf("\n") + 1);
        }
        normalizedInput = input.replace("\n", delimiter);
    }
}
