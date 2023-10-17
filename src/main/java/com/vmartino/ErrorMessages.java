package com.vmartino;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ErrorMessages {

    private List<String> messages;
    private ErrorsQuery errorsQuery;


    public ErrorMessages(ErrorsQuery errorsQuery) {
        this.messages = new ArrayList<>();
        this.errorsQuery = errorsQuery;
    }

    public void addErrorsNegativeNumber(String negativeNumbers) {
        messages.add(0, "Negative number(s) not allowed: " + negativeNumbers);
    }

    public boolean anyErrors() {
        return !messages.isEmpty();
    }

    public void addErrorsInvalidDelimiterMessage(String itemWithInvalidDelimiter) {
        String invalidDelimiter = itemWithInvalidDelimiter.replaceAll("-?\\d", "");
        this.messages.add(0,
                String.format("'%s' expected but '%s' found at position %d",
                        this.errorsQuery.getDelimiter(),
                        invalidDelimiter,
                        this.errorsQuery.getPosition(invalidDelimiter)));
    }

    public String getValidationMessage() {
                return messages.stream()
                .collect(Collectors.joining("\n"));
    }
}
