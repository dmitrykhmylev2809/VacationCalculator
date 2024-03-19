package com.testtask.vacationcalculator.service.impl;

import com.testtask.vacationcalculator.exception.VacationCalculatorException;
import com.testtask.vacationcalculator.model.VacationRequest;
import com.testtask.vacationcalculator.model.VacationResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VacationCalculatorServiceImplTest {

    private final VacationCalculatorServiceImpl calculatorService = new VacationCalculatorServiceImpl();

    @DisplayName("Проверка правильности возвращаемого расчета ")
    @Test
    public void testCalculateVacationAllowanceWithValidData() {
        VacationRequest request = new VacationRequest();
        request.setAverageSalary(new BigDecimal("5000"));
        request.setVacationDays(20);

        VacationResponse response = calculatorService.calculateVacationAllowance(request);

        assertEquals(new BigDecimal("3413.00"), response.getVacationAllowance());
    }

    @DisplayName("Проверка исключения при средней зарплате = 0 ")
    @Test
    public void testCalculateVacationAllowanceWithZeroAverageSalary() {
        VacationRequest request = new VacationRequest();
        request.setAverageSalary(BigDecimal.ZERO);
        request.setVacationDays(20);

        VacationCalculatorException exception = assertThrows(VacationCalculatorException.class, () -> calculatorService.calculateVacationAllowance(request));
        assertEquals("Некорректная сумма зарплаты или количество дней отпуска", exception.getMessage());
    }

    @DisplayName("Проверка исключения при количестве дней отпуска = 0 ")
    @Test
    public void testCalculateVacationAllowanceWithZeroVacationDays() {
        VacationRequest request = new VacationRequest();
        request.setAverageSalary(new BigDecimal("5000"));
        request.setVacationDays(0);

        VacationCalculatorException exception = assertThrows(VacationCalculatorException.class, () -> calculatorService.calculateVacationAllowance(request));
        assertEquals("Количество дней отпуска не может быть 0", exception.getMessage());
    }

    @DisplayName("Проверка исключения отрицательном значении количества дней отпуска ")
    @Test
    public void testCalculateVacationAllowanceWithNegativeAverageSalary() {
        VacationRequest request = new VacationRequest();
        request.setAverageSalary(new BigDecimal("-5000"));
        request.setVacationDays(5);

        VacationCalculatorException exception = assertThrows(VacationCalculatorException.class, () -> calculatorService.calculateVacationAllowance(request));
        assertEquals("Некорректная сумма зарплаты или количество дней отпуска", exception.getMessage());
    }

    @DisplayName("Проверка исключения отрицательном значении количества дней отпуска ")
    @Test
    public void testCalculateVacationAllowanceWithNegativeVacationDays() {
        VacationRequest request = new VacationRequest();
        request.setAverageSalary(new BigDecimal("5000"));
        request.setVacationDays(-5);

        VacationCalculatorException exception = assertThrows(VacationCalculatorException.class, () -> calculatorService.calculateVacationAllowance(request));
        assertEquals("Некорректная сумма зарплаты или количество дней отпуска", exception.getMessage());
    }
}