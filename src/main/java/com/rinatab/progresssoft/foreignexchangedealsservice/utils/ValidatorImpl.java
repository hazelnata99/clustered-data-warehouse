package com.rinatab.progresssoft.foreignexchangedealsservice.utils;

import com.rinatab.progresssoft.foreignexchangedealsservice.exception.BaseException;
import com.rinatab.progresssoft.foreignexchangedealsservice.exception.InvalidCurrencyIsoCodeException;
import com.rinatab.progresssoft.foreignexchangedealsservice.exception.InvalidInputException;
import com.rinatab.progresssoft.foreignexchangedealsservice.model.DealRequest;
import org.springframework.stereotype.Service;

/**
 * Implementation of the Validator interface for validating foreign exchange deal request inputs.
 * This class provides methods for validating fields such as amount, currency ISO codes, and more
 * within a {@link DealRequest} object and throws custom exceptions when validation fails.
 */
@Service
public class ValidatorImpl implements Validator {

    /**
     * Validates the input fields of a foreign exchange deal request.
     *
     * @param requestDTO The foreign exchange deal request to be validated.
     * @throws BaseException When validation fails, a custom exception is thrown indicating the validation error.
     *                      This can be either an {@link InvalidInputException} or an {@link InvalidCurrencyIsoCodeException}.
     */
    @Override
    public void validateInput(DealRequest requestDTO) throws BaseException {
        if (requestDTO.getAmount() == null) {
            throw new InvalidInputException("Amount should not be null", 0, "deal.amount");
        }
        if (requestDTO.getToCurrency().isEmpty() || requestDTO.getToCurrency().isBlank()) {
            throw new InvalidInputException("to_currency shouldn't be null or blank", 0, "deal.toCurrency");
        }
        if (requestDTO.getFromCurrency().isEmpty() || requestDTO.getFromCurrency().isBlank()) {
            throw new InvalidInputException("from_currency shouldn't be null or blank", 0, "deal.FROMCurrency");
        }
        if (requestDTO.getFromCurrency().isBlank()) {
            throw new InvalidCurrencyIsoCodeException("This is not a valid currency ISO code", 0, "deal.FROMCurrency");
        }
        if (requestDTO.getToCurrency().isBlank()) {
            throw new InvalidCurrencyIsoCodeException("This is not a valid currency ISO code", 0, "deal.toCurrency");
        }
    }
}
