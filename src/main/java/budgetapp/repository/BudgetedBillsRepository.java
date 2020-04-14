package budgetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import budgetapp.beans.BudgetedBills;

public interface budgetedBillsRepository extends JpaRepository<BudgetedBills, Long>{
	
	}
