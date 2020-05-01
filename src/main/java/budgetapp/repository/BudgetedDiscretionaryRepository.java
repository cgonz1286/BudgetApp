package budgetapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import budgetapp.beans.BudgetPeriod;
import budgetapp.beans.BudgetedDiscretionary;
import budgetapp.beans.DiscretionaryCategory;
import budgetapp.pojo.SpendingByCategoryAndPeriod;
import budgetapp.pojo.SumByCategory;
import budgetapp.pojo.SumByPeriod;

public interface BudgetedDiscretionaryRepository extends JpaRepository<BudgetedDiscretionary, Long>{
	//https://thoughts-on-java.org/spring-data-jpa-query-annotation/
	@Query("FROM #{#entityName} WHERE budgetPeriod = :bp")//parameterize query to limit risk of sql injection attack
	List<BudgetedDiscretionary> findByBudgetPeriod(@Param("bp") BudgetPeriod selectedPeriod);
	 	
	
	@Query("SELECT SUM(e.amount) FROM #{#entityName} e WHERE budgetPeriod = :bp")
	double sumByPeriod(@Param("bp") BudgetPeriod selectedPeriod);
	

	@Query("SELECT NEW budgetapp.pojo.SpendingByCategoryAndPeriod(e.discCategory, e.budgetPeriod, SUM(e.amount)) FROM #{#entityName} e GROUP BY e.discCategory, e.budgetPeriod ORDER BY e.discCategory, e.budgetPeriod")
	List<SpendingByCategoryAndPeriod> sumSpendingByCategoryAndPeriod();
}
