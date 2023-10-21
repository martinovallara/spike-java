package com.vmartino;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CalculatorRequest implements ErrorsQuery {

    private String normalizedInput;
    private String delimiter;

    public CalculatorRequest(String normalizedInput, String delimiter) {
        this.normalizedInput = normalizedInput;
        this.delimiter = delimiter;
    }

    public Stream<Integer> numbers() {
        if (isEmpty())
            return Stream.of(0);
    
        return stringOfNumbers().mapToInt(Integer::parseInt).boxed();
    }
     
    
    public Stream<String> invalidDelimiter() {
        return stringOfNumbers()
        .flatMap(s -> Arrays.stream(s.split("-?\\d+")))
        .filter(s -> !s.isEmpty());
    }
    public boolean existNegativeNumbers() {
        return !filterNegativeNumbers().isEmpty();
    }
    
    public Stream<String> getNegativeNumbersMessage() {
        return filterNegativeNumbers().stream();
    }
    
    @Override
    public String getDelimiter() {
        return delimiter;
    }
    
    @Override
    public int getPosition(String text) {
        return normalizedInput.indexOf(text);
    }
    
    public String getNormalizedInput() {
        return normalizedInput;
    }
    
    private boolean isEmpty() {
        return getNormalizedInput().isEmpty();
    }
    private List<String> filterNegativeNumbers() {
        return stringOfNumbers()
        .flatMap((Function<String, Stream<String>>) inputString -> Pattern.compile("-\\d+")
        .matcher(inputString)
        .results()
        .map(MatchResult::group))
        .collect(Collectors.toList());
    }
    
    private Stream<String> stringOfNumbers() {
        String escape = getDelimiter().length() == 1 ? "\\" : "";
        return Arrays.stream(getNormalizedInput().split(escape + getDelimiter()));
    }
}
