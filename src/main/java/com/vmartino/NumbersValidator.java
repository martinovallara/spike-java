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

    public NumbersValidator() {
        this.errorMessages = new ArrayList<>();
    }

    public void checkNegativeNumber(String[] numberList) {
        List<String> negativeNumbers = filterNegativeNumbers(numberList);

        if (!negativeNumbers.isEmpty()) {
            errorMessages.add(0,"Negative number(s) not allowed: " + getNegativeNumbers(negativeNumbers));
        }
    }

    public String getValidationMessage() {
        return errorMessages.stream()
                .collect(Collectors.joining("\n"));
    }

    public boolean anyErrors() {
        return !errorMessages.isEmpty();
    }

    private static String getNegativeNumbers(List<String> negativeNumbers) {
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
        this.errorMessages.add(0,
                String.format("'%s' expected but '%s' found at position %d",
                        delimiter,
                        invalidDelimiter,
                        getPosition(invalidDelimiter)));
    }

    private int getPosition(String delimiter) {
        return normalizedInput.indexOf(delimiter);
    }
}
