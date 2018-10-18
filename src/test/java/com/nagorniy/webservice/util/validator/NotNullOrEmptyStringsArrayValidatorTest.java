package com.nagorniy.webservice.util.validator;

import com.nagorniy.webservice.model.InputDataModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(JUnit4.class)
public class NotNullOrEmptyStringsArrayValidatorTest {

    private NotNullOrEmptyStringsArrayValidator validator = new NotNullOrEmptyStringsArrayValidator();

    @Test
    public void validateNull() throws Exception {
        doTest(null, false);
    }

    @Test
    public void validateEmptyList() throws Exception {
        doTest(Collections.emptyList(), false);
    }

    @Test
    public void validateOneElementList() throws Exception {
        doTest(Collections.singletonList("a"), true);
    }

    @Test
    public void validateMultiplyElementsList() throws Exception {
        List<String> testStringsList = Arrays.asList("not empty string", "second string");
        doTest(testStringsList, true);
    }

    private void doTest(List<String> testStringsList, boolean isInputValid) {
        String validationMessage = validator.validate(new InputDataModel(testStringsList));
        assertThat("Validation message should not be null", validationMessage, is(notNullValue()));

        boolean actualIsInputValid = validationMessage.isEmpty();
        String assertMessage = String.format("String %s must be valid: %b", testStringsList, isInputValid);
        assertThat(assertMessage, actualIsInputValid, is(isInputValid));
    }
}