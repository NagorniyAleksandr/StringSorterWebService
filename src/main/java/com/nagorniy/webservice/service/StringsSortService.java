package com.nagorniy.webservice.service;

import com.nagorniy.webservice.model.InputDataModel;
import com.nagorniy.webservice.model.ResultModel;

/**
 * General interface for service layer.
 */
public interface StringsSortService {

    ResultModel doService(InputDataModel inputData);
}
