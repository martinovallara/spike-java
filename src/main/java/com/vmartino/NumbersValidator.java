package com.vmartino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NumbersValidator {
    private List<String> errorMessages;
    private ParsedData data;
    
    public NumbersValidator() {
        this.errorMessages = new ArrayList<>();
    }
    public void setData(ParsedData parsedData) {
        this.data = parsedData;
    }

    public void checkInvalidDelimiter() {
        invalidDelimiter(data)
                .findFirst()
                .ifPresent(this::addErrorsInvalidDelimiterMessage);
    }

    public void checkNegativeNumber() {
        if (data.existNegativeNumbers()) {
            addErrorsNegativeNumber();
        }
    }

    public String getValidationMessage() {
        return errorMessages.stream()
                .collect(Collectors.joining("\n"));
    }

    public boolean anyErrors() {
        return !errorMessages.isEmpty();
    }

    private void addErrorsNegativeNumber() {
        errorMessages.add(0, "Negative number(s) not allowed: " + data.getNegativeNumbersMessage());
    }

    private void addErrorsInvalidDelimiterMessage(String itemWithInvalidDelimiter) {

        String invalidDelimiter = itemWithInvalidDelimiter.replaceAll("-?\\d", "");
        this.errorMessages.add(0,
                String.format("'%s' expected but '%s' found at position %d",
                        data.getDelimiter(),
                        invalidDelimiter,
                        data.getPosition(invalidDelimiter)));
    }

    public static Stream<String> invalidDelimiter(ParsedData data) {
        return data.stringOfNumbers()
                .flatMap(s -> Arrays.stream(s.split("-?\\d+")))
                .filter(s -> !s.isEmpty());
    }
}
