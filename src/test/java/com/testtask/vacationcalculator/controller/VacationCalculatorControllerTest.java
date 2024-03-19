package com.testtask.vacationcalculator.controller;

import com.testtask.vacationcalculator.exception.VacationCalculatorException;
import com.testtask.vacationcalculator.model.VacationResponse;
import com.testtask.vacationcalculator.service.VacationCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(VacationCalculatorController.class)
public class VacationCalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VacationCalculatorService calculatorService;


    @Test
    public void testCalculateVacationAllowanceWithValidData() throws Exception {
        BigDecimal averageSalary = new BigDecimal("5000");
        int vacationDays = 20;
        BigDecimal expectedAllowance = new BigDecimal("3413.0");

        VacationResponse expectedResponse = new VacationResponse(expectedAllowance);
        when(calculatorService.calculateVacationAllowance(any())).thenReturn(expectedResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                        .param("averageSalary", averageSalary.toString())
                        .param("vacationDays", String.valueOf(vacationDays))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vacationAllowance").value(expectedAllowance));
    }

}
