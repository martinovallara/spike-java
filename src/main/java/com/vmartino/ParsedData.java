package com.vmartino;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
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

    private boolean isEmpty() {
        return getNormalizedInput().isEmpty();
    }

    public Stream<String> stringOfNumbers() {
        String escape = getDelimiter().length() == 1 ? "\\" : "";
        return Arrays.stream(getNormalizedInput().split(escape + getDelimiter()));
    }

    public Stream<Integer> numbers() {
        if (isEmpty())
            return Stream.of(0);

        return stringOfNumbers().mapToInt(Integer::parseInt).boxed();
    }

    public int getPosition(String text) {
        return normalizedInput.indexOf(text);
    }
    
    public boolean existNegativeNumbers() {
        return !filterNegativeNumbers().isEmpty();
    }

    public String getNegativeNumbersMessage() {
        return filterNegativeNumbers().stream().collect(Collectors.joining(", "));
    }
    private List<String> filterNegativeNumbers() {
        return stringOfNumbers()
                .flatMap((Function<String, Stream<String>>) inputString -> Pattern.compile("-\\d+")
                        .matcher(inputString)
                        .results()
                        .map(MatchResult::group))
                .collect(Collectors.toList());
    }
}
