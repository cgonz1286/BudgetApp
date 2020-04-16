package budgetapp.beans;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


	@Entity
	public class BudgetPeriod {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private		long id;
		private String description;
		private LocalDate startDate;
		//private LocalDate endDate; 
		/**
		 * @param id
		 */
		public BudgetPeriod(long id) {
			super();
			this.id = id;
		}
		/**
		 * @param id
		 * @param Description
		 */
		public BudgetPeriod(long id, String Description) {
			super();
			this.id = id;
			description = Description;
		}
		/**
		 * 
		 */
		public BudgetPeriod() {
			super();
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

	@Override
	public String toString() {
		return "BudgetPeriod [getId()=" + getId() + ", getDescription()="+ getDescription() + "]";
	}

}
