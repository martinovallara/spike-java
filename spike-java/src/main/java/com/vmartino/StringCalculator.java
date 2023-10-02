package com.vmartino;

public class StringCalculator {

    public String add(String input) {

        NumbersParser parser = new NumbersParser();

        return Adder.getSum(parser.numbers(input));
    }
}
