package com.testtask.vacationcalculator.service.impl;

import com.testtask.vacationcalculator.exception.VacationCalculatorException;
import com.testtask.vacationcalculator.model.VacationRequest;
import com.testtask.vacationcalculator.model.VacationResponse;
import com.testtask.vacationcalculator.service.VacationCalculatorService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class VacationCalculatorServiceImpl implements VacationCalculatorService {

    private static final double AVERAGE_DAYS_IN_MONTH = 29.3;

    @Override
    public VacationResponse calculateVacationAllowance(VacationRequest request) {

        if (request.getAverageSalary().compareTo(BigDecimal.ZERO) <= 0 || request.getVacationDays() < 0) {
            throw new VacationCalculatorException("Некорректная сумма зарплаты или количество дней отпуска",
                    new IllegalArgumentException());
        }
        if (request.getVacationDays() == 0) {
            throw new VacationCalculatorException("Количество дней отпуска не может быть 0",
                    new ArithmeticException());
        }

        BigDecimal averageSalary = request.getAverageSalary();
        BigDecimal vacationDays = BigDecimal.valueOf(request.getVacationDays());

        BigDecimal vacationAllowance = averageSalary
                .divide(BigDecimal.valueOf(AVERAGE_DAYS_IN_MONTH), 2, RoundingMode.HALF_UP)
                .multiply(vacationDays)
                .setScale(2, RoundingMode.HALF_UP);

            return new VacationResponse(vacationAllowance);
    }

}
