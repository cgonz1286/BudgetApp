package budgetapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import budgetapp.beans.BudgetPeriod;
import budgetapp.beans.BudgetedBills;
import budgetapp.beans.BudgetedDiscretionary;

public interface BudgetedDiscretionaryRepository extends JpaRepository<BudgetedDiscretionary, Long>{
	//Amy
	//https://thoughts-on-java.org/spring-data-jpa-query-annotation/
	  @Query("FROM #{#entityName} WHERE budgetPeriod = :bp")//parameterize query to limit risk of sql injection attack
	 List<BudgetedDiscretionary> findByBudgetPeriod(@Param("bp") BudgetPeriod selectedPeriod);
	
	
}
