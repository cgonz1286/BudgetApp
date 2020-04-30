package budgetapp.pojo;


import budgetapp.beans.BudgetPeriod;
import budgetapp.beans.DiscretionaryCategory;


public class SpendingByCategoryAndPeriod {
	/**
	 * @param budgetPeriod
	 * @param discCategory
	 * @param amount
	 */
	public SpendingByCategoryAndPeriod(DiscretionaryCategory discCategory, BudgetPeriod budgetPeriod, 
			double amount) {
		super();
		this.discCategory = discCategory;
		this.budgetPeriod = budgetPeriod;
		this.amount = amount;
	}
	private BudgetPeriod budgetPeriod;
	private DiscretionaryCategory discCategory;
	private double amount;
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
	 * @return the discCategory
	 */
	public DiscretionaryCategory getDiscCategory() {
		return discCategory;
	}
	/**
	 * @param discCategory the discretionaryCategory to set
	 */
	public void setDiscCategory(DiscretionaryCategory discCategory) {
		this.discCategory = discCategory;
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
		return "SpendingByCategoryAndPeriod [budgetPeriod=" + budgetPeriod.getId() + ", discCategory="
				+ discCategory.getDescription() + ", amount=" + amount + "] "
						+ "\n"
						+ "\n";
	}
	
	
	
}