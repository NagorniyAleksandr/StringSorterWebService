package com.nagorniy.webservice.service;

import com.nagorniy.webservice.config.property.ProcessingProperties;
import com.nagorniy.webservice.model.Entity;
import com.nagorniy.webservice.model.InputDataModel;
import com.nagorniy.webservice.model.ResultModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

@RunWith(JUnit4.class)
public class StringsSortServiceEdgeCaseTest {
    private final static int MAX_RESULT_AMOUNT = 5;

    private StringsSortService service;

    @Before
    public void setUp() throws Exception {
        ProcessingProperties processingProperties = new ProcessingProperties();
        processingProperties.setMaxResultsAmount(MAX_RESULT_AMOUNT);
        service = new StringsSortServiceImpl(processingProperties);
    }

    @Test
    public void nullInStrings() throws Exception {

        String el1 = "first el";
        String el2 = "second el";
        InputDataModel inputData = new InputDataModel(Arrays.asList(el1, null, el2));

        ResultModel actualResult = service.doService(inputData);

        int expectedResultsAmount = 2;
        assertThat("Result length must be " + expectedResultsAmount,
                actualResult.getResult().size(), is(expectedResultsAmount));

        Entity firstEntity = new Entity(el2, 6);
        Entity secondEntity = new Entity(el1, 5);

        assertThat("result list must contains elements in right order",
                actualResult.getResult(), contains(firstEntity, secondEntity));
    }

    @Test
    public void zeroStringLength() throws Exception {

        String emptyString = "";
        InputDataModel inputData = new InputDataModel(Arrays.asList(emptyString, emptyString));

        ResultModel actualResult = service.doService(inputData);

        int expectedResultsAmount = 2;
        assertThat("Result length must be " + expectedResultsAmount,
                actualResult.getResult().size(), is(expectedResultsAmount));

        Entity emptyStringEntity = new Entity(emptyString, 0);

        assertThat("result list must contains elements in right order",
                actualResult.getResult(), contains(emptyStringEntity, emptyStringEntity));
    }

    @Test
    public void sameLengthWordsStrings() throws Exception {

        String el1 = "first sentence";
        String el2 = "nice developer";
        String el3 = "second building";
        String el4 = "need to discover new world";
        InputDataModel inputData = new InputDataModel(Arrays.asList(el1, el2, el3, el4));

        Entity entity1 = new Entity(el2, 9);
        Entity entity2 = new Entity(el1, 8);
        Entity entity3 = new Entity(el4, 8);
        Entity entity4 = new Entity(el3, 8);
        List<Entity> resultEntities = Arrays.asList(entity1, entity2, entity3, entity4);
        ResultModel expectedResult = new ResultModel(resultEntities);

        ResultModel actualResult = service.doService(inputData);

        int expectedResultsAmount = 4;
        assertThat("Result length must be " + expectedResultsAmount,
                actualResult.getResult().size(), is(expectedResultsAmount));

        assertThat("Actual result must contains all elements from expected list in right order",
                actualResult.getResult(), contains(resultEntities.toArray()));

        assertThat("Actual ResultModel must be equal to expected ResultModel",
                actualResult, is(expectedResult));
    }
}