package com.vmartino;

public class RequestValidator {
    private ErrorMessages errorMessages;
    private CalculatorRequest calculatorRequest;

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

    public void validate(CalculatorRequest calculatorRequest) {
        this.calculatorRequest = calculatorRequest;
        this.errorMessages = new ErrorMessages(calculatorRequest);

        checkInvalidDelimiter();
        checkNegativeNumber(); 
    }
}
