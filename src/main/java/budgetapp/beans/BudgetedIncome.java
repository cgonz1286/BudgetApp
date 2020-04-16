package budgetapp.beans;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;


	@Entity
	public class BudgetedIncome {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private		long id;
		
		@ManyToOne(cascade=CascadeType.PERSIST)
		private BudgetPeriod budgetPeriod;
		
		private String description;
		
	    @DateTimeFormat(pattern = "MM/dd/yyyy")
		private Date incomeDate;

	    private String amount; //https://www.javacodemonk.com/which-data-type-would-you-choose-for-storing-monetary-values-in-java-6692c78c
	    
	    /**
		 * @param id
		 */
		public BudgetedIncome(long id) {
			super();
			this.id = id;
		}
		/**
		 * @param id
		 * @param Description
		 */
		public BudgetedIncome(long id, String Description) {
			super();
			this.id = id;
			description = Description;
		}
		/**
		 * 
		 */
		public BudgetedIncome() {
			super();
		}


		
	/**
		 * @return the budgetPeriod
		 */
		public BudgetPeriod getBudgetPeriod() {
			return budgetPeriod;
		}
		/**
		 * @param budgetPeriod the budgetPeriod to set
		 */
		public void setBudgetPeriod(BudgetPeriod budgetPeriod) {
			this.budgetPeriod = budgetPeriod;
		}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the Description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param Description the Description to set
	 */
	public void setDescription(String Description) {
		description = Description;
	}
	
	/**
	 * @return the incomeDate
	 */
	public Date getIncomeDate() {
		return incomeDate;
	}
	/**
	 * @param incomeDate the incomeDate to set
	 */
	public void setIncomeDate(Date incomeDate) {
		this.incomeDate = incomeDate;
	}
	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "BudgetedIncome [id=" + id  + ", description=" + description
				+ ", incomeDate=" + incomeDate + ", amount=" + amount + "]";
	}

}
