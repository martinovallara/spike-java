package com.vmartino;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class StringCalculatorTest {

    @ParameterizedTest
    @MethodSource("inputAndExpectedValuesProvider")
    public void shouldReturnExpectedResult(String input, int expected) {
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.add(input), is(expected));
    }

    public static Collection<Object[]> inputAndExpectedValuesProvider() {
        return Arrays.asList(new Object[][] {
                { "", 0 }, // Stringa vuota
                { "1", 1 }, // Stringa contenente un numero
                { "1,2", 3 }, // Stringa contenente due numeri separati da virgola
                { "1,2,3", 6 }, // Stringa contenente tre numeri separati da virgola
                // Aggiungi altri casi di test qui
            });
    }
}