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
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;


public class DealsServiceImplUnitTests {

    @Mock
    private DealsRepository mockDealsRepository;

    @Mock
    private ValidatorImpl validator;

    private DealsServiceImpl dealsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.dealsService = new DealsServiceImpl(mockDealsRepository, validator);
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
    public void testAddFxDealSuccess(){

        DealRequest dealRequest = new DealRequest();
        dealRequest.setDealId(101);
        dealRequest.setFromCurrency("SAR");
        dealRequest.setToCurrency("JOD");
        dealRequest.setAmount(BigDecimal.valueOf(100));
        dealRequest.setTime(LocalDateTime.of(2023, 4, 14, 23, 54, 9, 0));
        
        // Mock repository method
        when(mockDealsRepository.existsFxDealByDealId(dealRequest.getDealId())).thenReturn(false);
        doNothing().when(validator).validateInput(any());
        ResponseEntity<String> response = dealsService.addDeal(dealRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("The fx deal has successfully been added..", response.getBody());
        verify(mockDealsRepository, times(1)).save(any(Deal.class));
    }

   @Test
   public void testAddingExistingFxDeal(){

        DealRequest dealRequest = new DealRequest();
        dealRequest.setDealId(100);
        dealRequest.setFromCurrency("SAR");
        dealRequest.setToCurrency("JOD");
        dealRequest.setAmount(BigDecimal.valueOf(100));
        dealRequest.setTime(LocalDateTime.of(2023, 4, 14, 23, 54, 9, 0));

       when(mockDealsRepository.existsFxDealByDealId(100)).thenReturn(true);

       ResponseEntity<String> responseEntity = dealsService.addDeal(dealRequest);

       // Assert
       verify(validator, times(1)).validateInput(dealRequest);
       verify(mockDealsRepository, times(1)).existsFxDealByDealId(dealRequest.getDealId());
       verify(mockDealsRepository, never()).save(any());
       assert responseEntity.getStatusCode() == HttpStatus.IM_USED;
       assertEquals("The FX deal already exists", responseEntity.getBody());

   }

   @Test
   public void testAddingInvalidFxDeal(){

        DealRequest dealRequest = new DealRequest();
        dealRequest.setDealId(100);
        dealRequest.setFromCurrency("SAR");
        dealRequest.setToCurrency("JOD");
        dealRequest.setAmount(BigDecimal.valueOf(100));
        dealRequest.setTime(LocalDateTime.of(2023, 4, 14, 23, 54, 9, 0));
       doThrow(new InvalidInputException("Invalid input", 1, "deal.toCurrency")).when(validator).validateInput(dealRequest);
       when(mockDealsRepository.existsFxDealByDealId(1)).thenReturn(false);

       ResponseEntity<String> responseEntity = dealsService.addDeal(dealRequest);

       // Assert
       verify(validator, times(1)).validateInput(dealRequest);
       verify(mockDealsRepository, never()).existsFxDealByDealId(dealRequest.getDealId());
       verify(mockDealsRepository, never()).save(any());
       assert responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST;
   }
}
