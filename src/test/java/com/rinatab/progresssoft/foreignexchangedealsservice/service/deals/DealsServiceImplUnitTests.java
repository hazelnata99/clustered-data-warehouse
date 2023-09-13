package com.rinatab.progresssoft.foreignexchangedealsservice.service.deals;

import com.rinatab.progresssoft.foreignexchangedealsservice.exception.InvalidInputException;
import com.rinatab.progresssoft.foreignexchangedealsservice.model.DealRequest;
import com.rinatab.progresssoft.foreignexchangedealsservice.model.entity.Deal;
import com.rinatab.progresssoft.foreignexchangedealsservice.repository.DealsRepository;
import com.rinatab.progresssoft.foreignexchangedealsservice.utils.Validator;
import com.rinatab.progresssoft.foreignexchangedealsservice.utils.ValidatorImpl;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;


public class DealsServiceImplUnitTests {

    @Mock
    private DealsRepository mockDealsRepository;

    @Mock
    private ValidatorImpl validator;

    @InjectMocks
    private DealsServiceImpl dealsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this); // Initialize mock objects
    }

    @Test
    public void testGetAllFxDeals(){
        Mockito.when(mockDealsRepository.findAll()).thenReturn(Collections.singletonList(new Deal()));
        List<Deal> result = dealsService.getAllFxDeals();
        assert !result.isEmpty();
    }

    @Test
    public void testFindFxDeal(){
        Mockito.when(mockDealsRepository.existsFxDealByDealId(anyInt())).thenReturn(true);
        boolean result = dealsService.findFxDeal(100);
        verify(mockDealsRepository, times(1)).existsFxDealByDealId(100);
        assert result;
    }

    @Test
    public void testAddingNonExistingFxDeal(){

        DealRequest dealRequest = new DealRequest(2343, "AED", "JOD", BigDecimal.valueOf(200));

        when(mockDealsRepository.existsFxDealByDealId(2343)).thenReturn(false);
        doNothing().when(validator).validateInput(any());
        ResponseEntity<Void> responseEntity = dealsService.addDeal(dealRequest);

//        verify(validator, times(0)).validateInput(dealRequest);
//        verify(mockDealsRepository, times(1)).existsFxDealByDealId(1);
//        verify(mockDealsRepository, times(1)).save(any(Deal.class));
        assert responseEntity.getStatusCode() == HttpStatus.OK;
    }
//
//    @Test
//    public void testAddingExistingFxDeal(){
//        DealRequest dealRequest = new DealRequest(1, "AED", "EUR", BigDecimal.valueOf(100));
//
//        when(mockDealsRepository.existsFxDealByDealId(1)).thenReturn(true);
//
//        ResponseEntity<Void> responseEntity = dealsService.addDeal(dealRequest);
//
//        // Assert
//        verify(validator, never()).validateInput(dealRequest);
//        verify(mockDealsRepository, times(1)).existsFxDealByDealId(dealRequest.getDealId());
//        verify(mockDealsRepository, never()).save(any());
//        assert responseEntity.getStatusCode() == HttpStatus.IM_USED;
//    }

//    @Test
//    public void testAddingInvalidFxDeal(){
//
//        DealRequest dealRequest = new DealRequest(1, "AED", "JOD", BigDecimal.valueOf(200));
//
//        doThrow(new InvalidInputException("Invalid input", 1, "deal.toCurrency")).when(validator).validateInput(dealRequest);
//        when(mockDealsRepository.existsFxDealByDealId(1)).thenReturn(false);
//
//        ResponseEntity<Void> responseEntity = dealsService.addDeal(dealRequest);
//
//        // Assert
//        verify(validator, times(1)).validateInput(dealRequest);
//        verify(mockDealsRepository, never()).existsFxDealByDealId(any());
//        verify(mockDealsRepository, never()).save(any());
//        assert responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST;
//    }
}
