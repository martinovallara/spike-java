package com.vmartino;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;


public class StringCalculatorTest 
{

    @Test
    public void shouldReturnZeroWhenStringEmpty()
    {
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.add(""), is(0));
    }

    @Test
    public void shouldReturnTheNumberWhenTheInputOnlyContainsOne()
    {
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.add("1"), is(1));
    }
}
