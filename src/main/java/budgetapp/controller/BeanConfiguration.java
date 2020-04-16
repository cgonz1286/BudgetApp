package budgetapp.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import budgetapp.beans.BudgetPeriod;
import budgetapp.beans.BudgetedBills;
import budgetapp.beans.DiscretionaryCategory;

@Configuration
public class BeanConfiguration {
	
	@Bean
	public BudgetPeriod budgetPeriod() {
		BudgetPeriod bean = new BudgetPeriod();
		return bean;
	}

	@Bean
	public BudgetedBills budgetedbills() {
		BudgetedBills bean = new BudgetedBills();
		return bean;
	}
	
	@Bean
	public DiscretionaryCategory discretionaryCategory() {
		DiscretionaryCategory bean = new DiscretionaryCategory();
		return bean;
	}
	
}