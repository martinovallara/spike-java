package com.vmartino;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
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
                { "1,2,30", 33 }, // Stringa contenente tre numeri separati da virgola
                { "1,2\n3", 6 }, // Stringa contenente tre numeri separati da virgola con newline
                { "//;\n1;3", 4 }, // String with delimiter configuration
                { "//sep\n1sep2sep3", 6 }, // String with long delimiter configuration
        });
    }

    @Test
    public void shouldReturnErrorWhenDelimiterIsInvalid() {
        StringCalculator calculator = new StringCalculator();
        Exception exception = assertThrows(Exception.class, () -> calculator.add("//|\n1|2,3"));
        assertEquals("'|' expected but ',' found at position 3", exception.getMessage());
    }

    @Test
    public void shouldReturnMessageNumberIsNotAllowed() {
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.add("1,-2"), is("Negative number(s) not allowed: -2"));
    }    
}