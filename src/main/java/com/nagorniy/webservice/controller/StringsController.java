package com.nagorniy.webservice.controller;

import com.nagorniy.webservice.model.ErrorMessage;
import com.nagorniy.webservice.model.InputDataModel;
import com.nagorniy.webservice.model.ResultModel;
import com.nagorniy.webservice.service.StringsSortService;
import com.nagorniy.webservice.util.validator.InputDataValidator;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Rest controller to process incoming requests
 */
@RestController
public class StringsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringsController.class);

    private final StringsSortService service;
    private final List<InputDataValidator> inputDataValidators;

    public StringsController(StringsSortService service,
                             List<InputDataValidator> inputDataValidators) {
        this.service = service;
        this.inputDataValidators = inputDataValidators;
    }

    /**
     * Method to handle post requests to process array of strings.
     * Input data will be validated and redirected to the service layer.
     * {@code ApiResponses} annotation provides information for swagger description.
     *
     * @param inputData object with array of string to process
     * @return {@code ResponseEntity} with {@code ResultModel} or {@code ErrorMessage}
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ResultModel.class),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorMessage.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ErrorMessage.class)})
    @PostMapping(value = "/sort/strings", produces = "application/json", consumes = "application/json")
    public ResponseEntity compareStrings(@RequestBody InputDataModel inputData) {

        String validationMessage = inputDataValidators
                .stream()
                .map(inputDataValidator -> inputDataValidator.validate(inputData))
                .filter(message -> !StringUtils.isEmpty(message))
                .collect(Collectors.joining(". "));

        if (!StringUtils.isEmpty(validationMessage)) {
            LOGGER.info("Input data validation failed, cause: {}", validationMessage);
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorMessage(validationMessage));
        }

        ResultModel resultModel = service.doService(inputData);
        return ResponseEntity
                .ok()
                .body(resultModel);
    }
}
