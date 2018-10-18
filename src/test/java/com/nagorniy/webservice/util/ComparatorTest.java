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
public class ComparatorTest {

    private String firstString;
    private String secondString;
    private String expectedResult;


    public ComparatorTest(String firstString, String secondString, String expectedResult) {
        this.firstString = firstString;
        this.secondString = secondString;
        this.expectedResult = expectedResult;
    }

    @Parameters
    public static Collection stringCollection() {

        return Arrays.asList(new Object[][]{
                {"short", "longest", "short"},
                {"longest", "short", "short"},
                {"longest", "biggest", "biggest"},
                {"a", "a", "a"},
                {"", "a", ""}
        });
    }

    @Test
    public void compareTwoStrings() throws Exception {

        int compareResult = StringUtils.STRINGS_BY_LENGTH_AND_LEXICOGRAPHY_COMPARATOR
                .compare(firstString, secondString);

        String actualResult = (compareResult >= 0) ? firstString : secondString;
        assertThat(actualResult, is(expectedResult));
    }
}