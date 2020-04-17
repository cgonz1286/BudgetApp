package budgetapp.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;


	@Entity
	public class BudgetPeriod {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private		long id;
		private String description;
		
	    @DateTimeFormat(pattern = "MM/dd/yyyy")
		private Date startDate;
	   
	    @DateTimeFormat(pattern = "MM/dd/yyyy")
		private Date endDate; 
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
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "BudgetPeriod [getId()=" + getId() + ", getDescription()="+ getDescription() + "]";
	}

}
