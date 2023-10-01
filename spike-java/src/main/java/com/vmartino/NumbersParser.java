package com.vmartino;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class NumbersParser {
    private String normalizedInput;
    private String delimiter = ",";

    public Stream<Integer> numbers(String input) {
        parse(input);

        if (normalizedInput.isEmpty())
            return Stream.of(0);
        
        String[] numbers = normalizedInput.split("\\" + delimiter);
        
        for (int i = 0; i < numbers.length; i++) {

            String delimiterWithoutNumbers = numbers[i].replaceAll("\\d", "");
            if (!delimiterWithoutNumbers.isEmpty()) {
                String exceptionMessage = String.format("'%s' expected but '%s' found at position %d", delimiter,
                        delimiterWithoutNumbers, i + 2);

                throw new IllegalArgumentException(exceptionMessage);
            }

        }

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
