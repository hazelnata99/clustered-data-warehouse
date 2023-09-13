package com.rinatab.progresssoft.foreignexchangedealsservice.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Represents a foreign exchange deal entity.
 */

@Entity
@Table(name = "deal")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Deal {

    /**
     * The unique identifier for a foreign exchange deal.
     */
    @Id
    @Column(name = "deal_id", nullable = false, unique = true)
    private int dealId;

    /**
     * The "to" currency ISO code .
     */
    @Column(name = "to_currency")
    private String toCurrency;

    /**
     * The currency ISO code of ordering.
     */
    @Column(name = "from_currency")
    private String fromCurrency;

    /**
     * The amount in ordering currency
     */
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    /**
     * The timestamp of deal ordering.
     */
    @Column(name = "time")
    private Date time;
}
