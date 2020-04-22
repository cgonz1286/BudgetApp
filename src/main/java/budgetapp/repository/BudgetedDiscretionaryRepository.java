package budgetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import budgetapp.beans.BudgetedDiscretionary;

public interface BudgetedDiscretionaryRepository extends JpaRepository<BudgetedDiscretionary, Long>{

}
