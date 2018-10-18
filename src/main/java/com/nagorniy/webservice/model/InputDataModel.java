package com.nagorniy.webservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Model for received input object
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputDataModel {

    private List<String> strings;
}
