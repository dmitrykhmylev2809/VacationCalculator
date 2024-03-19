package com.testtask.vacationcalculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class VacationResponse {
    private BigDecimal vacationAllowance;
}
