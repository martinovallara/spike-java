package com.vmartino;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class StringCalculatorParametrizedTest {

    @ParameterizedTest
    @MethodSource("inputAndExpectedValuesProvider")
    void shouldReturnExpectedResult(String input, String expected) {
        StringCalculator calculator = BuildStringCalculator();
        assertThat(calculator.add(input), is(expected));
    }

    private StringCalculator BuildStringCalculator() {
        RequestValidator validator = new RequestValidator();
        Adder adder = new Adder();
        NumbersParser parser = new NumbersParser();
        return new StringCalculator(adder, parser, validator);
    }

    public static Collection<Object[]> inputAndExpectedValuesProvider() {
        return Arrays.asList(new Object[][] {
                { "", "0" }, // Stringa vuota
                { "1", "1" }, // Stringa contenente un numero
                { "1,2", "3" }, // Stringa contenente due numeri separati da virgola
                { "1,2,30", "33" }, // Stringa contenente tre numeri separati da virgola
                { "1,2\n3", "6" }, // Stringa contenente tre numeri separati da virgola con newline
                { "//;\n1;3", "4" }, // String with delimiter configuration
                { "//sep\n1sep2sep3", "6" }, // String with long delimiter configuration
                {"2,1000", "1002" }, // Stringa contenente il numero max sommabile
                {"1,1001", "1" }, // Stringa contenente il numero min non sommabile 
        });
    }
}