package com.vmartino;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

class StringCalculatorTest {

    private StringCalculator calculator;

    @BeforeEach
    public void setUp() {
        CalculatorRequest calculatorRequest = new CalculatorRequest();
        ErrorMessages errorMessages = new ErrorMessages(calculatorRequest);
        NumbersValidator validator = new NumbersValidator(errorMessages);
        Adder adder = new Adder();
        NumbersParser parser = new NumbersParser(validator, calculatorRequest);
        calculator = new StringCalculator(adder, parser, validator);
    }

    @Test
    void testShouldReturnErrorWhenDelimiterIsInvalid() {
        assertEquals("'|' expected but ',' found at position 3", calculator.add("//|\n1|2,3"));
    }

    @Test
    void shouldReturnMessageNumberIsNotAllowed() {
        assertThat(calculator.add("1,-2"), is("Negative number(s) not allowed: -2"));
    }

    @Test
    void shouldReturnMessageaNumberIsNotAllowedAndInvalidDelimiter() {
        assertThat(calculator.add("//|\n1|2,-3"),
                is("Negative number(s) not allowed: -3\n'|' expected but ',' found at position 3"));
    }
}