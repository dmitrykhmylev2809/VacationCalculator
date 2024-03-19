package com.testtask.vacationcalculator.model;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class VacationRequest {
    private BigDecimal averageSalary;
    private int vacationDays;
}