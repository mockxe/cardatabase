package io.mockxe.carDatabase.car.model;

import java.sql.Date;

import io.mockxe.carDatabase.brand.dao.BrandDAO;
import io.mockxe.carDatabase.brand.model.Brand;
import io.mockxe.carDatabase.engine.dao.EngineDAO;
import io.mockxe.carDatabase.engine.model.Engine;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Car {
	
	// Create ApplicationContext with xml file from classpath
	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
	
	// Get BrandDAO bean out of context and cast it as BrandDAO
	BrandDAO brandDAO = (BrandDAO) context.getBean("brandDAO");
		
	// Get EngineDAO bean out of context and cast it as EngineDAO
	EngineDAO engineDAO = (EngineDAO) context.getBean("engineDAO");
	
	private int index;
	private Brand brand;
	private Engine engine;
	private int displacement;
	private Date date;
	private int doors;
	private String color;
	private int weight;
	private String chassis;
	private int pollutant;
	private String description;
	
	// brand/engine by index, without index
	public Car(int brand, int engine, int displacement, Date date, int doors, String color, int weight, String chassis, int pollutant, String description) {
		this.brand = brandDAO.getEntry(brand);
		this.engine = engineDAO.getEntry(engine);
		this.displacement = displacement;
		this.date = date;
		this.doors = doors;
		this.color = color;
		this.weight = weight;
		this.chassis = chassis;
		this.pollutant = pollutant;
		this.description = description;
	}
	
	// brand/engine by index, with index
		public Car(int index, int brand, int engine, int displacement, Date date, int doors, String color, int weight, String chassis, int pollutant, String description) {
			this.index = index;
			this.brand = brandDAO.getEntry(brand);
			this.engine = engineDAO.getEntry(engine);
			this.displacement = displacement;
			this.date = date;
			this.doors = doors;
			this.color = color;
			this.weight = weight;
			this.chassis = chassis;
			this.pollutant = pollutant;
			this.description = description;
		}
	
	// without index
	public Car(Brand brand, Engine engine, int displacement, Date date, int doors, String color, int weight, String chassis, int pollutant, String description) {
		this.brand = brand;
		this.engine = engine;
		this.displacement = displacement;
		this.date = date;
		this.doors = doors;
		this.color = color;
		this.weight = weight;
		this.chassis = chassis;
		this.pollutant = pollutant;
		this.description = description;
	}
	
	// with index
	public Car(int index, Brand brand, Engine engine, int displacement, Date date, int doors, String color, int weight, String chassis, int pollutant, String description) {
		this.index = index;
		this.brand = brand;
		this.engine = engine;
		this.displacement = displacement;
		this.date = date;
		this.doors = doors;
		this.color = color;
		this.weight = weight;
		this.chassis = chassis;
		this.pollutant = pollutant;
		this.description = description;
	}

	// verify whether users input is save for the database... never trust a users input. NEVER.
	public boolean validateInput() {
		
		// check brand
		// if index didn't exist when creating the object it is already null
		if (brand == null) {
				return false;
		}
		
		// check engine
		// if index didn't exist when creating the object it is already null
		if (engine == null) {
			return false;
		}
		
		// check displacement
		if (this.displacement > 100000 || this.displacement < 0) {
			return false;
		}
		
		// check date
		if (this.date.after(new java.util.Date())) {
			return false;
		}
		
		// check doors
		if (this.doors > 10 || this.doors < 1) {
			return false;
		}

		// check color
		if (!this.color.matches("#[0-9a-f]{6}")) {
			return false;
		}
			
		
		// check weight
		if (this.weight > 10000 || this.weight < 0) {
			return false;
		}
		
		// check chassis 
		if (this.chassis.length() > 17 || this.chassis.length() < 11) {
			return false;
		}
		
		// check pollutant
		if (this.pollutant > 6 || this.pollutant < 1) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		return "Car [index=" + index + ", brand=" + brand + ", engine=" + engine + ", displacement=" + displacement + ", date=" + date
				+ ", doors=" + doors + ", color=" + color + ", weight=" + weight + ", chassis=" + chassis
				+ ", description=" + description + "]";
	}

	public int getIndex() {
		return index;
	}
	
	public Brand getBrand() {
		return brand;
	}

	public Engine getEngine() {
		return engine;
	}
	
	public int getDisplacement() {
		return displacement;
	}

	public Date getDate() {
		return date;
	}

	public int getDoors() {
		return doors;
	}

	public String getColor() {
		return color;
	}

	public int getWeight() {
		return weight;
	}

	public String getChassis() {
		return chassis;
	}

	public int getPollutant() {
		return pollutant;
	}
	
	public String getDescription() {
		return description;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public void setDisplacement(int displacement) {
		this.displacement = displacement;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDoors(int doors) {
		this.doors = doors;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void setChassis(String chassis) {
		this.chassis = chassis;
	}
	
	public void setPollutant(int pollutant) {
		this.pollutant = pollutant;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
