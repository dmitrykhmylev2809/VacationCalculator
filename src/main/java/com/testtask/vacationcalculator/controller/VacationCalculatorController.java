package com.testtask.vacationcalculator.controller;

import com.testtask.vacationcalculator.model.VacationRequest;
import com.testtask.vacationcalculator.model.VacationResponse;
import com.testtask.vacationcalculator.service.VacationCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class VacationCalculatorController {

    private final VacationCalculatorService calculatorService;

    @GetMapping("/calculate")
    public VacationResponse calculateVacationAllowance(@RequestParam BigDecimal averageSalary,
                                                       @RequestParam int vacationDays){
        VacationRequest request = new VacationRequest();
        request.setAverageSalary(averageSalary);
        request.setVacationDays(vacationDays);

        return calculatorService.calculateVacationAllowance(request);
    }
}