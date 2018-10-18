package com.nagorniy.webservice.util.validator;

import com.nagorniy.webservice.model.InputDataModel;

/**
 * Implement this interface to add addition logic for request body preprocessing
 * related to the business requirements.
 * For example, check the maximum string length or min/max amount of strings to compare, etc.
 */
public interface InputDataValidator {

    /**
     * Method to validate {@code InputDataModel}
     *
     * @param inputDataModel data to validate
     * @return validation message or an empty string {@code ""} in case or passed validation
     */
    String validate(InputDataModel inputDataModel);
}
