package budgetapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import budgetapp.beans.BudgetPeriod;
import budgetapp.beans.BudgetedBills;
import budgetapp.beans.BudgetedIncome;
import budgetapp.beans.DiscretionaryCategory;
import budgetapp.repository.BudgetPeriodRepository;
import budgetapp.repository.BudgetedBillsRepository;
import budgetapp.repository.BudgetedDiscretionaryRepository;
import budgetapp.repository.BudgetedIncomeRepository;
import budgetapp.repository.DiscretionaryCategoryRepository;

@Controller
public class WebController {
	@Autowired
	BudgetPeriodRepository repoBudgetPeriod;
	@Autowired
	BudgetedBillsRepository repoBudgetedBills;
	@Autowired
	BudgetedIncomeRepository repoBudgetedIncome;
	@Autowired
	DiscretionaryCategoryRepository repoDiscretionaryCategory;
	@Autowired
	BudgetedDiscretionaryRepository repoBudgetedDiscretionary;
	
//	@GetMapping({ "/","/index", "/index.html"})
//	public String index() {
//		return "index.html";
//	}

	@GetMapping({"/index.html"})
	public String index() {
		return "index.html";
	}
		
	@GetMapping({"/viewAllBudgetPeriods" ,"/" })

	public String viewAllBudgetPeriods(Model model) {
		if(repoBudgetPeriod.findAll().isEmpty()) {
			return addNewBudgetPeriod(model);
		}
		
		model.addAttribute("BudgetPeriods", repoBudgetPeriod.findAll());
		return "resultsPeriod";
	}

	@GetMapping("/inputBudgetPeriod")
	public String addNewBudgetPeriod(Model model) {
		BudgetPeriod p = new BudgetPeriod();
		model.addAttribute("newBudgetPeriod", p);
		return "inputPeriod";
	}

	@GetMapping("/editBudgetPeriod/{id}")
	public String showUpdateBudgetPeriod(@PathVariable("id") long id, Model model) {
		BudgetPeriod p = repoBudgetPeriod.findById(id).orElse(null);
		System.out.println("ITEM TO EDIT: " + p.toString());
		model.addAttribute("newBudgetPeriod", p);
		return "inputPeriod";
	}
/*
	@GetMapping("/continueBudgetPeriodtoIncome/{id}")
	public String addIncometoBudgetPeriod(@PathVariable("id") long id, Model model) {
		BudgetPeriod p = repoBudgetPeriod.findById(id).orElse(null);
		System.out.println("ITEM TO EDIT: " + p.toString());
		model.addAttribute("selectedBudgetPeriod", p);
		return "inputPeriod";
	}
*/
	@PostMapping("/updateBudgetPeriod/{id}")
	public String reviseBudgetPeriod(BudgetPeriod p, Model model) {
		repoBudgetPeriod.save(p);
		
		return viewAllBudgetPeriods(model);
	}
   
	@GetMapping("/viewReports/{periodId}")
	public String viewReports(@PathVariable("periodId") long periodId, Model model) {
		BudgetPeriod p = repoBudgetPeriod.findById(periodId).orElse(null);
	    model.addAttribute("selectedBudgetPeriod", p);
	    model.addAttribute("periodIncomes", p.getListOfBudgetedIncomes());
	    model.addAttribute("periodIncomesString", p.toString());

	    return "reports";
	}	
	
	@GetMapping("/deleteBudgetPeriod/{id}")
	public String deleteBudgetPeriod(@PathVariable("id") long id, Model model) {
		BudgetPeriod p = repoBudgetPeriod.findById(id).orElse(null);
	    repoBudgetPeriod.delete(p);
	    return viewAllBudgetPeriods(model);
	}
	////////////////End of BudgetPeriod Maps////////////////////
	
	//////////////////BudgetedBill maps/////////////////////////

	@GetMapping("/inputBudgetedBill/{periodId}")
	public String newBudgetedBill(@PathVariable("periodId") long periodId, Model model) {
		BudgetedBills p = new BudgetedBills();
		
		BudgetPeriod selectedPeriod = repoBudgetPeriod.findById(periodId).orElse(null);
		
		model.addAttribute("newBudgetedBill", repoBudgetedBills.findAll());
		
		model.addAttribute("BudgetedBills", p);
		model.addAttribute("selectedBudgetPeriod", selectedPeriod);
		
		
		return "BudgetedBill";
	}
	
