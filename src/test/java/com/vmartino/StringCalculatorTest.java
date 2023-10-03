package com.vmartino;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class StringCalculatorTest {

    @Test
    public void testShouldReturnErrorWhenDelimiterIsInvalid() {
        StringCalculator calculator = new StringCalculator();
        assertEquals("'|' expected but ',' found at position 3", calculator.add("//|\n1|2,3"));
    }

    @Test
    public void shouldReturnMessageNumberIsNotAllowed() {
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.add("1,-2"), is("Negative number(s) not allowed: -2"));
    }

    @Test
    public void shouldReturnMessageaNumberIsNotAllowedAndInvalidDelimiter() {
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.add("//|\n1|2,-3"), is("Negative number(s) not allowed: -3\n'|' expected but ',' found at position 3"));
    } 
}