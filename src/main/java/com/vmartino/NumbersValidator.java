package com.vmartino;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class NumbersValidator {

    private String negativeNumberNotAllowedMessage;
    private String delimiter;
    private String normalizedInput;
    private String invalidDelimiterMessage;

    public NumbersValidator() {
        this.negativeNumberNotAllowedMessage = "";
        this.invalidDelimiterMessage = "";
    }

    public void checkNegativeNumber(List<String> negativeNumbers) {
        if (!negativeNumbers.isEmpty()) {
            this.negativeNumberNotAllowedMessage = "Negative number(s) not allowed: "
                    + getNegativeNumbers(negativeNumbers);
        }
    }

    public String getValidationMessage() {
        List<String> errorMessages = Arrays.asList(negativeNumberNotAllowedMessage, invalidDelimiterMessage);
        return errorMessages.stream()
        .filter(s -> !s.isEmpty())
        .collect(Collectors.joining("\n"));
    }
    public boolean anyErrors() {
        return !negativeNumberNotAllowedMessage.isEmpty() || !invalidDelimiterMessage.isEmpty();
    }
    
    private static String getNegativeNumbers(List<String> negativeNumbers) {
        return negativeNumbers.stream().collect(Collectors.joining(", "));
    }


    public List<String> filterNegativeNumbers(String[] numberList) {
        List<String> numbers = Arrays.stream(numberList)
                .flatMap(inputString -> Pattern.compile("-\\d+")
                        .matcher(inputString)
                        .results()
                        .map(matchResult -> matchResult.group()))
                .collect(Collectors.toList());

        return numbers;
    }

    public void checkInvalidDelimiter(String[] numbers, String delimiter, String normalizedInput) {
        this.delimiter = delimiter;
        this.normalizedInput = normalizedInput;
        Arrays.stream(numbers)
                .filter(this::noNumbers)
                .findFirst()
                .ifPresent(this::throwInvalidDelimiterException);
    }

    private boolean noNumbers(String n) {
        return !n.matches("-?\\d+");
    }

    private void throwInvalidDelimiterException(String itemWithInvalidDelimiter) {

        String invalidDelimiter = itemWithInvalidDelimiter.replaceAll("-?\\d", "");
        this.invalidDelimiterMessage = String.format("'%s' expected but '%s' found at position %d",
                delimiter,
                invalidDelimiter,
                getPosition(invalidDelimiter));
        // throw new IllegalArgumentException(invalidDelimiterMessage);
    }

    private int getPosition(String delimiter) {
        return normalizedInput.indexOf(delimiter);
    }
}
