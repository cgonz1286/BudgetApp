package budgetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import budgetapp.beans.BudgetPeriod;

public interface BudgetPeriodRepository extends JpaRepository<BudgetPeriod, Long>{

}
