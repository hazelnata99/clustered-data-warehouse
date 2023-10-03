package com.rinatab.progresssoft.foreignexchangedealsservice.service.deals;

import com.rinatab.progresssoft.foreignexchangedealsservice.exception.InvalidCurrencyIsoCodeException;
import com.rinatab.progresssoft.foreignexchangedealsservice.exception.InvalidInputException;
import com.rinatab.progresssoft.foreignexchangedealsservice.model.DealRequest;
import com.rinatab.progresssoft.foreignexchangedealsservice.model.entity.Deal;
import com.rinatab.progresssoft.foreignexchangedealsservice.repository.DealsRepository;
import com.rinatab.progresssoft.foreignexchangedealsservice.utils.ValidatorImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DealsServiceImpl implements  DealsService{
    private final DealsRepository dealsRepository;

    private ValidatorImpl validator;

    /**
     * Constructor for the DealService class.
     *
     * @param dealsRepository The repository used to access deals data.
     */
    @Autowired
    public DealsServiceImpl(DealsRepository dealsRepository, ValidatorImpl validator) {
        this.dealsRepository = dealsRepository;
        this.validator = validator;
    }

    /**
     * Retrieves a list of all foriegn exchange deals in the database.
     *
     * @return A list of fx deals.
     */
    public List<Deal> getAllFxDeals() {
        return dealsRepository.findAll();
    }

    /**
     * Retrieves a list of all foriegn exchange deals in the database.
     *
     * @return A list of fx deals.
     */
    public boolean findFxDeal(int dealId) {
        return dealsRepository.existsFxDealByDealId(dealId);
    }

    /**
     * Adds a new fx deal to the library.
     *
     * @param dealRequestDto The foreign exchange deal to be added.
     */
    public ResponseEntity<String> addDeal(DealRequest dealRequestDto) {
        try {
            // Input values validation
            validator.validateInput(dealRequestDto);

            log.info("Validating if a FX deal already exists in DB. Deal ID: {}", dealRequestDto.getDealId());
            if (!findFxDeal(dealRequestDto.getDealId())) {

                log.info("The FX deal doesn't exists in DB. Deal ID: {}. Continuing to add fx deal to DB.", dealRequestDto.getDealId());

                // Create a new Deal object
                Deal dealToBeAdded = Deal.builder()
                        .dealId(dealRequestDto.getDealId())
                        .toCurrency(dealRequestDto.getToCurrency())
                        .fromCurrency(dealRequestDto.getFromCurrency())
                        .amount(dealRequestDto.getAmount())
                        .build();

                // Save the deal to the database
                log.info("Inserting the FX deal into DB. Deal ID: {}", dealRequestDto.getDealId());
                dealsRepository.save(dealToBeAdded);

                log.info("Successfully added FX deal request into DB. Deal ID: {}", dealRequestDto.getDealId());
                return new ResponseEntity<String>("The fx deal has successfully been added..",HttpStatus.OK);
            } else {
                log.warn("This FX deal (ID: {}) already exists in the DB.", dealRequestDto.getDealId());
                return new ResponseEntity<>("The FX deal already exists",HttpStatus.IM_USED);
            }
        } catch (HttpMessageNotReadableException ex) {
            log.error("The body request you provided is invalid. Error: {}", ex.getMessage());
            return new ResponseEntity<>("Unable to add the FX deal with ID: ${dealRequestDto.getDealId()} as it is an invalid. Check logs for more details.",HttpStatus.BAD_REQUEST);
        } catch (InvalidInputException | InvalidCurrencyIsoCodeException ex) {
            log.error("Values entered within the request are invalid. Error: {}", ex.getMessage());
            return new ResponseEntity<>("Unable to add the FX deal with ID: ${dealRequestDto.getDealId()} as it is an invalid. Check logs for more details.",HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            // Catch any unexpected exceptions
            log.error("An unexpected error occurred while processing the request. Error: {}", ex.getMessage());
            return new ResponseEntity<>("Unable to add the FX deal with ID: ${dealRequestDto.getDealId()} as it is an invalid. Check logs for more details.",HttpStatus.BAD_REQUEST);
        }
    }

}