	/*@GetMapping("/inputBudgetedBill/{id}")
	public String newBudgetedBill(@PathVariable("id") long id, Model model) {
		BudgetedBills p = new BudgetedBills();
		
		BudgetPeriod selectedPeriod = repoBudgetPeriod.findById(id).orElse(null);
		
		model.addAttribute("newBudgetedBill", repoBudgetedBills.findAll());
		
		model.addAttribute("BudgetedBills", p);
		model.addAttribute("selectedBudgetPeriod", selectedPeriod);
		
		
		return "BudgetedBill";
	}*/
	
	
	@GetMapping({ "/viewAllBudgetedBills" })
	public String viewAllBudgetedBills(Model model) {
		if(repoBudgetedBills.findAll().isEmpty()) {
			return viewAllBudgetedBills(model);
		}
		
		model.addAttribute("BudgetedBills", repoBudgetedBills.findAll());
		return "resultsBudgetedBills";
	}
	
	@GetMapping("/editBudgetedBill/{id}")
	public String showUpdateBudgetedBill(@PathVariable("id") long id, Model model) {
		BudgetedBills p = repoBudgetedBills.findById(id).orElse(null);
		System.out.println("ITEM TO EDIT: " + p.toString());
		model.addAttribute("newBudgetedBills", p);
		return "inputBudgetedBill";
	}

	@PostMapping("/updateBudgetedBills/{periodId}")
	public String reviseBudgetedBills(@PathVariable("periodId") long periodId, BudgetedBills bb, Model model) {
		
		BudgetPeriod selectedPeriod = repoBudgetPeriod.findById(periodId).orElse(null);
		
//		bb.setBudgetPeriod(selectedPeriod); // commented this line out due to it causing errors
		
		
		repoBudgetedBills.save(bb);
		return viewAllBudgetedBills(model);
	}
	
	@GetMapping("/deleteBudgetedBills/{id}")
	public String deleteBudgetedBills(@PathVariable("id") long id, Model model) {
		BudgetedBills p = repoBudgetedBills.findById(id).orElse(null);
	    repoBudgetedBills.delete(p);
	    return viewAllBudgetedBills(model);
	}


	////////////////End of BudgededBill Maps////////////////////

	///////////////////BudgetedIncome maps//////////////////////

	@GetMapping({ "/viewAllBudgetedIncomes" })
	public String viewAllBudgetedIncomes(Model model) {
		if(repoBudgetedIncome.findAll().isEmpty()) {
			return viewAllBudgetPeriods(model);
		}
		model.addAttribute("BudgetedIncomes", repoBudgetedIncome.findAll());
		return "resultsIncome";
	}
	
	///continue from period to inputBudgetedIncome
	//!!! use this format to allow join, pass in the period id and add BudgetPeriod as an attribute
	//!!!add the findAll attribute if you are also displaying the existing entries on the input form
	@GetMapping("/inputBudgetedIncome/{periodId}")
	public String addNewBudgetedIncome(@PathVariable("periodId") long periodId, Model model) {
	BudgetedIncome b = new BudgetedIncome();
	BudgetPeriod selectedPeriod = repoBudgetPeriod.findById(periodId).orElse(null);
	model.addAttribute("BudgetedIncomes", repoBudgetedIncome.findAll());
	//b.setBudgetPeriod(selectedPeriod);

		model.addAttribute("newBudgetedIncome", b);
		model.addAttribute("selectedBudgetPeriod", selectedPeriod);

		return "inputIncome";
	}

	@GetMapping("/editBudgetedIncome/{id}")
	public String showUpdateBudgetedIncome(@PathVariable("id") long id,  Model model) {
		BudgetedIncome b = repoBudgetedIncome.findById(id).orElse(null);
		System.out.println("ITEM TO EDIT: " + b.toString());
		model.addAttribute("newBudgetedIncome", b);

		return "inputIncome";
	}

	//!!! use this format to allow join
	@PostMapping("/updateBudgetedIncome/{periodId}")
	public String reviseBudgetedIncome(@PathVariable("periodId") long periodId, BudgetedIncome b, Model model) {
		BudgetPeriod selectedPeriod = repoBudgetPeriod.findById(periodId).orElse(null);
		b.setBudgetPeriod(selectedPeriod);
		repoBudgetedIncome.save(b);
		return viewAllBudgetedIncomes(model);
	}
	
	@GetMapping("/deleteBudgetedIncome/{id}")
	public String deleteBudgetedIncome(@PathVariable("id") long id, Model model) {
		BudgetedIncome b = repoBudgetedIncome.findById(id).orElse(null);
	    repoBudgetedIncome.delete(b);
	    return viewAllBudgetedIncomes(model);
	}
	
