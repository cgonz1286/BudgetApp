package budgetapp.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import budgetapp.beans.BudgetPeriod;
import budgetapp.beans.BudgetedIncome;

@Configuration
public class BeanConfiguration {
	
	@Bean
	public BudgetedIncome budgetedIncome() {
		BudgetedIncome bean = new BudgetedIncome();
		return bean;
	}
	@Bean
	public BudgetPeriod budgetPeriod() {
		BudgetPeriod bean = new BudgetPeriod();
		return bean;
	}
}
