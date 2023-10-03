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

    private String delimiter;
    private String normalizedInput;
    private List<String> errorMessages;
    private List<String> negativeNumbers;

    public NumbersValidator() {
        this.errorMessages = new ArrayList<>();
    }
    public void checkInvalidDelimiter(String delimiter, String normalizedInput) {
        this.delimiter = delimiter;
        this.normalizedInput = normalizedInput;

        invalidDelimiter(normalizedInput, delimiter).stream()
                .findFirst()
                .ifPresent(this::addErrorsInvalidDelimiterMessage);
    }
    public void checkNegativeNumber(String[] numberList) {
        this.negativeNumbers = filterNegativeNumbers(numberList);

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

    private List<String> filterNegativeNumbers(String[] numberList) {
        return Arrays.stream(numberList)
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
                        delimiter,
                        invalidDelimiter,
                        getPosition(invalidDelimiter)));
    }

    private int getPosition(String delimiter) {
        return normalizedInput.indexOf(delimiter);
    }

    public static List<String> invalidDelimiter(String input, String delimiter) {

        String[] itemByDelmiter = NumbersParser.getNumbers(input, delimiter);

        return Arrays.stream(itemByDelmiter)
                .flatMap(s -> Arrays.stream(s.split("-?\\d+")))
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

    }
}
