package com.nagorniy.webservice.util.validator;

import com.nagorniy.webservice.model.InputDataModel;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * Implementation of {@code InputDataValidator} to check if the strings list is null or empty
 */
@Component
public class NotNullOrEmptyStringsArrayValidator implements InputDataValidator {

    /**
     * Check if strings list is null or empty
     *
     * @param inputDataModel data to validate
     * @return an empty string if validation passed {@code ""} or an error message
     */
    public String validate(InputDataModel inputDataModel) {
        return CollectionUtils.isEmpty(inputDataModel.getStrings()) ?
                "'strings' array must be not empty" :
                "";
    }
}
