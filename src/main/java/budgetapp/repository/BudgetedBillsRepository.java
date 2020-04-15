package budgetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import budgetapp.beans.BudgetedBills;

public interface BudgetedBillsRepository extends JpaRepository<BudgetedBills, Long>{
	
	}
