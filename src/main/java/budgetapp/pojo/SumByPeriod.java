package budgetapp.pojo;


import budgetapp.beans.BudgetPeriod;
import budgetapp.beans.DiscretionaryCategory;


public class SumByPeriod {

	private BudgetPeriod budgetPeriod;
	private double amount;

	
	/**
	 * @param budgetPeriod
	 * @param amount
	 */
	public SumByPeriod(BudgetPeriod budgetPeriod, 
			double amount) {
		super();
		this.budgetPeriod = budgetPeriod;
		this.amount = amount;
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
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "SumByPeriod [budgetPeriod=" + budgetPeriod.getId()  + ", amount=" + amount + "] "
						+ "\n"
						+ "\n";
	}
	
	
	
}