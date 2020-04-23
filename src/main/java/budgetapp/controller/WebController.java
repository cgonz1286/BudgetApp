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
		System.out.println("??? /editBudgetPeriod/{id} ITEM TO EDIT: " + p.toString());
		model.addAttribute("newBudgetPeriod", p);
		return "inputPeriod";
	}
//"inputPeriod" <form th:object="${newBudgetPeriod}" th:action="@{/updateBudgetPeriod/{id}(id=${newBudgetPeriod.id})}" method=POST>
	
	@PostMapping("/updateBudgetPeriod/{id}")
	public String reviseBudgetPeriod(BudgetPeriod p, Model model) {
		System.out.println("??? reviseBudgetPeriod ...BudgetPeriod ID to edit is " + p.getId());		
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
//////////////////BudgetedBill maps////////////////////////
	@GetMapping("/updateBudgetedBill")
	public String newBudgetedBill(Model model) {
		BudgetedBills p = new BudgetedBills();
		model.addAttribute("newBudgetedBill", p);
		return "BudgetedBill";
	}
	
	@GetMapping({ "/viewAllBudgetedBills" })
	public String viewAllBudgetedBills(Model model) {
		if(repoBudgetedBills.findAll().isEmpty()) {
			return newBudgetedBill(model);
		}
		
		model.addAttribute("BudgetedBills", repoBudgetedBills.findAll());
		return "resultsBudgetedBills";
	}
	@GetMapping("/editBudgetedBill/{id}")
	public String showUpdateBudgetedBill(@PathVariable("id") long id, Model model) {
		BudgetedBills p = repoBudgetedBills.findById(id).orElse(null);
		System.out.println("???/editBudgetedBill/{id} ITEM TO EDIT: " + p.toString());
		model.addAttribute("newBudgetedBills", p);
		return "inputBudgetedBills";
	}

	@PostMapping("/updateBudgetedBills/{id}")
	public String reviseBudgetedBills(BudgetedBills p, Model model) {
		repoBudgetedBills.save(p);
		return viewAllBudgetedBills(model);
	}
	
	@GetMapping("/deleteBudgetedBills/{id}")
	public String deleteBudgetedBills(@PathVariable("id") long id, Model model) {
		BudgetedBills p = repoBudgetedBills.findById(id).orElse(null);
	    repoBudgetedBills.delete(p);
	    return viewAllBudgetedBills(model);
	}

////////////////End of BudgededBill Maps////////////////////

	////////////////BudgetedIncome maps//////////////////////

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
		model.addAttribute("newBudgetedIncome", b);
		model.addAttribute("selectedBudgetPeriod", selectedPeriod);
		System.out.println("??? /inputBudgetedIncome/{periodId} inputIncome");

		return "inputIncome";
	}
	


	//!!!START Added GoTo - will also need to edit the post action link on reports.html to add the .../GoToReports . 
	//I created a simplified input form to collect just the edits and pass through the GoTo destination (see editIncome.html)
	@GetMapping("/editBudgetedIncome/{id}/{GoTo}")
	public String showUpdateBudgetedIncome(@PathVariable("id") long id, @PathVariable("GoTo") String GoTo,  Model model) {
		BudgetedIncome b = repoBudgetedIncome.findById(id).orElse(null);
		System.out.println("??? /editBudgetedIncome/{id}/{GoTo} ITEM TO EDIT: " + b.toString());
		System.out.println("??? /editBudgetedIncome/{id}/{GoTo} budget period: " + b.getBudgetPeriod().getId());
		
		BudgetPeriod selectedPeriod =  b.getBudgetPeriod();
		System.out.println("??? /editBudgetedIncome/{id}/{GoTo} budget period: " + selectedPeriod.toString());
				
		model.addAttribute("selectedBudgetPeriod", selectedPeriod);
		model.addAttribute("newBudgetedIncome", b);
		model.addAttribute("BudgetedIncomes", repoBudgetedIncome.findAll());
		return "editIncome";
	}
	
			//made the original map redirect to the above mapping so no need to change existing links, they will just default to the Input Form.
			@GetMapping("/editBudgetedIncome/{id}")
			public String showUpdateBudgetedIncome(@PathVariable("id") long id,  Model model) {
					return showUpdateBudgetedIncome(id, "InputForm", model);
			}
			
	///editIncome post action leads here
	///If you want other destinations, add another if statement to return to that. If more reports are added, can specify which report to go to.
	@PostMapping("/updateBudgetedIncome/{id}/{periodId}/{GoTo}")
	public String reviseBudgetedIncome( @PathVariable("periodId") long periodId, @PathVariable("GoTo") String GoTo, BudgetedIncome b, Model model) {
		System.out.println("??? reviseBudgetedIncome ...BudgetedIncome ID to edit is " + b.getId());

		BudgetPeriod selectedPeriod = repoBudgetPeriod.findById(periodId).orElse(null);
		System.out.println("??? reviseBudgetedIncome ...selectedPeriod is " + selectedPeriod.getId());

		b.setBudgetPeriod(selectedPeriod);
		System.out.println("??? reviseBudgetedIncome ...set period is " + b.getBudgetPeriod().getId());

		repoBudgetedIncome.save(b);

		System.out.println(" ??? /updateBudgetedIncome/{id}/{periodId   editIncomeGoToInputForm form");
		if(GoTo.equals("GoToReports")) {
			return viewReports(selectedPeriod.getId(), model);
		}
		else {
		return addNewBudgetedIncome( selectedPeriod.getId(),  model) ;//!!!
		}
	}
				//made the original map redirect to the above mapping so no need to change existing links, they will just default to the Input Form.
				@PostMapping("/updateBudgetedIncome/{id}/{periodId}")
				public String reviseBudgetedIncome( @PathVariable("periodId") long periodId, BudgetedIncome b, Model model) {
					
					return reviseBudgetedIncome( periodId, "InputForm", b, model)	;
					
				}

	@GetMapping("/deleteBudgetedIncome/{id}/{GoTo}")
	public String deleteBudgetedIncome(@PathVariable("id") long id, @PathVariable("GoTo") String GoTo, Model model) {
		BudgetedIncome b = repoBudgetedIncome.findById(id).orElse(null);
		BudgetPeriod selectedPeriod = b.getBudgetPeriod();//!!! add selected period
	    repoBudgetedIncome.delete(b);
	    if(GoTo.equals("GoToReports")) {
			return viewReports(selectedPeriod.getId(), model);
		}
	    return addNewBudgetedIncome(selectedPeriod.getId(), model);//!!! add selected period
		
	}
				//made the original map redirect to the above mapping so no need to change existing links, they will just default to the Input Form.
				@GetMapping("/deleteBudgetedIncome/{id}")
				public String deleteBudgetedIncome(@PathVariable("id") long id, Model model) {
				    return deleteBudgetedIncome(id, "GoToInputForm",  model);
				}
	
	//END Added GoTo to the above
	
	
	/*
	//original version does not have GoTo
	@PostMapping("/updateBudgetedIncome/{periodId}")
	public String reviseBudgetedIncome(@PathVariable("periodId") long periodId, BudgetedIncome b, Model model) {
		BudgetPeriod selectedPeriod = repoBudgetPeriod.findById(periodId).orElse(null);
		b.setBudgetPeriod(selectedPeriod);
		repoBudgetedIncome.save(b);
		return addNewBudgetedIncome( selectedPeriod.getId(),  model) ;//!!!
	}
	
	 	@GetMapping("/editBudgetedIncomeFromReport/{id}")
	public String showUpdateBudgetedIncomeFromReport(@PathVariable("id") long id,  Model model) {
		BudgetedIncome b = repoBudgetedIncome.findById(id).orElse(null);
		System.out.println("???ITEM TO EDIT: " + b.toString());
		
		BudgetPeriod selectedPeriod = b.getBudgetPeriod(); ///!!!Add this so the input form layout will work with the edit mapping
		System.out.println("???ITEM TO EDIT: " + selectedPeriod.toString());

		model.addAttribute("selectedBudgetPeriod", selectedPeriod);

		model.addAttribute("newBudgetedIncome", b);
		model.addAttribute("BudgetedIncomes", repoBudgetedIncome.findAll());///!!Add this so the input form will work with the edit mapping

		return "editIncomeGoToInputForm";
	}
	
	//!!!edited to return to input form, have not added GoTo to this yet
	@GetMapping("/deleteBudgetedIncome/{id}")
	public String deleteBudgetedIncome(@PathVariable("id") long id, Model model) {
		BudgetedIncome b = repoBudgetedIncome.findById(id).orElse(null);
		BudgetPeriod selectedPeriod = b.getBudgetPeriod();//!!! add
	    repoBudgetedIncome.delete(b);
	    return addNewBudgetedIncome(selectedPeriod.getId(), model);//!!! add
	}
	*/
	

	
	//Maybe temporary, using this for troubleshooting
	@GetMapping({ "/viewBudgetedIncomeDetail/{id}" })
	public String viewBudgetedIncomeDetail(@PathVariable("id") long id, Model model) {
		BudgetedIncome b = repoBudgetedIncome.findById(id).orElse(null);
		BudgetPeriod p = b.getBudgetPeriod();
		model.addAttribute("selectedBudgetedIncome", b);
		model.addAttribute("linkedBudgetPeriod", p);
		return "resultsIncomeDetail";
	}
	//////////End of BudgetedIncomeMaps////////////////////////////////
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
	
}