	//Maybe temporary, using this for troubleshooting
	@GetMapping({ "/viewBudgetedIncomeDetail/{id}" })
	public String viewBudgetedIncomeDetail(@PathVariable("id") long id, Model model) {
		BudgetedIncome b = repoBudgetedIncome.findById(id).orElse(null);
		BudgetPeriod p = b.getBudgetPeriod();
		model.addAttribute("selectedBudgetedIncome", b);
		model.addAttribute("linkedBudgetPeriod", p);
		return "resultsIncomeDetail";
	}
	//////////End of BudgetedIncomeMaps/////////////////////////
	
	// ------------------------------
	// DiscretionaryCategory Mappings 
	// ------------------------------
	@GetMapping("/mainDiscretionaryCategory")
	public String addNewDiscretionaryCategory(Model model) {
		DiscretionaryCategory dc = new DiscretionaryCategory();
		
		model.addAttribute("discretionaryCategory", dc);	
		
		if(repoDiscretionaryCategory.findAll().isEmpty()) {
			model.addAttribute("allDiscretionaryCategories", "EMPTY");
		} else {
			model.addAttribute("allDiscretionaryCategories", repoDiscretionaryCategory.findAll());
		}
		
		return "discretionaryCategory";
	}
	
	@PostMapping("/updateDiscretionaryCategory/{id}")
	public String reviseDiscretionaryCategory(DiscretionaryCategory dc, Model model) {
		repoDiscretionaryCategory.save(dc);
		
		return addNewDiscretionaryCategory(model);
	}
	
	@GetMapping("/editDiscretionaryCategory/{id}")
	public String showUpdateDiscretionaryCategory(@PathVariable("id") long discCategoryId, Model model) {
		DiscretionaryCategory dc = repoDiscretionaryCategory.findById(discCategoryId).orElse(null);
			
		model.addAttribute("discretionaryCategory", dc);
		
		if(repoDiscretionaryCategory.findAll().isEmpty()) {
			model.addAttribute("allDiscretionaryCategories", "EMPTY");
		} else {
			model.addAttribute("allDiscretionaryCategories", repoDiscretionaryCategory.findAll());
		}
		
		return "discretionaryCategory";
	}
	
	@GetMapping("/deleteDiscretionaryCategory/{id}")
	public String deleteUser(@PathVariable("id") long discCategoryId, Model model) {
		DiscretionaryCategory dc = repoDiscretionaryCategory.findById(discCategoryId).orElse(null);
		repoDiscretionaryCategory.delete(dc);
		
		return addNewDiscretionaryCategory(model);
	}
	
	// ------------------------------
	// BudgetedDiscretionary Mappings 
	// ------------------------------
	
	// !!!!!!!!!! COPY/PASTED BELOW FROM DISCRETIONARYCATEGORY MAPPINGS, SO NEED TO CHANGE FOR BUDGETEDDISCRETIONARY !!!!!!!!!!!!
	
//	@GetMapping("/mainDiscretionaryCategory")
//	public String addNewDiscretionaryCategory(Model model) {
//		DiscretionaryCategory dc = new DiscretionaryCategory();
//		
//		model.addAttribute("discretionaryCategory", dc);	
//		
//		if(repoDiscretionaryCategory.findAll().isEmpty()) {
//			model.addAttribute("allDiscretionaryCategories", "EMPTY");
//		} else {
//			model.addAttribute("allDiscretionaryCategories", repoDiscretionaryCategory.findAll());
//		}
//		
//		return "discretionaryCategory";
//	}
//
//	@PostMapping("/updateDiscretionaryCategory/{id}")
//	public String reviseDiscretionaryCategory(DiscretionaryCategory dc, Model model) {
//		repoDiscretionaryCategory.save(dc);
//		
//		return addNewDiscretionaryCategory(model);
//	}
//
//	@GetMapping("/editDiscretionaryCategory/{id}")
//	public String showUpdateDiscretionaryCategory(@PathVariable("id") long discCategoryId, Model model) {
//		DiscretionaryCategory dc = repoDiscretionaryCategory.findById(discCategoryId).orElse(null);
//			
//		model.addAttribute("discretionaryCategory", dc);
//		
//		if(repoDiscretionaryCategory.findAll().isEmpty()) {
//			model.addAttribute("allDiscretionaryCategories", "EMPTY");
//		} else {
//			model.addAttribute("allDiscretionaryCategories", repoDiscretionaryCategory.findAll());
//		}
//		
//		return "discretionaryCategory";
//	}
//	
//	@GetMapping("/deleteDiscretionaryCategory/{id}")
//	public String deleteUser(@PathVariable("id") long discCategoryId, Model model) {
//		DiscretionaryCategory dc = repoDiscretionaryCategory.findById(discCategoryId).orElse(null);
//		repoDiscretionaryCategory.delete(dc);
//		
//		return addNewDiscretionaryCategory(model);
//	}
	
}