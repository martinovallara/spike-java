package com.vmartino;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Adder {

    private Adder() {
    }

    public static String getSum(Stream<Integer> numbers) {
        List<Integer> numberList = toList(numbers);

        List<String> negativeNumbers = filterNegativeNumbers(numberList);

        if (!negativeNumbers.isEmpty()) {
            return "Negative number(s) not allowed: " + getNegativeNumbers(negativeNumbers);
        }
  
        return aStream(numberList).reduce(0, Integer::sum).toString();
    }

    private static String getNegativeNumbers(List<String> negativeNumbers) {
        return negativeNumbers.stream().collect(Collectors.joining(", "));
    }
    
    private static List<String> filterNegativeNumbers(List<Integer> numberList) {
        return aStream(numberList)
        .filter(n -> n < 0)
        .map(Object::toString)
        .collect(Collectors.toList());
    }
    
    private static List<Integer> toList(Stream<Integer> numbers) {
        return numbers.collect(Collectors.toList());
    }
    private static Stream<Integer> aStream(List<Integer> numbers) {
        return numbers.stream();     
    }
}
