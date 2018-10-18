package com.nagorniy.webservice.service;

import com.nagorniy.webservice.config.property.ProcessingProperties;
import com.nagorniy.webservice.model.Entity;
import com.nagorniy.webservice.model.InputDataModel;
import com.nagorniy.webservice.model.ResultModel;
import com.nagorniy.webservice.util.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Implementation of {@code StringsSortService} to sort strings
 * by the longest word in the string and feeds top {@code maxResultsAmount} results in response
 */
@Service
public class StringsSortServiceImpl implements StringsSortService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringsSortServiceImpl.class);

    private final ProcessingProperties processingProperties;

    public StringsSortServiceImpl(ProcessingProperties processingProperties) {
        this.processingProperties = processingProperties;
    }

    /**
     * Method to sort strings by the longest word in the string and feeds top results.
     * Top results are limited by {@code maxResultsAmount}.
     * {@code null} strings are ignored.
     *
     * @param inputData
     * @return {@code ResultModel} with top sorted strings
     */
    @Override
    public ResultModel doService(InputDataModel inputData) {

        LOGGER.trace("Start request processing");
        List<String> strings = inputData.getStrings();

        List<Entity> resultList = strings
                .stream()
                .filter(Objects::nonNull)
                .map(str -> Pair.of(StringUtils.getLongestWord(str), str))
                .sorted((o1, o2) -> StringUtils.STRINGS_BY_LENGTH_AND_LEXICOGRAPHY_COMPARATOR
                        .compare(o1.getKey(), o2.getKey()))
                .limit(processingProperties.getMaxResultsAmount())
                .map(pair -> new Entity(pair.getValue(), pair.getKey().length()))
                .collect(Collectors.toList());
        LOGGER.info("Processed request with {} strings", strings.size());
        return new ResultModel(resultList);
    }
}
