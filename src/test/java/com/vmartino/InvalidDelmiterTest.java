package com.vmartino;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;

public class InvalidDelmiterTest {
    
    @Test
    public void shouldReturnEmptywhenNoneIsInvalid() {
        String input = "1,2";
        String delimiter = ",";

        assertThat(NumbersValidator.invalidDelimiter(input, delimiter),  empty());
    }
    
    @Test
    public void shouldReturnOnewhenExistOnInvalidSingleCharDelimiter() {
        String input = "1,2";
        String delimiter = ";";

        List<String> invalidDelimiter = NumbersValidator.invalidDelimiter(input, delimiter);
        assertThat(invalidDelimiter.size(), is(1));
        assertThat(invalidDelimiter.get(0), is(","));
    }

    @Test
    public void shouldReturnTwoWhenExistTwoInvalidSingleCharDelimiter() {
        String input = "1,2|3";
        String delimiter = ";";

        List<String> invalidDelimiter = NumbersValidator.invalidDelimiter(input, delimiter);
        assertThat(invalidDelimiter.size(), is(2));
        assertThat(invalidDelimiter.get(0), is(","));
        assertThat(invalidDelimiter.get(1), is("|"));
    }

    @Test
    public void shouldReturnTwoWhenExistTwoInvalidStringDelimiter() {
        String input = "1sep2||-3se4p5";
        String delimiter = "sep";

        List<String> invalidDelimiter = NumbersValidator.invalidDelimiter(input, delimiter);
        assertThat(invalidDelimiter.size(), is(3));
        assertThat(invalidDelimiter.get(0), is("||"));
        assertThat(invalidDelimiter.get(1), is("se"));
        assertThat(invalidDelimiter.get(2), is("p"));
    }
}
