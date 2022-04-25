package io.mockxe.carDatabase.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import io.mockxe.carDatabase.brand.dao.BrandDAO;
import io.mockxe.carDatabase.brand.model.Brand;
import io.mockxe.carDatabase.car.dao.CarDAO;
import io.mockxe.carDatabase.car.model.Car;
import io.mockxe.carDatabase.engine.dao.EngineDAO;
import io.mockxe.carDatabase.engine.model.Engine;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class WelcomeController {

	// create ApplicationContext with xml file from classpath
	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
	
	// get CarDAO bean out of context and cast it as CarDAO
	CarDAO carDAO = (CarDAO) context.getBean("carDAO");
	
	// get BrandDAO bean out of context and cast it as BrandDAO
	BrandDAO brandDAO = (BrandDAO) context.getBean("brandDAO");
		
	// get EngineDAO bean out of context and cast it as EngineDAO
	EngineDAO engineDAO = (EngineDAO) context.getBean("engineDAO");
	
	// global variable for cross site request forgery token
	private int xsrftoken;
	
	// create pseudo-random xsrf token
	private int createXsrfToken() {
		return Double.toString((Math.random() * 10 )).hashCode();
	}
	
	// user requests overview page
	@RequestMapping(value = "/overview")
	public String httpShowPageOverview(ModelMap model) {

		ArrayList<Car> cars = carDAO.getAllEntries();
		
		String tableStr = "";
		
		// generate table with all cars in it
		for (Car car : cars) {

			// Start row
			tableStr += "<tr>\n";
			
			// insert cells
			tableStr += "<td>" + car.getIndex() + "</td>\n";
			tableStr += "<td>" + car.getBrand().getName() + "</td>\n";
			tableStr += "<td>" + car.getEngine().getName() + "</td>\n";
			tableStr += "<td>" + car.getDisplacement() + "</td>\n";
			tableStr += "<td>" + car.getDate() + "</td>\n";
			tableStr += "<td>" + car.getDoors() + "</td>\n";
			tableStr += "<td bgcolor=\"" + car.getColor() + "\"></td>\n";
			tableStr += "<td>" + car.getWeight() + "</td>\n";
			tableStr += "<td>" + car.getChassis() + "</td>\n";
			tableStr += "<td>" + car.getPollutant() + "</td>\n";
			tableStr += "<td>" + car.getDescription() + "</td>\n";
			
			// open cell for buttons
			tableStr += "<td>\n";
			
			// insert buttons
			tableStr += "<a href=\"details?index=" + car.getIndex() + "\"><button class=\"action\">details</button></a>";
			tableStr += "<a href=\"edit?index=" + car.getIndex() + "\"><button class=\"action\">edit</button></a>";
			tableStr += "<a href=\"delete?index=" + car.getIndex() + "\"><button class=\"action\">delete</button></a>";
			
			// close cell for buttons
			tableStr += "</td>\n";
			
			// close row
			tableStr += "</tr>";
		}

		// put HTML code for table into model
		model.put("table", tableStr);
		
		// set body page to overview
		model.put("body", "overview");

		// return the mainpage
		return "mainpage";
	}

	// User requests add page
	@RequestMapping(value = "/add")
	public String httpShowPageAdd(ModelMap model) {
		
		// create xsrf token
		this.xsrftoken = createXsrfToken();
		model.put("xsrftoken", xsrftoken);
		
		// generate selection with all brands in it
		ArrayList<Brand> brands = brandDAO.getAllEntries();
		
		String brandsStr = "";
		
		for (Brand brand : brands) {
			brandsStr += "<option value=\"" + brand.getIndex() + "\">" + brand.getName() + "</option>\n";
		}
		
		// put HTML code for table into model
		model.put("brands", brandsStr);

		// generate selection with all brands in it
		ArrayList<Engine> engines = engineDAO.getAllEntries();

		String enginesStr = "";

		for (Engine engine : engines) {
			enginesStr += "<option value=\"" + engine.getIndex() + "\">" + engine.getName() + "</option>\n";
		}

		// put HTML code for table into model
		model.put("engines", enginesStr);
		
		// set some default values for the user
		model.put("brandInt", "1"); 
		model.put("engineInt", "1");
		model.put("displacement", "2000");
		model.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		model.put("doors", "5");
		model.put("color", "#c6c6c6");
		model.put("weight", "1250");
		model.put("chassis", "W0L000051T2123456");
		model.put("pollutant", "5");
		model.put("description", "Enter description here...");

		// set method to save in model
		model.put("method", "save");

		// set body page to edit
		model.put("body", "edit");

		// return the mainpage
		return "mainpage";
	}
	
	// User request edit
	@RequestMapping(value = "edit")
	public String httpShowPageEdit(@RequestParam int index, ModelMap model) {

		// create xsrf token
		this.xsrftoken = createXsrfToken();
		model.put("xsrftoken", xsrftoken);
		
		// generate selection with all brands in it
		ArrayList<Brand> brands = brandDAO.getAllEntries();
		
		String brandsStr = "";
		
		for (Brand brand : brands) {
			brandsStr += "<option value=\"" + brand.getIndex() + "\">" + brand.getName() + "</option>\n";
		}
		
		// put HTML code for table into model
		model.put("brands", brandsStr);

		// generate selection with all brands in it
		ArrayList<Engine> engines = engineDAO.getAllEntries();

		String enginesStr = "";

		for (Engine engine : engines) {
			enginesStr += "<option value=\"" + engine.getIndex() + "\">" + engine.getName() + "</option>\n";
		}

		// put HTML code for table into model
		model.put("engines", enginesStr);
		
		Car car;

		// check if car index is valid
		if (carDAO.indexExists(index)) {
			//  load car details into model by index
			car = carDAO.getEntry(index);
			model.put("index", car.getIndex());
			model.put("brand", car.getBrand());
			model.put("brandInt", car.getBrand().getIndex());
			model.put("engine", car.getEngine());
			model.put("engineInt", car.getEngine().getIndex());
			model.put("displacement", car.getDisplacement());
			model.put("date", car.getDate());
			model.put("doors", car.getDoors());
			model.put("color", car.getColor());
			model.put("weight", car.getWeight());
			model.put("chassis", car.getChassis());
			model.put("pollutant", car.getPollutant());
			model.put("description", car.getDescription());
		} else {

			// else put errormsg into model
			model.put("errorMsg", "invalid selection");
		}
		
		// set method to update in model
		model.put("method", "update");

		// set body page to edit
		model.put("body", "edit");

		// return mainpage page
		return "mainpage";
	}

	// User request details
	@RequestMapping(value = "details")
	public String httpShowPageDetails(@RequestParam int index, ModelMap model) {

		Car car;
		
		// check if car index is valid
		if (carDAO.indexExists(index)) {

			//  load car details into model by index
			car = carDAO.getEntry(index);
			model.put("brand", car.getBrand().getName());
			model.put("engine", car.getEngine().getName());
			model.put("displacement", car.getDisplacement());
			model.put("date", car.getDate());
			model.put("doors", car.getDoors());
			model.put("color", car.getColor());
			model.put("weight", car.getWeight());
			model.put("chassis", car.getChassis());
			model.put("pollutant", car.getPollutant());
			model.put("description", car.getDescription());
			
		} else {

			// else put errormsg into model
			model.put("errorMsg", "invalid selection");
		}

		// set body page to edit
		model.put("body", "details");

		// return mainpage page
		return "mainpage";
	}

	// User request delete
	@RequestMapping(value = "delete")
	public String httpShowPageDelete(@RequestParam int index, ModelMap model) {

		// create xsrf token
		this.xsrftoken = createXsrfToken();
		model.put("xsrftoken", xsrftoken);
		
		// check if car index is valid
		if (carDAO.indexExists(index)) {
			
			//put index into model, so we have something to delete
			model.put("index", index);
			
		} else {

			// else put errormsg into model
			model.put("errorMsg", "invalid selction");

		}

		// set body page to edit
		model.put("body", "delete");

		// return mainpage page
		return "mainpage";
	}
	
	// user confirmed deletion
	@RequestMapping(value="deleteYes")
	public RedirectView httpShowPageDeleteYes(@RequestParam int index, @RequestParam int xsrftoken) {
		
		//check if xsrf token match and execute actual action
		if (this.xsrftoken == xsrftoken) {

			//delte car in database
			carDAO.deleteEntry(index);
	
		}
		
		// create new xsrf token, so it can't be reused or brute forced
		this.xsrftoken = createXsrfToken();
				
		// send user back
		return new RedirectView("overview");
	}
	
	// user did not confirmed deletion
	@RequestMapping(value="deleteNo")
	public RedirectView httpShowPageDeleteNo() {
		
		// send user back, do nothing
		return new RedirectView("overview");
	}
	
	// user saves new data
	@PostMapping(value="save")
	public RedirectView httpPostPageSave(
			@RequestParam int brand,
			@RequestParam int engine,
			@RequestParam int displacement,
			@RequestParam Date date,
			@RequestParam int doors,
			@RequestParam String color,
			@RequestParam int weight,
			@RequestParam String chassis,
			@RequestParam int pollutant,
			@RequestParam String description,
			@RequestParam int xsrftoken,
			ModelMap model
			) {
		
		// create Car object to save data in
		Car car = new Car(brand, engine, displacement, date, doors, color, weight, chassis, pollutant, description);

		
		// validate input
		if (car.validateInput()) { 
			
			// check if xsrf token match and execute actual action
			if (this.xsrftoken == xsrftoken) {
				// save car in database
				carDAO.addEntry(car);
			}
			
			// create new xsrf token, so it can't be reused or brute forced
			this.xsrftoken = createXsrfToken();
		
			// get back to overview page
			return new RedirectView("overview");
			
		} else {

			// get back to edit page
			return new RedirectView("saveError");
			
		}
		
	}
	
	// data to save was invalid 
	@RequestMapping(value="saveError")
	public String httpShowPageSaveError(ModelMap model) {
		
		model.put("errorMsg", "illegal value in form");
		
		// set body page to back
		model.put("body", "back");

		// return mainpage page
		return "mainpage";
	}
	
	
	// User updates existing data
	@PostMapping(value="update")
	public RedirectView httpPostPageUpdate(
			@RequestParam int index,
			@RequestParam int brand,
			@RequestParam int engine,
			@RequestParam int displacement,
			@RequestParam Date date,
			@RequestParam int doors,
			@RequestParam String color,
			@RequestParam int weight,
			@RequestParam String chassis,
			@RequestParam int pollutant,
			@RequestParam String description,
			@RequestParam int xsrftoken
			) {
		
		//create Car object to save data in
		Car car = new Car(brand, engine, displacement, date, doors, color, weight, chassis, pollutant, description);

		// validate input
		if (car.validateInput()) { 
			
			// check if xsrf tokens match and execute actual action
			if (this.xsrftoken == xsrftoken) {
				// save car in database
				carDAO.updateEntry(car, index);
			}
		
			// create new xsrf token, so it can't be reused or brute forced
			this.xsrftoken = createXsrfToken();
			
			// get back to overview page
			return new RedirectView("overview");
			
		} else {

			// get back to edit page
			return new RedirectView("saveError");
			
		}
		
	}

}
