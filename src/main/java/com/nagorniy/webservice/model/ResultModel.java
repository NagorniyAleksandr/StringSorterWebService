package com.nagorniy.webservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Model for result object
 */
@Data
@AllArgsConstructor
public class ResultModel {

    private List<Entity> result;
}
