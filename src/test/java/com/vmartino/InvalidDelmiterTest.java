package com.vmartino;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;

class InvalidDelmiterTest {

    private String input;
    private String delimiter;

    @Test
    void shouldReturnEmptywhenNoneIsInvalid() {
        this.input = "1,2";
        this.delimiter = ",";

        assertThat(getInvalidDelimiters(), empty());
    }

    private List<String> getInvalidDelimiters() {
        return NumbersValidator.invalidDelimiter(inputDataQuery())
                .collect(Collectors.toList());
    }

    @Test
    void shouldReturnOnewhenExistOnInvalidSingleCharDelimiter() {
        this.input = "1,2";
        this.delimiter = ";";

        List<String> invalidDelimiter = getInvalidDelimiters();
        assertThat(invalidDelimiter.size(), is(1));
        assertThat(invalidDelimiter.get(0), is(","));
    }

    @Test
    void shouldReturnTwoWhenExistTwoInvalidSingleCharDelimiter() {
        this.input = "1,2|3";
        this.delimiter = ";";

        List<String> invalidDelimiter = getInvalidDelimiters();
        assertThat(invalidDelimiter.size(), is(2));
        assertThat(invalidDelimiter.get(0), is(","));
        assertThat(invalidDelimiter.get(1), is("|"));
    }

    @Test
    void shouldReturnTwoWhenExistTwoInvalidStringDelimiter() {
        this.input = "1sep2||-3se4p5";
        this.delimiter = "sep";

        List<String> invalidDelimiter = getInvalidDelimiters();
        assertThat(invalidDelimiter.size(), is(3));
        assertThat(invalidDelimiter.get(0), is("||"));
        assertThat(invalidDelimiter.get(1), is("se"));
        assertThat(invalidDelimiter.get(2), is("p"));
    }

    private InputDataQuery inputDataQuery() {
        InputDataQuery inputDataQuery = new InputDataQuery();
        inputDataQuery.setData(input, delimiter);
        return inputDataQuery;
    }
}
