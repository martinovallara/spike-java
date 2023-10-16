package com.vmartino;

import java.util.Arrays;
import java.util.stream.Stream;

public class NumbersValidator {
    private ErrorMessages errorMessages;
    private ParsedData data;
    
    public void setData(ParsedData parsedData) {
        this.data = parsedData;
        this.errorMessages = new ErrorMessages(parsedData);
    }

    public void checkInvalidDelimiter() {
        invalidDelimiter(data)
                .findFirst()
                .ifPresent(this::addErrorsInvalidDelimiterMessage);
    }

    public void checkNegativeNumber() {
        if (data.existNegativeNumbers()) {
            errorMessages.addErrorsNegativeNumber(data.getNegativeNumbersMessage());
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

    public static Stream<String> invalidDelimiter(ParsedData data) {
        return data.stringOfNumbers()
                .flatMap(s -> Arrays.stream(s.split("-?\\d+")))
                .filter(s -> !s.isEmpty());
    }
}
