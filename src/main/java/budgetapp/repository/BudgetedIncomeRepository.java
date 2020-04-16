package budgetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import budgetapp.beans.BudgetedIncome;

public interface BudgetedIncomeRepository extends JpaRepository<BudgetedIncome, Long>{
	
	}
