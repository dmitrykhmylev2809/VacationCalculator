package com.testtask.vacationcalculator.service;

import com.testtask.vacationcalculator.model.VacationRequest;
import com.testtask.vacationcalculator.model.VacationResponse;


public interface VacationCalculatorService {
    VacationResponse calculateVacationAllowance(VacationRequest request);
}
