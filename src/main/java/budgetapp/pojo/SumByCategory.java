package budgetapp.pojo;


import budgetapp.beans.DiscretionaryCategory;


public class SumByCategory {

	private DiscretionaryCategory discCategory;
	private double amount;

	
	/**
	 * @param discCategory
	 * @param amount
	 */
	public SumByCategory(DiscretionaryCategory discCategory, 
			double amount) {
		super();
		this.discCategory = discCategory;
		this.amount = amount;
	}
	/**
	 * @return the DiscretionaryCategory
	 */
	public DiscretionaryCategory getDiscCategory() {
		return discCategory;
	}
	
	
	/**
	 * @param discCategory the DiscretionaryCategory to set
	 */
	public void setCategory(DiscretionaryCategory discCategory) {
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
		return "SumByPeriod [DiscretionaryCategory=" + discCategory.getDiscCategoryId()  + ", amount=" + amount + "] "
						+ "\n"
						+ "\n";
	}
	
	
	
}