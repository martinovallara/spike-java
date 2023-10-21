package com.vmartino;

public class StringCalculator {

    private NumbersParser parser;
    private Adder adder;
    private RequestValidator validator;

    public StringCalculator(Adder adder, NumbersParser parser, RequestValidator validator) {
        this.parser = parser;
        this.adder = adder;
        this.validator = validator;
    }

    public String add(String input) {
        CalculatorRequest calculatorRequest = parser.getRequest(input);

        validator.validate(calculatorRequest);

        if (validator.anyErrors())
            return validator.getValidationMessage();

        return adder.getSum(calculatorRequest.numbers()).toString();
    }
}
