package budgetapp.beans;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class BudgetedBills {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
private String name;
private int accountNum;
private String category;
@DateTimeFormat(pattern = "MM/dd/yyyy")
private Date dateDue;
private double price;


@ManyToOne(cascade=CascadeType.PERSIST)
private BudgetPeriod budgetPeriod;

public BudgetedBills() {
	super();
	// TODO Auto-generated constructor stub
}

public BudgetedBills(long id) {
	super();
	this.id = id;
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

public BudgetedBills(String name, int accountNum, String category, Date dateDue, double price, BudgetPeriod budgetperiod) {
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
@Column(name = "account_num")
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


@ManyToOne
public BudgetPeriod getBudgetPeriod() {
	return budgetPeriod;
}
public void setBudgetPeriod(BudgetPeriod budgetPeriod) {
	this.budgetPeriod = budgetPeriod;
}


}
