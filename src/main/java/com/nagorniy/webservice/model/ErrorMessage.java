package com.nagorniy.webservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Model for error message response
 */
@Data
@AllArgsConstructor
public class ErrorMessage {

    private String message;
}
