package com.vmartino;

public class DelimiterParser {

    private String normalizedInput;
    private String delimiter =",";

    public CalculatorParams parse(String input) {        
        if (input.startsWith("//")) {
            delimiter = input.substring(2, input.indexOf("\n"));
            input = input.substring(input.indexOf("\n") + 1);
        }
        normalizedInput = input.replace("\n", delimiter);

        return new CalculatorParams() {
            public String delimiter() {
                return delimiter;
            }

            public String normalizedInput() {
                return normalizedInput;
            }
        };
    }
}
