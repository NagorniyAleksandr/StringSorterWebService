package com.nagorniy.webservice.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class StringUtilsTest {

    private String inputString;
    private String expectedLongestWord;


    public StringUtilsTest(String inputString, String expectedLongestWord) {
        this.inputString = inputString;
        this.expectedLongestWord = expectedLongestWord;
    }

    @Parameters
    public static Collection stringCollection() {

        return Arrays.asList(new Object[][]{
                {"one two three", "three"},
                {"one three two", "three"},
                {"three two one", "three"},
                {"one\ttwo \nthree", "three"},
                {"one\nthree\ttwo", "three"},
                {"three\ntwo\none", "three"},
                {"some very long string", "string"},
                {"alone", "alone"},
                {"a", "a"},
                {"123 456 7890", "7890"},
                {"", ""}
        });
    }

    @Test
    public void getLongestWordTest() throws Exception {

        String actualLongestWord = StringUtils.getLongestWord(inputString);
        assertThat("The longest string is incorrect",
                actualLongestWord, is(expectedLongestWord));
    }
}