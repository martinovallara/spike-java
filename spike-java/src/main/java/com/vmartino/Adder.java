package com.vmartino;

import java.util.Arrays;

public class Adder {

    public int getSum(CalculatorParams params) {

        String normalizedInput = params.normalizedInput();
        if (normalizedInput.isEmpty()) return 0;
        String[] numbers = normalizedInput.split(params.delimiter());
        return Arrays.stream(numbers)
                    .mapToInt(Integer::parseInt )
                    .sum();
    }

}
