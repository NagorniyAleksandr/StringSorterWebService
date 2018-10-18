package com.nagorniy.webservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Model for result object
 */
@Data
@AllArgsConstructor
public class Entity {

    private String string;
    private int longestWord;
}
