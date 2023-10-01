package com.vmartino;

import java.util.Arrays;

public class StringCalculator {

    public int add(String input) {
        if (input.isEmpty()) return 0;
        String[] numbers = input.split(",");
        return Arrays.stream(numbers)
                    .mapToInt(Integer::parseInt )
                    .sum();
    }
}
