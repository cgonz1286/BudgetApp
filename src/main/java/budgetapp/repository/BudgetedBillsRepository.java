package budgetapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import budgetapp.beans.BudgetPeriod;
import budgetapp.beans.BudgetedBills;

public interface BudgetedBillsRepository extends JpaRepository<BudgetedBills, Long>{
	//Amy
	//https://thoughts-on-java.org/spring-data-jpa-query-annotation/
	  @Query("FROM #{#entityName} WHERE budgetPeriod = :bp")//parameterize query to limit risk of sql injection attack
	  List<BudgetedBills> findByBudgetPeriod(@Param("bp") BudgetPeriod selectedPeriod);
		
		@Query("SELECT SUM(e.price) FROM #{#entityName} e WHERE budgetPeriod = :bp")
		double sumByPeriod(@Param("bp") BudgetPeriod selectedPeriod);
	

	}
