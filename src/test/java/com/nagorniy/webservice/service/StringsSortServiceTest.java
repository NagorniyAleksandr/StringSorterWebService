package com.nagorniy.webservice.service;

import com.nagorniy.webservice.config.property.ProcessingProperties;
import com.nagorniy.webservice.model.Entity;
import com.nagorniy.webservice.model.InputDataModel;
import com.nagorniy.webservice.model.ResultModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class StringsSortServiceTest {

    private final static int MAX_RESULT_AMOUNT = 5;

    private StringsSortService service;

    private InputDataModel inputData;
    private ResultModel expectedResult;

    public StringsSortServiceTest(InputDataModel inputData, ResultModel expectedResult) {
        this.inputData = inputData;
        this.expectedResult = expectedResult;
    }

    @Parameters(name = "combination {index}")
    public static Collection stringCollection() {

        // test strings
        String stringMaxLength1 = "s t r i n g";
        String stringMaxLength2 = "s t r i ng";
        String stringMaxLength3 = "str in g";
        String stringMaxLength4 = "a stri ng";
        String stringMaxLength5 = "a strin g";
        String stringMaxLength6 = "middle str";
        String stringMaxLength7 = "the longest str";


        InputDataModel inputData1 = new InputDataModel(
                Arrays.asList(
                        stringMaxLength2,
                        stringMaxLength3,
                        stringMaxLength1,
                        stringMaxLength4,
                        stringMaxLength6,
                        stringMaxLength5,
                        stringMaxLength7));
        ResultModel result1 = new ResultModel(Arrays.asList(
                new Entity(stringMaxLength7, 7),
                new Entity(stringMaxLength6, 6),
                new Entity(stringMaxLength5, 5),
                new Entity(stringMaxLength4, 4),
                new Entity(stringMaxLength3, 3)));

        InputDataModel inputData2 = new InputDataModel(
                Arrays.asList(
                        stringMaxLength4,
                        stringMaxLength2,
                        stringMaxLength6,
                        stringMaxLength1));
        ResultModel result2 = new ResultModel(Arrays.asList(
                new Entity(stringMaxLength6, 6),
                new Entity(stringMaxLength4, 4),
                new Entity(stringMaxLength2, 2),
                new Entity(stringMaxLength1, 1)));


        return Arrays.asList(new Object[][]{
                {inputData1, result1},
                {inputData2, result2}
        });
    }

    @Before
    public void setUp() throws Exception {
        ProcessingProperties processingProperties = new ProcessingProperties();
        processingProperties.setMaxResultsAmount(MAX_RESULT_AMOUNT);
        service = new StringsSortServiceImpl(processingProperties);
    }

    @Test
    public void doService() throws Exception {

        ResultModel actualResult = service.doService(inputData);

        assertThat("Result length must be less than " + MAX_RESULT_AMOUNT,
                actualResult.getResult().size(), is(lessThanOrEqualTo(MAX_RESULT_AMOUNT)));

        assertThat("Actual result should be equal to expected result",
                actualResult, is(expectedResult));
    }
}