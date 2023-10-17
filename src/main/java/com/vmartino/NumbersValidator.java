package com.vmartino;

import java.util.Arrays;
import java.util.stream.Stream;

public class NumbersValidator {
    private ErrorMessages errorMessages;
    private InputDataQuery data;

    public NumbersValidator(InputDataQuery data, ErrorMessages errorMessages) {
        this.data = data;
        this.errorMessages = errorMessages;
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

    public static Stream<String> invalidDelimiter(InputDataQuery data) {
        return data.stringOfNumbers()
                .flatMap(s -> Arrays.stream(s.split("-?\\d+")))
                .filter(s -> !s.isEmpty());
    }
}
