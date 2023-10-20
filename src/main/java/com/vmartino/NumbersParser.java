package com.vmartino;


import java.util.stream.Stream;

public class NumbersParser {
    private NumbersValidator validator;
    private CalculatorRequest calculatorRequest;

    public NumbersParser(NumbersValidator validator, CalculatorRequest calculatorRequest) {
        this.validator = validator;
        this.calculatorRequest = calculatorRequest;
    }

    public Stream<Integer> numbers(String input) {
        parse(input);
        
        validator.setCalculatorRequest(calculatorRequest);
        validator.checkInvalidDelimiter();
        validator.checkNegativeNumber();

        return calculatorRequest.numbers();
    }

    private void parse(String input) {
        String delimiter = ",";
        if (existDelimiterConfiguration(input)) {
            delimiter = getDelimiterFromConfiguration(input);
            input = extractInput(input);
        }
        String normalizedInput = input.replace("\n", delimiter);

        calculatorRequest.setData(normalizedInput, delimiter);
    }

    private boolean existDelimiterConfiguration(String input) {
        return input.startsWith("//");
    }

    private String extractInput(String input) {
        return input.substring(input.indexOf("\n") + 1);
    }

    private String getDelimiterFromConfiguration(String input) {
        return input.substring(2, input.indexOf("\n"));
    }
}
