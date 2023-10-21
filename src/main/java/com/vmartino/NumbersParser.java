package com.vmartino;

public class NumbersParser {
    public CalculatorRequest getRequest(String input) {
        String delimiter = ",";
        if (existDelimiterConfiguration(input)) {
            delimiter = getDelimiterFromConfiguration(input);
            input = extractInput(input);
        }
        String normalizedInput = input.replace("\n", delimiter);
        
        return new CalculatorRequest(normalizedInput, delimiter);
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
