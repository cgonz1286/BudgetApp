package budgetapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import budgetapp.beans.BudgetPeriod;
import budgetapp.beans.BudgetedBills;
import budgetapp.beans.BudgetedDiscretionary;
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

	@GetMapping({"/index.html"})
	public String index() {
		return "index.html";
	}
	
	//------------------------------------------------------
	//                Calculations Section
	//use the getBudgetPeriodEntries and getBudgetPeriodSums for reports, or copy just the line you need into your own mapping.                            
	//------------------------------------------------------
	double calcTotalBudgetedIncome(BudgetPeriod selectedPeriod){
		 List<BudgetedIncome> BudgetedIncomes = repoBudgetedIncome.findByBudgetPeriod(selectedPeriod);
		 double totalIncome = 0;

		 for (BudgetedIncome b : BudgetedIncomes)
		 {
			 totalIncome += b.getAmount();
				System.out.println("??? calcTotalBudgetedIncome "+b.getId()+" "+b.getAmount());
		 }
			System.out.println("??? calcTotalBudgetedIncome "+totalIncome);

		 return totalIncome;
	}
	
	double calcTotalBudgetedBills(BudgetPeriod selectedPeriod){
		 List<BudgetedBills> BudgetedDiscretionaries = repoBudgetedBills.findByBudgetPeriod(selectedPeriod);
		 double totalBill = 0;

		 for (BudgetedBills b : BudgetedDiscretionaries)
		 {
			 totalBill += b.getPrice();
				System.out.println("??? calcTotalBudgetedBill "+b.getId()+" "+b.getPrice());
		 }
			System.out.println("??? calcTotalBudgetedBill "+totalBill);

		 return totalBill;
	}
	
	double calcTotalBudgetedDiscretionary(BudgetPeriod selectedPeriod){
		 List<BudgetedDiscretionary> BudgetedDiscretionaries = repoBudgetedDiscretionary.findByBudgetPeriod(selectedPeriod);
		 double totalDiscretionary = 0;

		 for (BudgetedDiscretionary b : BudgetedDiscretionaries)
		 {
			 totalDiscretionary += b.getAmount();
				System.out.println("??? calcTotalBudgetedDiscretionary "+b.getId()+" "+b.getAmount());
		 }
			System.out.println("??? calcTotalBudgetedDiscretionary "+totalDiscretionary);

		 return totalDiscretionary;
	}
	
	double calcIncomeMinusBills(BudgetPeriod selectedPeriod){
		return calcTotalBudgetedIncome(selectedPeriod) - calcTotalBudgetedBills(selectedPeriod);
	}
	
	double calcRemainingToBudget(BudgetPeriod selectedPeriod){
		return calcTotalBudgetedIncome(selectedPeriod) - calcTotalBudgetedBills(selectedPeriod) - calcTotalBudgetedDiscretionary(selectedPeriod);
	}
	
	//to use this, add "model = getBudgetPeriodEntries(model, selectedPeriod)" as a line to your mapping 
	//then you can reference these attributes on your html page. See the income section of reports.html for example
	//use this to create table of all entries
	public Model getBudgetPeriodEntries(Model model, BudgetPeriod selectedPeriod) {
		model.addAttribute("periodIncomes", repoBudgetedIncome.findByBudgetPeriod(selectedPeriod)); //!!!Changed this to query the repo, allows the reports.html to refresh better
		model.addAttribute("periodBills", repoBudgetedBills.findByBudgetPeriod(selectedPeriod)); //!!!Changed this to query the repo, allows the reports.html to refresh better
		model.addAttribute("periodDiscretionaries", repoBudgetedDiscretionary.findByBudgetPeriod(selectedPeriod)); //!!!Changed this to query the repo, allows the reports.html to refresh better
		return model;
	}	
	
	//to use this, add "model = getBudgetPeriodSums(model, selectedPeriod)" as a line to your mapping 
	//then you can reference these attributes on your html page. See the income section of reports.html for example
	public Model getBudgetPeriodSums(Model model, BudgetPeriod selectedPeriod) {
		double inc =  calcTotalBudgetedIncome(selectedPeriod);//!!!Changed this to query the repo instead of using list in BudgetPeriod, allows the reports.html to refresh better
		double bills = calcTotalBudgetedBills(selectedPeriod);
		double disc = calcTotalBudgetedDiscretionary(selectedPeriod);
		double incMinusBills = inc - bills;
		double budgetBalance = inc - bills - disc;
		String balanceInstructions = "";
		if(budgetBalance > 0)
		{
			balanceInstructions = " Funds available to budget (increase spending amounts or add spending categories)";
		}
		else if (budgetBalance < 0)
		{
			balanceInstructions = " Budgeted total is greater than income total (decrease spending amounts or delete spending categories)";
		}
		else
		{
			balanceInstructions = "Balanced budget (all income has been budgeted into bill and/or spending categories)";
		}
		model.addAttribute("periodIncomesTotal", inc); 
		model.addAttribute("periodBillsTotal", bills); 
		model.addAttribute("periodDiscTotal", disc); 
		model.addAttribute("periodIncomeMinusBills", incMinusBills);
		model.addAttribute("periodBudgetBalance", budgetBalance);
		model.addAttribute("balanceInstructions", balanceInstructions);

		return model;
	}
	///________________________________________
	///End Calculations Section
	///_________________________________________
	
	//------------------------------------------------------
	//                BudgetPeriod Maps                            
	//------------------------------------------------------		

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
		BudgetPeriod selectedPeriod = repoBudgetPeriod.findById(periodId).orElse(null);
	    model.addAttribute("selectedBudgetPeriod", selectedPeriod);
	    model = getBudgetPeriodEntries(model, selectedPeriod);//Adds all the entries lists as attributes. See getBudgetPeriodEntries() function for attribute names.
	    model = getBudgetPeriodSums(model, selectedPeriod);//Adds all the calculation fields as attributes. See getBudgetPeriodSums() function for attribute names.
	    return "reports";
	}	
	
	@GetMapping("/deleteBudgetPeriod/{id}")
	public String deleteBudgetPeriod(@PathVariable("id") long id, Model model) {
		BudgetPeriod p = repoBudgetPeriod.findById(id).orElse(null);
	    repoBudgetPeriod.delete(p);
	    return viewAllBudgetPeriods(model);
	}
	////////////////End of BudgetPeriod Maps////////////////
	
	//------------------------------------------------------
	//                BudgetedBill Maps
	//------------------------------------------------------
	@GetMapping("/inputBudgetedBill/{periodId}")
	public String newBudgetedBill(@PathVariable("periodId") long periodId, Model model) {
		System.out.println("??? /inputBudgetedBill/{periodId}");		

		BudgetedBills p = new BudgetedBills();
		
		BudgetPeriod selectedPeriod = repoBudgetPeriod.findById(periodId).orElse(null);
		System.out.println("??? /inputBudgetedBill/{periodId} selectedPeriod "+selectedPeriod.getId());		

		//model.addAttribute("newBudgetedBill", repoBudgetedBills.findAll());
		model.addAttribute("newBudgetedBill", p);
		
		model.addAttribute("BudgetedBills", p);
		model.addAttribute("selectedBudgetPeriod", selectedPeriod);
		
		
		return "inputBudgetedBill";
	}
	
	/*@GetMapping("/inputBudgetPeriod")
	public String addNewBudgetPeriod(Model model) {
		BudgetPeriod p = new BudgetPeriod();
		model.addAttribute("newBudgetPeriod", p);
		return "inputPeriod";
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
		System.out.println("???/editBudgetedBill/{id} ITEM TO EDIT: " + p.toString());
		model.addAttribute("newBudgetedBills", p);
		return "inputBudgetedBill";
	}

	@PostMapping("/updateBudgetedBills/{id}/{periodId}")
	public String reviseBudgetedBills(@PathVariable("id") long id, @PathVariable("periodId") long periodId, BudgetedBills bb, Model model) {
		System.out.println("???/updateBudgetedBills/{id}/{periodId ITEM TO EDIT: " + bb.toString());

		BudgetPeriod selectedPeriod = repoBudgetPeriod.findById(periodId).orElse(null);
		System.out.println("???/updateBudgetedBills/{id}/{periodId budgetperiod: " + selectedPeriod.toString());

		bb.setBudgetPeriod(selectedPeriod); // commented this line out due to it causing errors

		
		repoBudgetedBills.save(bb);
		System.out.println("???/updateBudgetedBills/{id}/{periodId bb: " + bb.toString());

		return viewAllBudgetedBills(model);
	}
	
	@GetMapping("/deleteBudgetedBills/{id}")
	public String deleteBudgetedBills(@PathVariable("id") long id, Model model) {
		BudgetedBills p = repoBudgetedBills.findById(id).orElse(null);
	    repoBudgetedBills.delete(p);
	    return viewAllBudgetedBills(model);
	}
	////////////////End of BudgededBill Maps////////////////

	//------------------------------------------------------
	//                 BudgetedIncome maps                        
	//------------------------------------------------------
	/* Not using
	@GetMapping({ "/viewAllBudgetedIncomes" })
	public String viewAllBudgetedIncomes(Model model) {
		if(repoBudgetedIncome.findAll().isEmpty()) {
			return viewAllBudgetPeriods(model);
		}
		model.addAttribute("BudgetedIncomes", repoBudgetedIncome.findAll());
		return "resultsIncome";
	}
	
	Object findTotalIncome(BudgetPeriod selectedPeriod){
		 List<Object[]> results = repoBudgetedIncome.sumByBudgetPeriod(selectedPeriod);
		return results.get(0);
	}

	// Commented out this method because it already exists in first section -CMG
	double calcTotalBudgetedIncome(BudgetPeriod selectedPeriod){
		 List<BudgetedIncome> BudgetedIncomes = repoBudgetedIncome.findByBudgetPeriod(selectedPeriod);
		 double totalIncome = 0;

		 for (BudgetedIncome b : BudgetedIncomes)
		 {
			 totalIncome += b.getAmount();
				System.out.println("??? calcTotalBudgetedIncome "+b.getId()+" "+b.getAmount());

		 }
			System.out.println("??? calcTotalBudgetedIncome "+totalIncome);

		 return totalIncome;
	}
	*/

	///continue from period to inputBudgetedIncome
	//!!! use this format to allow join, pass in the period id and add BudgetPeriod as an attribute
	//!!!add the findAll attribute if you are also displaying the existing entries on the input form
	@GetMapping("/inputBudgetedIncome/{periodId}")
	public String addNewBudgetedIncome(@PathVariable("periodId") long periodId, Model model) {
	BudgetedIncome b = new BudgetedIncome();
	BudgetPeriod selectedPeriod = repoBudgetPeriod.findById(periodId).orElse(null);
		model.addAttribute("BudgetedIncomes", repoBudgetedIncome.findByBudgetPeriod(selectedPeriod)); ///!!!Fixed this by adding a method in BudgetedIncomeRepository to filter by period
		model.addAttribute("BudgetedIncomesTotal", calcTotalBudgetedIncome(selectedPeriod)); ///!!!Fixed this by adding a method in BudgetedIncomeRepository to filter by period
		System.out.println("??? /inputBudgetedIncome/{periodId} BudgetedIncomesTotal"+calcTotalBudgetedIncome(selectedPeriod));

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
			
	//editIncome post action leads here
	//If you want other destinations, add another if statement to return to that. If more reports are added, can specify which report to go to.
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
	    repoBudgetedIncome.flush();
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
	//!!!END Added GoTo to the above
	
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
	//////////End of BudgetedIncomeMaps/////////////////////
	
	//------------------------------------------------------
	//           DiscretionaryCategory Mappings 
	//------------------------------------------------------
	//!!! Use this format to allow join, pass in the period id and add BudgetPeriod as an attribute
	//!!! Add the findAll attribute if you are also displaying the existing entries on the input form
	// Continue from a previous page to budgetedDiscretionary
	@GetMapping("/inputDiscretionaryCategory/{periodId}/{GoTo}")
	public String addNewDiscretionaryCategory(@PathVariable("periodId") long periodId, @PathVariable("GoTo") String GoTo, Model model) {
		DiscretionaryCategory dc = new DiscretionaryCategory();
		BudgetPeriod selectedPeriod = repoBudgetPeriod.findById(periodId).orElse(null);
		
		model.addAttribute("newDiscretionaryCategory", dc);
		model.addAttribute("selectedBudgetPeriod", selectedPeriod);
		model.addAttribute("DiscretionaryCategories", repoDiscretionaryCategory.findAll());
		model.addAttribute("GoTo", GoTo);

		return "inputDiscretionaryCategory";
	}
	
	@GetMapping("/editDiscretionaryCategory/{periodId}/{id}/{GoTo}")
	public String showUpdateDiscretionaryCategory(@PathVariable("periodId") long periodId, @PathVariable("id") long id, @PathVariable("GoTo") String GoTo,  Model model) {
		DiscretionaryCategory dc = repoDiscretionaryCategory.findById(id).orElse(null);	
		BudgetPeriod selectedPeriod = repoBudgetPeriod.findById(periodId).orElse(null);
		
		model.addAttribute("newDiscretionaryCategory", dc);
		model.addAttribute("selectedBudgetPeriod", selectedPeriod);
		model.addAttribute("DiscretionaryCategories", repoDiscretionaryCategory.findAll());
		model.addAttribute("GoTo", GoTo);

		return "editDiscretionaryCategory"; 
	}
	
	@PostMapping("/updateDiscretionaryCategory/{periodId}/{GoTo}")
	public String reviseBudgetedDiscretionary(@PathVariable("periodId") long periodId, @PathVariable("GoTo") String GoTo, DiscretionaryCategory dc, Model model) {
		repoDiscretionaryCategory.save(dc); // Save entity.
		
		if(GoTo.equals("GoToReports")) {
			return viewReports(periodId, model);
		}
		else {
			return addNewDiscretionaryCategory(periodId, GoTo, model) ;
		}
	}
	
	@GetMapping("/deleteDiscretionaryCategory/{periodId}/{id}/{GoTo}")
	public String deleteDiscretionaryCategory(@PathVariable("periodId") long periodId, @PathVariable("id") long id, @PathVariable("GoTo") String GoTo, Model model) {
		DiscretionaryCategory dc = repoDiscretionaryCategory.findById(id).orElse(null);
	    
		repoDiscretionaryCategory.delete(dc); // Delete entity.
		
		if(GoTo.equals("GoToReports")) {
			return viewReports(periodId, model);
		}
		else {
			return addNewDiscretionaryCategory(periodId, GoTo, model);
		}
	}
	//////////End of DiscretionaryCategory Maps/////////////
	
	//------------------------------------------------------
	//            BudgetedDiscretionary Mappings 
	//------------------------------------------------------
	//!!! Use this format to allow join, pass in the period id and add BudgetPeriod as an attribute
	//!!! Add the findAll attribute if you are also displaying the existing entries on the input form
	// Continue from a previous page to budgetedDiscretionary
	@GetMapping("/inputBudgetedDiscretionary/{periodId}/{GoTo}")
	public String addNewBudgetedDiscretionary(@PathVariable("periodId") long periodId, @PathVariable("GoTo") String GoTo, Model model) {
		BudgetedDiscretionary bd = new BudgetedDiscretionary();
		BudgetPeriod selectedPeriod = repoBudgetPeriod.findById(periodId).orElse(null);
		
		model.addAttribute("newBudgetedDiscretionary", bd);
		model.addAttribute("selectedBudgetPeriod", selectedPeriod);
		model.addAttribute("BudgetedDiscretionaries", repoBudgetedDiscretionary.findByBudgetPeriod(selectedPeriod));
		model.addAttribute("DiscretionaryCategories", repoDiscretionaryCategory.findAll());
		model.addAttribute("BudgetedDiscTotal", calcTotalBudgetedDiscretionary(selectedPeriod)); 
		model.addAttribute("GoTo", GoTo);

		return "budgetedDiscretionary";
	}
		
	@GetMapping("/editBudgetedDiscretionary/{id}/{GoTo}")
	public String showUpdateBudgetedDiscretionary(@PathVariable("id") long id, @PathVariable("GoTo") String GoTo,  Model model) {
		BudgetedDiscretionary bd = repoBudgetedDiscretionary.findById(id).orElse(null);		
		long periodId = bd.getBudgetPeriod().getId(); // Get periodId.
		BudgetPeriod selectedPeriod = repoBudgetPeriod.findById(periodId).orElse(null);
		
		model.addAttribute("newBudgetedDiscretionary", bd);
		model.addAttribute("selectedBudgetPeriod", selectedPeriod);
		model.addAttribute("BudgetedDiscretionaries", repoBudgetedDiscretionary.findByBudgetPeriod(selectedPeriod));
		model.addAttribute("DiscretionaryCategories", repoDiscretionaryCategory.findAll());
		model.addAttribute("BudgetedDiscTotal", calcTotalBudgetedDiscretionary(selectedPeriod)); 

		return "editBudgetedDiscretionary"; 
	}

	@PostMapping("/updateBudgetedDiscretionary/{periodId}/{GoTo}")
	public String reviseBudgetedDiscretionary(@PathVariable("periodId") long periodId, @PathVariable("GoTo") String GoTo, BudgetedDiscretionary bd, Model model) {
		BudgetPeriod selectedPeriod = repoBudgetPeriod.findById(periodId).orElse(null); // Find selected budget period.
		DiscretionaryCategory selectedCategory = repoDiscretionaryCategory.findById(bd.getDiscCategory().getDiscCategoryId()).orElse(null);
		bd.setBudgetPeriod(selectedPeriod); // Add budget period to entity.
		bd.setDiscCategory(selectedCategory); // Add discretionary category to entity.
		repoBudgetedDiscretionary.save(bd); // Save entity.
		
		if(GoTo.equals("GoToReports")) {
			return viewReports(periodId, model);
		}
		else {
			return addNewBudgetedDiscretionary(periodId, GoTo, model) ;
		}
	}

	@GetMapping("/deleteBudgetedDiscretionary/{id}/{GoTo}")
	public String deleteBudgetedDiscretionary(@PathVariable("id") long id, @PathVariable("GoTo") String GoTo, Model model) {
		BudgetedDiscretionary bd = repoBudgetedDiscretionary.findById(id).orElse(null);
		
		long periodId = bd.getBudgetPeriod().getId(); // Get periodId before deleting.
	    
		repoBudgetedDiscretionary.delete(bd); // Delete entity.
		
		if(GoTo.equals("GoToReports")) {
			return viewReports(periodId, model);
		}
		else {
			return addNewBudgetedDiscretionary(periodId, GoTo, model);
		}
	}
	
	// Made the original map redirect to the above EDIT mapping so no need to change existing links, they will just default to the Input Form.
	@GetMapping("/editBudgetedDiscretionary/{id}")
	public String showUpdateBudgetedDiscretionary(@PathVariable("id") long id,  Model model) {
			return showUpdateBudgetedDiscretionary(id, "GoToInputForm", model);
	}
	
	// Made the original map redirect to the above UPDATE mapping so no need to change existing links, they will just default to the Input Form.
	@PostMapping("/updateBudgetedDiscretionary/{periodId}")
	public String reviseBudgetedDiscretionary( @PathVariable("periodId") long periodId, BudgetedDiscretionary bd, Model model) {	
		return reviseBudgetedDiscretionary(periodId, "GoToInputForm", bd, model)	;
	}
	
	// Made the original map redirect to the above DELETE mapping so no need to change existing links, they will just default to the Input Form.
	@GetMapping("/deleteBudgetedDiscretionary/{id}")
	public String deleteBudgetedDiscretionary(@PathVariable("id") long id, Model model) {
	    return deleteBudgetedDiscretionary(id, "GoToInputForm",  model);
	}
}	


	
	
//============================================================================================//	
	/*OLD STUFF FOR REFERENCE INCOME MAPPINGS
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