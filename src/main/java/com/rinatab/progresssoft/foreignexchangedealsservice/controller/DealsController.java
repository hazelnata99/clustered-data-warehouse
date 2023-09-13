package com.rinatab.progresssoft.foreignexchangedealsservice.controller;

import com.rinatab.progresssoft.foreignexchangedealsservice.model.DealRequest;
import com.rinatab.progresssoft.foreignexchangedealsservice.model.entity.Deal;
import com.rinatab.progresssoft.foreignexchangedealsservice.service.deals.DealsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fx-deals") // Set a common base path for all endpoints in this controller
@Slf4j
public class DealsController {

    private final DealsServiceImpl dealsService;

    @Autowired
    public DealsController(DealsServiceImpl dealsService) {
        this.dealsService = dealsService;
    }

    /**
     * Provides information about the FX Deals API endpoints.
     *
     * @return A message containing descriptions of available endpoints.
     */
    @GetMapping("/help")
    public ResponseEntity<String> help() {
        String helpMessage = "Welcome to the FX Deals API. You can use the following endpoints:\n" +
                "- POST /save: Save a new FX deal\n" +
                "- GET /show: Retrieve a list of all FX deals\n" +
                "- POST /uploadFile: Upload a file contains fields of fx into DB\n";
        return ResponseEntity.ok(helpMessage);
    }


    /**
     * Defines an endpoint to save a new FX deal.
     *
     * @param dealRequest The request containing the FX deal details to be saved.
     * @return A ResponseEntity indicating the success or failure of the operation.
     */
    @PostMapping("/save")
    public ResponseEntity<Void> saveFxDeal(@RequestBody DealRequest dealRequest) {
        return dealsService.addDeal(dealRequest);
    }

    /**
     * Retrieves a list of all FX deals from the database and logs the retrieved data.
     *
     * @return A ResponseEntity containing the list of FX deals or an error response.
     */
    @GetMapping("/show")
    public ResponseEntity<List<Deal>> showFxDeals() {
        try {
            log.info("Starting to pull all fx deals data from DB..");
            List<Deal> deals = dealsService.getAllFxDeals();

            if (!deals.isEmpty()) {
                // Log the retrieved data
                for (Deal deal : deals) {
                    log.info("Retrieved FX Deal: ID={}, From Currency={}, To Currency={}, Amount={}\n",
                            deal.getDealId(), deal.getFromCurrency(), deal.getToCurrency(), deal.getAmount());
                }
            } else {
                log.info("No FX deals found in the database.");
            }

            return new ResponseEntity<>(deals, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("An error occurred while retrieving FX deals: {}", ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Define an endpoint to upload a file (TODO: implement)
    @PostMapping("/uploadFile")
    public ResponseEntity<Void> uploadFile() {
        // TODO: Add logic to handle file upload
        return new ResponseEntity<>(HttpStatus.OK);
    }
}