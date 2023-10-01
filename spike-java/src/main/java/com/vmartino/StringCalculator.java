package com.vmartino;

import java.util.Arrays;

public class StringCalculator {

    public int add(String input) {

        DelimiterParser parser = new DelimiterParser();
        parser.parse(input);

        String delimiter = parser.getDelmiter();
        String normalizedInput = parser.getNormalizedInput();

        if (normalizedInput.isEmpty()) return 0;
        String[] numbers = normalizedInput.split(delimiter);
        return Arrays.stream(numbers)
                    .mapToInt(Integer::parseInt )
                    .sum();
    }
}
