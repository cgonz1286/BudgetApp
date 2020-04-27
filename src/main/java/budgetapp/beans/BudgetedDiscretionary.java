package budgetapp.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="budgeted_discretionary")
public class BudgetedDiscretionary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="budgeted_discretionary_id")
	private long id; // auto-generated, pk, not null
	
	@ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	private BudgetPeriod budgetPeriod; // stores budgetPeriod.id, fk, not null, @ManyToOne
	
	@ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	private DiscretionaryCategory discCategory; // not a list, stores discCategory.id, fk, not null, @ManyToOne
	
	@Column(name="budgeted_discretionary_amount", nullable=false, length=10)
	private double amount; // not null, 10

	public BudgetedDiscretionary() {
		super();
		this.amount = 0;
	}

	public BudgetedDiscretionary(long id, BudgetPeriod budgetPeriod) {
		super();
		this.id = id;
		this.budgetPeriod = budgetPeriod;
		this.amount = 0;
	}

	public BudgetedDiscretionary(long id, BudgetPeriod budgetPeriod, DiscretionaryCategory discCategory, double amount) {
		super();
		this.id = id;
		this.budgetPeriod = budgetPeriod;
		this.discCategory = discCategory;
		this.amount = amount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BudgetPeriod getBudgetPeriod() {
		return budgetPeriod;
	}

	public void setBudgetPeriod(BudgetPeriod budgetPeriod) {
		this.budgetPeriod = budgetPeriod;
	}

	public DiscretionaryCategory getDiscCategory() {
		return discCategory;
	}

	public void setDiscCategory(DiscretionaryCategory discCategory) {
		this.discCategory = discCategory;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "BudgetedDiscretionary [id=" + id + ", budgetPeriod=" + budgetPeriod
				+ ", discCategory=" + discCategory + ", amount=" + amount + "]";
	}
	
}
