package com.vmartino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NumbersValidator {
    private List<String> errorMessages;
    private List<String> negativeNumbers;
    private ParsedData data;
    
    public NumbersValidator() {
        this.errorMessages = new ArrayList<>();
    }
    public void setData(ParsedData parsedData) {
        this.data = parsedData;
    }

    public void checkInvalidDelimiter() {
        invalidDelimiter(data).stream()
                .findFirst()
                .ifPresent(this::addErrorsInvalidDelimiterMessage);
    }

    public void checkNegativeNumber() {
        this.negativeNumbers = filterNegativeNumbers();

        if (!negativeNumbers.isEmpty()) {
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
        errorMessages.add(0, "Negative number(s) not allowed: " + getNegativeNumbersMessage());
    }

    private String getNegativeNumbersMessage() {
        return negativeNumbers.stream().collect(Collectors.joining(", "));
    }

    private List<String> filterNegativeNumbers() {
        return data.stringOfNumbers()
                .flatMap((Function<String, Stream<String>>) inputString -> Pattern.compile("-\\d+")
                        .matcher(inputString)
                        .results()
                        .map(MatchResult::group))
                .collect(Collectors.toList());
    }


    private void addErrorsInvalidDelimiterMessage(String itemWithInvalidDelimiter) {

        String invalidDelimiter = itemWithInvalidDelimiter.replaceAll("-?\\d", "");
        this.errorMessages.add(0,
                String.format("'%s' expected but '%s' found at position %d",
                        data.getDelimiter(),
                        invalidDelimiter,
                        data.getPosition(invalidDelimiter)));
    }

    public static List<String> invalidDelimiter(ParsedData data) {
        return data.stringOfNumbers()
                .flatMap(s -> Arrays.stream(s.split("-?\\d+")))
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
}
