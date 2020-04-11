package budgetapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import budgetapp.beans.BudgetPeriod;
import budgetapp.beans.BudgetedIncome;
import budgetapp.repository.BudgetPeriodRepository;
import budgetapp.repository.BudgetedIncomeRepository;


@Controller
public class WebController {
	@Autowired
	BudgetPeriodRepository repoBudgetPeriod;
	@Autowired
	BudgetedIncomeRepository repoBudgetedIncome;
	
	@GetMapping({ "/" })
	public String index() {
		return "index.html";
	}
	@GetMapping({ "/viewAllBudgetPeriods" })
	public String viewAllBudgetPeriods(Model model) {
		if(repoBudgetPeriod.findAll().isEmpty()) {
			return addNewBudgetPeriod(model);
		}
		
		model.addAttribute("BudgetPeriods", repoBudgetPeriod.findAll());
		return "resultsPeriods";
	}

	@GetMapping("/inputBudgetPeriod")
	public String addNewBudgetPeriod(Model model) {
		BudgetPeriod p = new BudgetPeriod();
		model.addAttribute("newBudgetPeriod", p);
		return "inputPeriods";
	}

	@GetMapping("/editBudgetPeriod/{id}")
	public String showUpdateBudgetPeriod(@PathVariable("id") long id, Model model) {
		BudgetPeriod p = repoBudgetPeriod.findById(id).orElse(null);
		System.out.println("ITEM TO EDIT: " + p.toString());
		model.addAttribute("newBudgetPeriod", p);
		return "inputPeriods";
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
	@GetMapping("/selectBudgetPeriod/{id}")
	public BudgetPeriod selectBudgetPeriod(@PathVariable("id") long id, Model model) {
		BudgetPeriod p = repoBudgetPeriod.findById(id).orElse(null);
	    return p;
	}
///	
}