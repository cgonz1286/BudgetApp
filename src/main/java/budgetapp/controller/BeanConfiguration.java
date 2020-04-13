package budgetapp.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import budgetapp.beans.BudgetPeriod;


@Configuration
public class BeanConfiguration {
	
	
	@Bean
	public BudgetPeriod budgetPeriod() {
		BudgetPeriod bean = new BudgetPeriod();
		return bean;
	}
}
