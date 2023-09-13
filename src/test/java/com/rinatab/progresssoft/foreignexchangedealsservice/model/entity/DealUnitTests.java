package com.rinatab.progresssoft.foreignexchangedealsservice.model.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class DealUnitTests {

    private Deal deal;

    @BeforeEach
    public void setUp() {
        deal = new Deal();
    }

    @Test
    public void testDealId() {
        int dealId = 1;
        deal.setDealId(dealId);
        assertEquals(dealId, deal.getDealId());
    }

    @Test
    public void testToCurrency() {
        String toCurrency = "USD";
        deal.setToCurrency(toCurrency);
        assertEquals(toCurrency, deal.getToCurrency());
    }

    @Test
    public void testFromCurrency() {
        String fromCurrency = "EUR";
        deal.setFromCurrency(fromCurrency);
        assertEquals(fromCurrency, deal.getFromCurrency());
    }

    @Test
    public void testAmount() {
        BigDecimal amount = new BigDecimal("100.50");
        deal.setAmount(amount);
        assertEquals(amount, deal.getAmount());
    }

    @Test
    public void testTime() {
        Date time = new Date();
        deal.setTime(time);
        assertEquals(time, deal.getTime());
    }
}
