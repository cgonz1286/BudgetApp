package budgetapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import budgetapp.beans.BudgetPeriod;
import budgetapp.beans.BudgetedIncome;

public interface BudgetedIncomeRepository extends JpaRepository<BudgetedIncome, Long>{
	//https://thoughts-on-java.org/spring-data-jpa-query-annotation/
	  @Query("FROM #{#entityName} WHERE budgetPeriod = :bp")//parameterize query to limit risk of sql injection attack
	 List<BudgetedIncome> findByBudgetPeriod(@Param("bp") BudgetPeriod selectedPeriod);
	
	  
	  @Query("Select amount FROM #{#entityName} WHERE budgetPeriod = :bp group by budgetPeriod")//parameterize query to limit risk of sql injection attack
	 List<Object[]> sumByBudgetPeriod(@Param("bp") BudgetPeriod selectedPeriod);
	
}