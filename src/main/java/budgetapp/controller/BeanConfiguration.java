package budgetapp.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import budgetapp.beans.BudgetPeriod;
import budgetapp.beans.BudgetedBills;
import budgetapp.beans.BudgetedDiscretionary;
import budgetapp.beans.BudgetedIncome;
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
	public BudgetedIncome budgetedincome() {
		BudgetedIncome bean = new BudgetedIncome();
		return bean;
	}
	
	@Bean
	public DiscretionaryCategory discretionaryCategory() {
		DiscretionaryCategory bean = new DiscretionaryCategory();
		return bean;
	}
	
	@Bean
	public BudgetedDiscretionary budgetedDiscretionary() {
		BudgetedDiscretionary bean = new BudgetedDiscretionary();
		return bean;
	}
	
}