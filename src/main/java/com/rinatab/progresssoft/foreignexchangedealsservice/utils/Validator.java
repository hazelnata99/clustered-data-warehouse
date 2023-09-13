package com.rinatab.progresssoft.foreignexchangedealsservice.utils;

import com.rinatab.progresssoft.foreignexchangedealsservice.exception.BaseException;
import com.rinatab.progresssoft.foreignexchangedealsservice.exception.InvalidInputException;
import com.rinatab.progresssoft.foreignexchangedealsservice.model.DealRequest;

/**
 * The Validator interface defines a contract for validating foreign exchange deal request inputs.
 * Classes implementing this interface should provide methods to validate fields such as amount,
 * currency ISO codes, and more within a {@link DealRequest} object. Validation may result in exceptions
 * like {@link InvalidInputException} or {@link BaseException} when validation fails.
 */
public interface Validator {

    /**
     * Validates the input fields of a foreign exchange deal request.
     *
     * @param dealRequest The foreign exchange deal request to be validated.
     * @throws Exception            General exception that can be thrown during validation.
     * @throws InvalidInputException When validation of input fields fails due to invalid data.
     * @throws BaseException         A base exception class for custom exceptions related to validation.
     */
    void validateInput(DealRequest dealRequest) throws Exception;
}
