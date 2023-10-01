package com.vmartino;

import java.util.Arrays;

public class StringCalculator {

    public int add(String input) {

        DelimiterParser parser = new DelimiterParser();
        CalculatorParams params = parser.parse(input);

        Adder adder = new Adder();
        return adder.getSum(params);
    }
}
