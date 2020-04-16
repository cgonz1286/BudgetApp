package budgetapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import budgetapp.beans.BudgetPeriod;
import budgetapp.beans.BudgetedBills;
import budgetapp.beans.DiscretionaryCategory;
import budgetapp.repository.BudgetPeriodRepository;
import budgetapp.repository.BudgetedBillsRepository;
import budgetapp.repository.DiscretionaryCategoryRepository;

@Controller
public class WebController {
	@Autowired
	BudgetPeriodRepository repoBudgetPeriod;
	@Autowired
	BudgetedBillsRepository repoBudgetedBills;
	@Autowired
	DiscretionaryCategoryRepository repoDiscretionaryCategory;
		
//	@GetMapping("/index")
//	public String index(Model model) {
//		return "index";
//	}
	
	@GetMapping({ "/viewAllBudgetPeriods" })
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

	@PostMapping("/updateBudgetPeriod/{id}")
	public String reviseBudgetPeriod(BudgetPeriod p, Model model) {
		repoBudgetPeriod.save(p);
		return viewAllBudgetPeriods(model);
	}
	
	@GetMapping("/deleteBudgetPeriod/{id}")
	public String deleteBudgetPeriod(@PathVariable("id") long id, Model model) {
		BudgetPeriod p = repoBudgetPeriod.findById(id).orElse(null);
	    repoBudgetPeriod.delete(p);
	    return viewAllBudgetPeriods(model);
	}
///something like this to select the item to add linked objects to...
//	@GetMapping("/selectBudgetPeriod/{id}")
//	public BudgetPeriod selectBudgetPeriod(@PathVariable("id") long id, Model model) {
//		BudgetPeriod p = repoBudgetPeriod.findById(id).orElse(null);
//	    return p;
//	}
///	
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
		System.out.println("ITEM TO EDIT: " + p.toString());
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
	
	// ------------------------------
	// DiscretionaryCategory Mappings 
	// ------------------------------
//	@GetMapping({"/viewAllDiscretionaryCategories"})
//	public String viewAllDiscretionaryCategories(Model model) {
//		if(repoDiscretionaryCategory.findAll().isEmpty()) {
//			return addNewDiscretionaryCategory(model);
//		}
//		
//		model.addAttribute("discretionaryCategories", repoDiscretionaryCategory.findAll());
//		
//		return "discretionaryCategory";
//	}
	
	@GetMapping("/inputDiscretionaryCategory")
	public String addNewDiscretionaryCategory(Model model) {
		DiscretionaryCategory dc = new DiscretionaryCategory();
		
		model.addAttribute("newDiscretionaryCategory", dc);
		
		if(repoDiscretionaryCategory.findAll().isEmpty()) {
			return "discretionaryCategory";
		} else {
			model.addAttribute("allDiscretionaryCategories", repoDiscretionaryCategory.findAll());
			return "discretionaryCategory";
		}
	}
	
	@PostMapping("/inputDiscretionaryCategory")
	public String addNewDiscretionaryCategory(@ModelAttribute DiscretionaryCategory dc, Model model) {
		repoDiscretionaryCategory.save(dc);
		
		return addNewDiscretionaryCategory(model);
	}
	
//	@PostMapping("/updateDiscretionaryCategory/{id}")
//	public String reviseDiscretionaryCategory(DiscretionaryCategory dc, Model model) {
//		repoDiscretionaryCategory.save(dc);
//		
//		return addNewDiscretionaryCategory(model);
//	}
}