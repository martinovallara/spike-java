package com.vmartino;

import java.util.Arrays;
import java.util.stream.Stream;

public class ParsedData {

    private String normalizedInput;
    private String delimiter;

    public ParsedData(String normalizedInput, String delimiter) {
        this.setNormalizedInput(normalizedInput);
        this.setDelimiter(delimiter);
    }

    public String getNormalizedInput() {
        return normalizedInput;
    }

    public void setNormalizedInput(String normalizedInput) {
        this.normalizedInput = normalizedInput;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public boolean isEmpty() {
        return getNormalizedInput().isEmpty();
    }

    public Stream<String> stringOfNumbers() {
        String escape = getDelimiter().length() == 1 ? "\\" : "";
        return Arrays.stream(getNormalizedInput().split(escape + getDelimiter()));
    }

    public Stream<Integer> numbers() {
        return stringOfNumbers().mapToInt(Integer::parseInt).boxed();
    }

    public int getPosition(String text) {
        return normalizedInput.indexOf(text);
    }
}
