package budgetapp.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
		name="discretionary_category",
		uniqueConstraints = @UniqueConstraint(columnNames={"DESCRIPTION"})
)
public class DiscretionaryCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="DiscretionaryCategoryId")
	private long discCategoryId;
	
	@Column(name="DESCRIPTION", nullable=false, length=30)
	private String description;
	
	public DiscretionaryCategory() {
		super();
		this.description = "";
	}
	
	public DiscretionaryCategory(String description) {
		super();
		this.description = description;
	}
	
	public DiscretionaryCategory(long discCategoryId, String description) {
		super();
		this.discCategoryId = discCategoryId;
		this.description = description;
	}

	public long getDiscCategoryId() {
		return discCategoryId;
	}

	public void setDiscCategoryId(long discCategoryId) {
		this.discCategoryId = discCategoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "DiscretionaryCategory [discCategoryId=" + discCategoryId + ", description=" + description + "]";
	}
	
}
