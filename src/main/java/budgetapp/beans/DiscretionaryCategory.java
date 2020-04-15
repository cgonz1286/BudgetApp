package budgetapp.beans;

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
	private long id;
	private String description;
	
	public DiscretionaryCategory() {
		super();
		this.description = "";
	}
	
	public DiscretionaryCategory(String description) {
		super();
		this.description = description;
	}
	
	public DiscretionaryCategory(long id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "DiscretionaryCategory [id=" + id + ", description=" + description + "]";
	}
	
}
