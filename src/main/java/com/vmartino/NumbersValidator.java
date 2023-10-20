package com.vmartino;

public class NumbersValidator {
    private ErrorMessages errorMessages;
    private CalculatorRequest calculatorRequest;

    public NumbersValidator( ErrorMessages errorMessages) {
        this.errorMessages = errorMessages;
    }

    public void setCalculatorRequest(CalculatorRequest calculatorRequest) {
        this.calculatorRequest = calculatorRequest;
    }

    public void checkInvalidDelimiter() {
        calculatorRequest.invalidDelimiter()
            .findFirst()
            .ifPresent(this::addErrorsInvalidDelimiterMessage);
    }

    public void checkNegativeNumber() {
        if (calculatorRequest.existNegativeNumbers()) {
            errorMessages.addErrorsNegativeNumber(calculatorRequest.getNegativeNumbersMessage());
        }
    }

    public String getValidationMessage() {
        return errorMessages.getValidationMessage();
    }

    public boolean anyErrors() {
        return errorMessages.anyErrors();
    }

    private void addErrorsInvalidDelimiterMessage(String itemWithInvalidDelimiter) {
        errorMessages.addErrorsInvalidDelimiterMessage(itemWithInvalidDelimiter);
    }
}
