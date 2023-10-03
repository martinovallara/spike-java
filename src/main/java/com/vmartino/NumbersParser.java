package com.vmartino;


import java.util.stream.Stream;

public class NumbersParser {
    private NumbersValidator validator;

    public NumbersParser(NumbersValidator validator) {
        this.validator = validator;
    }

    public Stream<Integer> numbers(String input) {
        ParsedData parsedData = parse(input);

        validator.setData(parsedData);
        validator.checkInvalidDelimiter();
        validator.checkNegativeNumber();

        return parsedData.numbers();
    }

    public static String[] getNumbers(String normalizedInput, String delimiter) {
        String escape = delimiter.length() == 1 ? "\\" : "";
        return normalizedInput.split(escape + delimiter); // add \\ to escape the delimiter
    }

    private ParsedData parse(String input) {
        String delimiter = ",";
        if (input.startsWith("//")) {
            delimiter = input.substring(2, input.indexOf("\n"));
            input = input.substring(input.indexOf("\n") + 1);
        }
        String normalizedInput = input.replace("\n", delimiter);
        return new ParsedData(normalizedInput, delimiter);
    }
}
