package com.testtask.vacationcalculator;

import com.testtask.vacationcalculator.controller.VacationCalculatorController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VacationcalculatorApplicationTests {

	@Autowired
	private VacationCalculatorController controller;

	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}
