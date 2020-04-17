package budgetapp.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(
		name="budgeted_discretionary"
)
public class BudgetedDiscretionary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="budgeted_discretionary_id")
	private long budgetDiscId; // auto-generated, pk, not null
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	private BudgetPeriod budgetPeriod; // stores budgetPeriod.id, fk, not null, @ManyToOne
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	private DiscretionaryCategory discCategory; // not a list, stores discCategory.id, fk, not null, @ManyToOne
	
	@Column(name="budgeted_discretionary_amount", nullable=false, length=10)
	private double budgetDiscAmount; // not null, 10

	public BudgetedDiscretionary() {
		super();
		this.budgetDiscAmount = 0;		
	}

	public BudgetedDiscretionary(long budgetDiscId, BudgetPeriod budgetPeriod) {
		super();
		this.budgetDiscId = budgetDiscId;
		this.budgetPeriod = budgetPeriod;
		this.budgetDiscAmount = 0;
	}

	public BudgetedDiscretionary(long budgetDiscId, BudgetPeriod budgetPeriod, DiscretionaryCategory discCategory, double budgetDiscAmount) {
		super();
		this.budgetDiscId = budgetDiscId;
		this.budgetPeriod = budgetPeriod;
		this.discCategory = discCategory;
		this.budgetDiscAmount = budgetDiscAmount;
	}

	public long getBudgetDiscId() {
		return budgetDiscId;
	}

	public void setBudgetDiscId(long budgetDiscId) {
		this.budgetDiscId = budgetDiscId;
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

	public double getBudgetDiscAmount() {
		return budgetDiscAmount;
	}

	public void setBudgetDiscAmount(double budgetDiscAmount) {
		this.budgetDiscAmount = budgetDiscAmount;
	}

	@Override
	public String toString() {
		return "BudgetedDiscretionary [budgetDiscId=" + budgetDiscId + ", budgetPeriod=" + budgetPeriod
				+ ", discCategory=" + discCategory + ", budgetDiscAmount=" + budgetDiscAmount + "]";
	}
	
}
