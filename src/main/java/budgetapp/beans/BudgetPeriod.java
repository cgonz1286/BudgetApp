package budgetapp.beans;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class BudgetPeriod {
	
	long BudgetPeriodId;
	String BudgetPeriodName;
	//LocalDate StartDate;
	//LocalDate EndDate; 

}
