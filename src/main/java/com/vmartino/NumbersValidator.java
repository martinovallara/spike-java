package com.vmartino;

public class NumbersValidator {
    private ErrorMessages errorMessages;
    private InputDataQuery inputDataQuery;

    public NumbersValidator(InputDataQuery inputDataQuery, ErrorMessages errorMessages) {
        this.inputDataQuery = inputDataQuery;
        this.errorMessages = errorMessages;
    }

    public void checkInvalidDelimiter() {
        inputDataQuery.invalidDelimiter()
            .findFirst()
            .ifPresent(this::addErrorsInvalidDelimiterMessage);
    }

    public void checkNegativeNumber() {
        if (inputDataQuery.existNegativeNumbers()) {
            errorMessages.addErrorsNegativeNumber(inputDataQuery.getNegativeNumbersMessage());
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
