package budgetapp.beans;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bills", schema = "budgeting")
public class BudgetedBills {
	
private long id;
private String name;
private int accountNum;
private String category;
private Date dateDue;
private double price;

/* !!!Remember to add getter and setter
@ManyToOne(cascade=CascadeType.PERSIST)
private BudgetPeriod budgetPeriod;
*/
public BudgetedBills() {
	super();
	// TODO Auto-generated constructor stub
}



public BudgetedBills(long id, String name, int accountNum, String category, Date dateDue, double price) {
	super();
	this.id = id;
	this.name = name;
	this.accountNum = accountNum;
	this.category = category;
	this.dateDue = dateDue;
	this.price = price;
}





public BudgetedBills(String name, int accountNum, String category, Date dateDue, double price) {
	super();
	this.name = name;
	this.accountNum = accountNum;
	this.category = category;
	this.dateDue = dateDue;
	this.price = price;
}



@Id
@Column(name = "id")
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
@Basic
@Column(name = "name")
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@Basic
@Column(name = "accountNum")
public int getAccountNum() {
	return accountNum;
}
public void setAccountNum(int accountNum) {
	this.accountNum = accountNum;
}
@Basic
@Column(name = "category")
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
@Basic
@Column(name = "dateDue")
public Date getDateDue() {
	return dateDue;
}
public void setDateDue(Date dateDue) {
	this.dateDue = dateDue;
}
@Basic
@Column(name = "price")
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}





}
