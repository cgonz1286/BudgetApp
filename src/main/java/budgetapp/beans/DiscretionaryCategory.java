package budgetapp.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DiscretionaryCategory")
public class DiscretionaryCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="DiscretionaryCategoryId")
	private long discretionaryCategoryId;
	
	@Column(name="Description")
	private String description;
	
	public DiscretionaryCategory() {
		super();
		this.description = "";
	}
	
	public DiscretionaryCategory(String description) {
		super();
		this.description = description;
	}
	
	public DiscretionaryCategory(long discretionaryCategoryId, String description) {
		super();
		this.discretionaryCategoryId = discretionaryCategoryId;
		this.description = description;
	}

	public long getDiscretionaryCategoryId() {
		return discretionaryCategoryId;
	}

	public void setDiscretionaryCategoryId(long discretionaryCategoryId) {
		this.discretionaryCategoryId = discretionaryCategoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "DiscretionaryCategory [discretionaryCategoryId=" + discretionaryCategoryId + ", description=" + description + "]";
	}
	
}
