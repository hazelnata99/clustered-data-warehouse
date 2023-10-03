package com.rinatab.progresssoft.foreignexchangedealsservice.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@AllArgsConstructor
@Setter
@Getter
@ToString
public class DealRequest {

    private int dealId;

    private String toCurrency;

    private String fromCurrency;

    private BigDecimal amount;

    private LocalDateTime time;

    public DealRequest(){

    }
}
