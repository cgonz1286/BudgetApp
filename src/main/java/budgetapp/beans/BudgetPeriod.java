package budgetapp.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


	@Entity
	public class BudgetPeriod {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private		long BudgetPeriodId;
		private String Description;
		//private LocalDate StartDate;
		//private LocalDate EndDate; 
		/**
		 * @param budgetPeriodId
		 */
		public BudgetPeriod(long budgetPeriodId) {
			super();
			BudgetPeriodId = budgetPeriodId;
		}
		/**
		 * @param budgetPeriodId
		 * @param Description
		 */
		public BudgetPeriod(long budgetPeriodId, String Description) {
			super();
			BudgetPeriodId = budgetPeriodId;
			Description = Description;
		}
		/**
		 * 
		 */
		public BudgetPeriod() {
			super();
		}


		
	/**
	 * @return the budgetPeriodId
	 */
	public long getBudgetPeriodId() {
		return BudgetPeriodId;
	}

	/**
	 * @param budgetPeriodId the budgetPeriodId to set
	 */
	public void setBudgetPeriodId(long budgetPeriodId) {
		BudgetPeriodId = budgetPeriodId;
	}
	/**
	 * @return the Description
	 */
	public String getDescription() {
		return Description;
	}
	/**
	 * @param Description the Description to set
	 */
	public void setDescription(String Description) {
		Description = Description;
	}

	@Override
	public String toString() {
		return "BudgetPeriod [getBudgetPeriodId()=" + getBudgetPeriodId() + ", getDescription()="
				+ getDescription() + "]";
	}

}
