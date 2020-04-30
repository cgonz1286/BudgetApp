package budgetapp.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class BudgetPeriod {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private	long id;
	private String description;
	
    @DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date startDate;
   
    @DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date endDate; 
    
	//cascade type merge so that it doesn't create duplicates of same item
	@OneToMany(mappedBy="budgetPeriod", cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private ArrayList<BudgetedIncome> listOfBudgetedIncomes = new ArrayList<BudgetedIncome>();
	
	//!!!REmember to re-add setters and getters!!!	
	//cascade type merge so that it doesn't create duplicates of same item
	@OneToMany(mappedBy="budgetPeriod", cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private ArrayList<BudgetedBills> listOfBudgetedBills= new ArrayList<BudgetedBills>();
	
	//!!!REmember to re-add setters and getters!!!				
	//cascade type merge so that it doesn't create duplicates of same item
	@OneToMany(mappedBy="budgetPeriod", cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private ArrayList<BudgetedDiscretionary> listOfBudgetedDiscretionaries= new ArrayList<BudgetedDiscretionary>();

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
	 * @return the listOfBudgetedIncomes
	 */
	public List<BudgetedIncome> getListOfBudgetedIncomes() {
		return listOfBudgetedIncomes;
	}
	
	/**
	 * @param listOfBudgetedIncomes the listOfBudgetedIncomes to set
	 */
	public void setListOfBudgetedIncomes(ArrayList<BudgetedIncome> listOfBudgetedIncomes) {
		this.listOfBudgetedIncomes = listOfBudgetedIncomes;
	}
	
	public List<BudgetedBills> getListOfBudgetedBills() {
		return listOfBudgetedBills;
	}
	
		public void setListOfBudgetedBills(ArrayList<BudgetedBills> listOfBudgetedBills) {
		this.listOfBudgetedBills = listOfBudgetedBills;
	}
	
	/**
	 * @return the listOfBudgetedDiscretionaries
	 */
	public List<BudgetedDiscretionary> getListOfBudgetedDiscretionaries() {
		return listOfBudgetedDiscretionaries;
	}
	/**
	 * @param listOfBudgetedDiscretionaries the listOfBudgetedDiscretionaries to set
	 */
	public void setListOfBudgetedDiscretionaries(ArrayList<BudgetedDiscretionary> listOfBudgetedDiscretionaries) {
		this.listOfBudgetedDiscretionaries = listOfBudgetedDiscretionaries;
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
		String tostr="BudgetPeriod [id=" + getId() + ", description="+ getDescription() + "] ";
		return tostr;
	}
	
	public String toStringlinkedincomes() {
		String tostr="BudgetPeriod [id=" + getId() + ", description="+ getDescription() + "] For test purposes, linked Incomes: ";
		for(BudgetedIncome b : listOfBudgetedIncomes) {
			tostr+= b.toString()+", ";
		} ;
		tostr+="end";
		return tostr;
	}

}

